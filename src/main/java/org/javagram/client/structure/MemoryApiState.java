package org.javagram.client.structure;

import org.jetbrains.annotations.NotNull;
import org.javagram.api.TLConfig;
import org.javagram.api.TLDcOption;
import org.javagram.client.structure.storage.TLDcInfo;
import org.javagram.client.structure.storage.TLKey;
import org.javagram.client.structure.storage.TLLastKnownSalt;
import org.javagram.client.structure.storage.TLStorage;
import org.javagram.mtproto.state.AbsMTProtoState;
import org.javagram.mtproto.state.ConnectionInfo;
import org.javagram.mtproto.state.KnownSalt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.javagram.MyTLAppConfiguration;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.auth.base.authorization.TLAbsAuthAuthorization;
import org.javagram.api.auth.base.authorization.TLAuthAuthorization;
import org.javagram.api.auth.base.authorization.TLAuthAuthorizationSignUpRequired;
import org.javagram.api.user.base.TLUser;
import org.javagram.client.structure.storage.AbsApiState;
import org.javagram.utils.BotLogger;

public class MemoryApiState extends TLPersistence<TLStorage> implements AbsApiState {

    private static final String LOGTAG = "[MemoryApiState]";
    
    private static final Map<Integer, TLDcInfo> ALL_DC = new HashMap<>();
    
    static {
        ALL_DC.put(1, new TLDcInfo(0, 1, "149.154.175.50", 443, 0));
        ALL_DC.put(2, new TLDcInfo(0, 2, "149.154.167.51", 443, 0));
        ALL_DC.put(3, new TLDcInfo(0, 3, "149.154.175.100", 443, 0));
        ALL_DC.put(4, new TLDcInfo(0, 4, "149.154.167.91", 443, 0));
        ALL_DC.put(5, new TLDcInfo(0, 5, "149.154.171.5", 443, 0));
        ALL_DC.put(6, new TLDcInfo(0, 6, "173.240.5.1", 443, 0));
    }

    private class DcAddress {
        public final HashMap<Integer, Integer> ports = new HashMap<>();
        public String host;
    }

    public MemoryApiState() {
    }
    
    @Override
    public void build(MyTLAppConfiguration config) {
        super.build(config, TLStorage.class);
        if (this.getObject().getDcInfos().isEmpty()) {
            ALL_DC.values().forEach((dc) -> {
                this.getObject().getDcInfos().add(dc);
            });
        }
    }

    public int[] getKnownDc() {
        HashSet<Integer> dcs = new HashSet<>();
        this.getObject().getDcInfos().forEach((dcInfo) -> {
            dcs.add(dcInfo.getDcId());
        });
        Integer[] dcsArray = dcs.toArray(new Integer[0]);
        int[] res = new int[dcs.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dcsArray[i];
        }
        return res;
    }


