package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import org.javagram.api.file.base.encrypted.TLAbsEncryptedFile;
import org.javagram.api.file.base.input.encrypted.TLAbsInputEncryptedFile;

/**
 * Upload encrypted file and associate it to a secret chat
 * messages.uploadEncryptedFile#5057c497 peer:InputEncryptedChat file:InputEncryptedFile = EncryptedFile;
 */
public class TLRequestMessagesUploadEncrypted extends TLMethod<TLAbsEncryptedFile> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5057c497;

    /**
     * The secret chat to associate the file to
     */
    private TLInputEncryptedChat peer;

    /**
     * The file
     */
    private TLAbsInputEncryptedFile file;
    
    public TLRequestMessagesUploadEncrypted() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    public TLAbsInputEncryptedFile getFile() {
        return file;
    }

    public void setFile(TLAbsInputEncryptedFile file) {
        this.file = file;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputEncryptedFile.class);
    }

    @Override
    public TLAbsEncryptedFile deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsEncryptedFile) {
            return (TLAbsEncryptedFile) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.uploadEncryptedFile#5057c497";
    }

}