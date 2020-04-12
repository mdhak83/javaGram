package org.javagram.mtproto.pq;

import org.javagram.mtproto.ServerException;
import org.javagram.mtproto.TransportSecurityException;
import org.javagram.mtproto.log.Logger;
import org.javagram.mtproto.secure.CryptoUtils;
import org.javagram.mtproto.secure.Entropy;
import org.javagram.mtproto.secure.Keys;
import org.javagram.mtproto.secure.pq.PQSolver;
import org.javagram.mtproto.state.ConnectionInfo;
import org.javagram.mtproto.time.TimeOverlord;
import org.javagram.mtproto.tl.pq.ClientDhInner;
import org.javagram.mtproto.tl.pq.DhGenFailure;
import org.javagram.mtproto.tl.pq.DhGenOk;
import org.javagram.mtproto.tl.pq.DhGenResult;
import org.javagram.mtproto.tl.pq.DhGenRetry;
import org.javagram.mtproto.tl.pq.PQInner;
import org.javagram.mtproto.tl.pq.ReqDhParams;
import org.javagram.mtproto.tl.pq.ReqPQ;
import org.javagram.mtproto.tl.pq.ReqSetDhClientParams;
import org.javagram.mtproto.tl.pq.ResPQ;
import org.javagram.mtproto.tl.pq.ServerDhFailure;
import org.javagram.mtproto.tl.pq.ServerDhInner;
import org.javagram.mtproto.tl.pq.ServerDhOk;
import org.javagram.mtproto.tl.pq.ServerDhParams;
import org.javagram.mtproto.tl.pq.TLInitContext;
import org.javagram.mtproto.transport.ConnectionType;
import org.javagram.mtproto.transport.PlainTcpConnection;
import org.javagram.mtproto.transport.TransportRate;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.javagram.MyTLAppConfiguration;
import static org.javagram.mtproto.secure.CryptoUtils.AES256IGEDecrypt;
import static org.javagram.mtproto.secure.CryptoUtils.AES256IGEEncrypt;
import static org.javagram.mtproto.secure.CryptoUtils.SHA1;
import static org.javagram.mtproto.secure.CryptoUtils.align;
import static org.javagram.mtproto.secure.CryptoUtils.alignKeyZero;
import static org.javagram.mtproto.secure.CryptoUtils.arrayEq;
import static org.javagram.mtproto.secure.CryptoUtils.concat;
import static org.javagram.mtproto.secure.CryptoUtils.fromBigInt;
import static org.javagram.mtproto.secure.CryptoUtils.loadBigInt;
import static org.javagram.mtproto.secure.CryptoUtils.substring;
import static org.javagram.mtproto.secure.CryptoUtils.xor;
import static org.javagram.utils.StreamingUtils.readBytes;
import static org.javagram.utils.StreamingUtils.readInt;
import static org.javagram.utils.StreamingUtils.readLong;
import static org.javagram.utils.StreamingUtils.writeByteArray;
import static org.javagram.utils.StreamingUtils.writeInt;
import static org.javagram.utils.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 4:11
 */
public class Authorizer {
    private static final String TAG = "Authorizer";
    private static final int AUTH_ATTEMPT_COUNT = 5;
    private static final int AUTH_RETRY_COUNT = 5;

    private PlainTcpConnection context;
    private TLInitContext initContext;

    public Authorizer(MyTLAppConfiguration config) {
        this.initContext = new TLInitContext();
        this.initContext.build(config);
    }

    private <T extends TLObject> T executeMethod(TLMethod<T> object) throws IOException {
        long requestMessageId = TimeOverlord.getInstance().createWeakMessageId();
        long start = System.nanoTime();
        byte[] data = object.serialize();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeLong(0, out); // Empty AUTH_ID
        writeLong(requestMessageId, out); // MessageID
        writeInt(data.length, out);
        writeByteArray(data, out);
        byte[] response = this.context.executeMethod(out.toByteArray());
        ByteArrayInputStream in = new ByteArrayInputStream(response);
        long authId = readLong(in);
        if (authId != 0) {
            throw new IOException("Auth id might be equal to zero");
        }
        long messageId = readLong(in);
        // TimeOverlord.getInstance().onMethodExecuted(requestMessageId, messageId, (System.nanoTime() - start) / 1000000);
        int length = readInt(in);
        byte[] messageResponse = readBytes(length, in);
        return object.deserializeResponse(messageResponse, this.initContext);
    }