    private TLKey findKey(int dcId) {
        for (TLKey key : this.getObject().getKeys()) {
            if (key.getDcId() == dcId) {
                return key;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean isAuthenticated() {
        return this.isAuthenticated(this.getPrimaryDc());
    }

    @Override
    public synchronized void doAuth(@NotNull TLAbsAuthAuthorization authorization) {
        if (authorization instanceof TLAuthAuthorization && authorization.getUser() != null && authorization.getUser() instanceof TLUser) {
            final TLUser user = (TLUser) authorization.getUser();
            final TLKey key = findKey(getPrimaryDc());
            key.setAuthorised(true);
            this.getObject().setUid(user.getId());
            this.getObject().setPhone(user.getPhone());
            this.write();
        } else {
            if (authorization instanceof TLAuthAuthorizationSignUpRequired) {
                BotLogger.error(LOGTAG, "Prior signup is required.");
            }
            if (!(authorization.getUser() instanceof TLUser)) {
                BotLogger.error(LOGTAG, "Authorized user is not a TLUser : " + authorization.getUser() != null ? authorization.getUser().getClass().getSimpleName() : "null");
            }
        }
    }

    public synchronized void doAuth(int uid, String phone) {
        TLKey key = this.findKey(getPrimaryDc());
        key.setAuthorised(true);
        this.getObject().setUid(uid);
        this.getObject().setPhone(phone);
        this.write();
    }

    @Override
    public synchronized int getPrimaryDc() {
        return this.getObject().getPrimaryDc();
    }

    @Override
    public synchronized void setPrimaryDc(int dc) {
        this.getObject().setPrimaryDc(dc);
        this.write();
    }

    @Override
    public synchronized boolean isAuthenticated(int dcId) {
        TLKey key = this.findKey(dcId);
        return (key != null && key.isAuthorised());
    }

    @Override
    public synchronized void setAuthenticated(int dcId, boolean auth) {
        TLKey key = this.findKey(dcId);
        key.setAuthorised(auth);
        this.write();
    }

    @Override
    public synchronized void updateSettings(TLConfig config) {
        int version = 0;
        for (TLDcInfo info : this.getObject().getDcInfos()) {
            version = Math.max(version, info.getVersion());
        }

        boolean hasUpdates = false;
        for (TLDcOption option : config.getDcOptions()) {
            if (option.getFlags() != 0) {
                continue;
            }
            boolean contains = false;
            org.javagram.api._primitives.TLVector<TLDcInfo> var = this.getObject().getDcInfos();
            for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
                if (info.getAddress().equals(option.getIpAddress()) && info.getPort() == option.getPort() && info.getDcId() == option.getId() && info.getVersion() == version) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                hasUpdates = true;
            }
        }

        if (!hasUpdates) {
            return;
        }

        int nextVersion = version + 1;
        config.getDcOptions().stream().filter(option -> !(option.getFlags() != 0)).forEachOrdered(option -> {
            org.javagram.api._primitives.TLVector<TLDcInfo> var = this.getObject().getDcInfos();
            for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
                if (info.getAddress().equals(option.getIpAddress()) && info.getDcId() == option.getId()) {
                    this.getObject().getDcInfos().remove(info);
                }
            }
            this.getObject().getDcInfos().add(new TLDcInfo(option.getFlags(), option.getId(), option.getIpAddress(), option.getPort(), nextVersion));
        });
        this.write();
    }

    public synchronized void updateDCInfo(int flags, int dcId, String ip, int port) {
        TLVector<TLDcInfo> var = this.getObject().getDcInfos();
        for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
            if (info.getAddress().equals(ip) && info.getPort() == port && info.getDcId() == dcId) {
                this.getObject().getDcInfos().remove(info);
            }
        }

        int version = 0;
        for (TLDcInfo info : this.getObject().getDcInfos()) {
            version = Math.max(version, info.getVersion());
        }

        this.getObject().getDcInfos().add(new TLDcInfo(flags, dcId, ip, port, version));
        this.write();
    }

    @Override
    public synchronized byte[] getAuthKey(int dcId) {
        TLKey key = findKey(dcId);
        return key != null ? key.getAuthKey() : null;
    }

    @Override
    public synchronized void putAuthKey(int dcId, byte[] authKey) {
        TLKey key = findKey(dcId);
        if (key != null) {
            return;
        }
        this.getObject().getKeys().add(new TLKey(dcId, authKey));
        this.write();
    }

