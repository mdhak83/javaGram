package org.telegram.client.structure;

import org.telegram.client.structure.storage.TLDcInfo;
import org.telegram.client.structure.storage.TLKey;
import org.telegram.client.structure.storage.TLLastKnownSalt;
import org.telegram.client.structure.storage.TLOldSession;
import org.telegram.client.structure.storage.TLStorage;
import org.telegram.utils.BotLogger;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.telegram.MyTLAppConfiguration;

public class TLPersistence<T extends TLObject> extends TLContext {

    private static final String LOGTAG = "KernelPersistence";

    private T object;
    private SafeFileWriter writer;

    public TLPersistence() {
        super();
    }

    public void build(MyTLAppConfiguration config, Class<T> destClass) {
        super.build(config);
        long start = System.currentTimeMillis();
        this.writer = new SafeFileWriter(this.config.getAuthFilename());
        byte[] data = this.writer.loadData();
        BotLogger.warning(LOGTAG, "Loaded state in " + (System.currentTimeMillis() - start) + " ms");
        this._deserialize(data, destClass);
    }
    
    @Override
    protected void registerClasses() {
        this.registerClass(TLDcInfo.CLASS_ID, TLDcInfo.class);
        this.registerClass(TLKey.CLASS_ID, TLKey.class);
        this.registerClass(TLLastKnownSalt.CLASS_ID, TLLastKnownSalt.class);
        this.registerClass(TLOldSession.CLASS_ID, TLOldSession.class);
        this.registerClass(TLStorage.CLASS_ID, TLStorage.class);
    }

    private void _deserialize(byte[] data, Class<T> destClass) {
        if (data != null) {
            try {
                ByteArrayInputStream stream = new ByteArrayInputStream(data);
                this.object = (T) this.deserializeMessage(stream);
            } catch (IOException e) {
                BotLogger.warning(LOGTAG, e);
            }
        }
        if (this.object == null) {
            try {
                this.object = destClass.newInstance();
            } catch (IllegalAccessException | InstantiationException ex) {
                throw new RuntimeException("Unable to instantiate '" + destClass.getSimpleName() + "'.", ex);
            }
        }
    }
    
    protected void write() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            this.object.serialize(stream);
            stream.close();
            this.writer.saveData(stream.toByteArray());
        } catch (IOException e) {
            BotLogger.warning(LOGTAG, e);
        }
    }

    public T getObject() {
        return object;
    }

}