    private PqAuth authAttempt() throws IOException {
        // PQ-Auth start
        byte[] nonce = Entropy.getInstance().generateSeed(16);
        ResPQ resPQ = executeMethod(new ReqPQ(nonce));
        byte[] serverNonce = resPQ.getServerNonce();

        long fingerprint = 0;
        Keys.Key publicKey = null;
        outer:
        for (Long srcFingerprint : resPQ.getFingerprints()) {
            for (Keys.Key key : Keys.AVAILABLE_KEYS) {
                if (srcFingerprint.equals(key.getFingerprint())) {
                    fingerprint = srcFingerprint;
                    publicKey = key;
                    break outer;
                }
            }
        }

        if (fingerprint == 0) {
            throw new IOException("Unknown public keys");
        }

        BigInteger pq = loadBigInt(resPQ.getPq());
        BigInteger p = null;
        try {
            long start = System.currentTimeMillis();
            p = PQSolver.solvePq(pq);
            Logger.d(TAG, "Solved PQ in " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            throw new IOException();
        }
        BigInteger q = pq.divide(p);

        byte[] newNonce = Entropy.getInstance().generateSeed(32);

        PQInner inner = new PQInner(resPQ.getPq(), fromBigInt(p), fromBigInt(q), nonce, serverNonce, newNonce);

        byte[] pqInner = inner.serialize();

        // PQ INNER
        byte[] hash = CryptoUtils.SHA1(pqInner);
        byte[] seed = Entropy.getInstance().generateSeed(255 - hash.length - pqInner.length);
        byte[] dataWithHash = concat(hash, pqInner, seed);

        byte[] encrypted = CryptoUtils.RSA(dataWithHash, publicKey.getPublicKey(), publicKey.getExponent());

        long start = System.nanoTime();
        ServerDhParams dhParams = executeMethod(new ReqDhParams(nonce, serverNonce, fromBigInt(p), fromBigInt(q), fingerprint, encrypted));
        long dhParamsDuration = (System.nanoTime() - start) / (1000 * 1000);

        if (dhParams instanceof ServerDhFailure) {
            ServerDhFailure hdFailure = (ServerDhFailure) dhParams;
            if (arrayEq(hdFailure.getNewNonceHash(), SHA1(newNonce))) {
                throw new ServerException("Received server_DH_params_fail#79cb045d");
            } else {
                throw new TransportSecurityException("Received server_DH_params_fail#79cb045d with incorrect hash");
            }
        }

        // PQ-Auth end
        // DH-Auth start
        ServerDhOk serverDhParams = (ServerDhOk) dhParams;

        byte[] encryptedAnswer = serverDhParams.getEncryptedAnswer();

        byte[] tmpAesKey = concat(SHA1(newNonce, serverNonce), substring(SHA1(serverNonce, newNonce), 0, 12));
        byte[] tmpAesIv = concat(concat(substring(SHA1(serverNonce, newNonce), 12, 8), SHA1(newNonce, newNonce)), substring(newNonce, 0, 4));

        byte[] answer = AES256IGEDecrypt(encryptedAnswer, tmpAesIv, tmpAesKey);
        ByteArrayInputStream stream = new ByteArrayInputStream(answer);
        byte[] answerHash = readBytes(20, stream); // Hash
        ServerDhInner dhInner = (ServerDhInner) this.initContext.deserializeMessage(stream);
        if (!arrayEq(answerHash, SHA1(dhInner.serialize()))) {
            throw new TransportSecurityException();
        }

        TimeOverlord.getInstance().onServerTimeArrived(dhInner.getServerTime() * 1000L, dhParamsDuration);

        for (int i = 0; i < AUTH_RETRY_COUNT; i++) {
            BigInteger b = loadBigInt(Entropy.getInstance().generateSeed(256));
            BigInteger g = new BigInteger(dhInner.getG() + "");
            BigInteger dhPrime = loadBigInt(dhInner.getDhPrime());
            BigInteger gb = g.modPow(b, dhPrime);

            BigInteger authKeyVal = loadBigInt(dhInner.getG_a()).modPow(b, dhPrime);
            byte[] authKey = alignKeyZero(fromBigInt(authKeyVal), 256);
            byte[] authAuxHash = substring(SHA1(authKey), 0, 8);

            ClientDhInner clientDHInner = new ClientDhInner(nonce, serverNonce, i, fromBigInt(gb));
            byte[] innerData = clientDHInner.serialize();
            byte[] innerDataWithHash = align(concat(SHA1(innerData), innerData), 16);
            byte[] dataWithHashEnc = AES256IGEEncrypt(innerDataWithHash, tmpAesIv, tmpAesKey);

            DhGenResult result = executeMethod(new ReqSetDhClientParams(nonce, serverNonce, dataWithHashEnc));

            if (result instanceof DhGenOk) {
                byte[] newNonceHash = substring(SHA1(newNonce, new byte[]{1}, authAuxHash), 4, 16);

                if (!arrayEq(result.getNewNonceHash(), newNonceHash))
                    throw new TransportSecurityException();

                long serverSalt = readLong(xor(substring(newNonce, 0, 8), substring(serverNonce, 0, 8)), 0);

                return new PqAuth(authKey, serverSalt, this.context.getSocket());
            } else if (result instanceof DhGenRetry) {
                byte[] newNonceHash = substring(SHA1(newNonce, new byte[]{2}, authAuxHash), 4, 16);

                if (!arrayEq(result.getNewNonceHash(), newNonceHash))
                    throw new TransportSecurityException();

            } else if (result instanceof DhGenFailure) {
                byte[] newNonceHash = substring(SHA1(newNonce, new byte[]{3}, authAuxHash), 4, 16);

                if (!arrayEq(result.getNewNonceHash(), newNonceHash))
                    throw new TransportSecurityException();

                throw new ServerException();
            }
        }
        throw new ServerException();
    }

    public PqAuth doAuth(ConnectionInfo[] infos) {
        TransportRate rate = new TransportRate(infos);
        for (int i = 0; i < AUTH_ATTEMPT_COUNT; i++) {
            ConnectionType connectionType = rate.tryConnection();
            try {
                this.context = new PlainTcpConnection(connectionType.getHost(), connectionType.getPort());
                rate.onConnectionSuccess(connectionType.getId());
            } catch (IOException e) {
                Logger.e(TAG, e);
                rate.onConnectionFailure(connectionType.getId());
                continue;
            }

            try {
                return authAttempt();
            } catch (IOException e) {
                Logger.e(TAG, e);
            } finally {
                if (this.context != null) {
                    this.context.destroy();
                    this.context = null;
                }
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }
}