    @Override
    public ConnectionInfo[] getAvailableConnections(final int dcId) {
        final ArrayList<TLDcInfo> infos = new ArrayList<>();
        int maxVersion = 0;
        for (TLDcInfo info : this.getObject().getDcInfos()) {
            if (info.getDcId() == dcId) {
                infos.add(info);
                maxVersion = Math.max(maxVersion, info.getVersion());
            }
        }

        ArrayList<ConnectionInfo> res = new ArrayList<>();

        // Maximum version addresses
        HashMap<String, DcAddress> mainAddresses = new HashMap<>();
        for (TLDcInfo i : infos) {
            if (i.getVersion() != maxVersion) {
                continue;
            }

            if (mainAddresses.containsKey(i.getAddress())) {
                mainAddresses.get(i.getAddress()).ports.put(i.getPort(), 1);
            } else {
                DcAddress address = new DcAddress();
                address.ports.put(i.getPort(), 1);
                address.host = i.getAddress();
                mainAddresses.put(i.getAddress(), address);
            }
        }

        mainAddresses.values().stream().forEachOrdered(address -> {
            address.ports.put(443, 2);
            address.ports.put(80, 1);
            address.ports.put(25, 0);
        });

        HashMap<Integer, HashMap<String, DcAddress>> otherAddresses = new HashMap<>();

        for (TLDcInfo i : infos) {
            if (i.getVersion() == maxVersion) {
                continue;
            }

            if (!otherAddresses.containsKey(i.getVersion())) {
                otherAddresses.put(i.getVersion(), new HashMap<>());
            }

            HashMap<String, DcAddress> addressHashMap = otherAddresses.get(i.getVersion());

            if (addressHashMap.containsKey(i.getAddress())) {
                addressHashMap.get(i.getAddress()).ports.put(i.getPort(), 1);
            } else {
                DcAddress address = new DcAddress();
                address.ports.put(i.getPort(), 1);
                address.host = i.getAddress();
                addressHashMap.put(i.getAddress(), address);
            }
        }

        otherAddresses.keySet().forEach((version) -> {
            otherAddresses.get(version).values().stream().filter(address -> !(mainAddresses.containsKey(address.host))).forEach(address -> {
                address.ports.put(443, 2);
                address.ports.put(25, 0);
                address.ports.put(80, 1);
            });
        });


        // Writing main addresses
        int index = 0;

        for (DcAddress address : mainAddresses.values()) {
            for (Integer port : address.ports.keySet()) {
                int priority = maxVersion + address.ports.get(port);
                res.add(new ConnectionInfo(index++, priority, address.host, port));
            }
        }

        // Writing other addresses

        for (Integer version : otherAddresses.keySet()) {
            for (DcAddress address : otherAddresses.get(version).values()) {
                for (Integer port : address.ports.keySet()) {
                    int priority = version + address.ports.get(port);
                    res.add(new ConnectionInfo(index++, priority, address.host, port));
                }
            }
        }


        return res.toArray(new ConnectionInfo[res.size()]);
    }

    private synchronized void writeKnownSalts(int dcId, KnownSalt[] salts) {
        TLKey key = findKey(dcId);
        key.getSalts().clear();
        for (KnownSalt salt : salts) {
            key.getSalts().add(new TLLastKnownSalt(salt.getValidSince(), salt.getValidUntil(), salt.getSalt()));
        }
        this.write();
    }

    private synchronized KnownSalt[] readKnownSalts(int dcId) {
        TLKey key = findKey(dcId);
        KnownSalt[] salts = new KnownSalt[key.getSalts().size()];
        for (int i = 0; i < salts.length; i++) {
            TLLastKnownSalt sourceSalt = key.getSalts().get(i);
            salts[i] = new KnownSalt(sourceSalt.getValidSince(), sourceSalt.getValidUntil(), sourceSalt.getSalt());
        }
        return salts;
    }

    @Override
    public synchronized AbsMTProtoState getMtProtoState(final int dcId) {
        return new AbsMTProtoState() {

            private KnownSalt[] knownSalts = null;

            @Override
            public byte[] getAuthKey() {
                return MemoryApiState.this.getAuthKey(dcId);
            }

            @Override
            public ConnectionInfo[] getAvailableConnections() {
                return MemoryApiState.this.getAvailableConnections(dcId);
            }

            @Override
            public KnownSalt[] readKnownSalts() {
                if (knownSalts == null) {
                    knownSalts = MemoryApiState.this.readKnownSalts(dcId);
                }
                return knownSalts;
            }

            @Override
            protected void writeKnownSalts(KnownSalt[] salts) {
                MemoryApiState.this.writeKnownSalts(dcId, salts);
                knownSalts = null;
            }
        };
    }

    @Override
    public synchronized void resetAuth() {
        this.getObject().getKeys().forEach((key) -> {
            key.setAuthorised(false);
        });
        this.getObject().setAuthorized(false);
        this.getObject().setUid(0);
        this.write();
    }

    @Override
    public synchronized void reset() {
        this.getObject().getKeys().clear();
        this.getObject().setAuthorized(false);
        this.getObject().setUid(0);
        this.write();
    }

    @Override
    public int getUserId() {
        return this.getObject().getUid();
    }

}