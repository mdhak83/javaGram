package org.javagram.client.structure;

import org.javagram.client.structure.storage.TLDcInfo;
import org.javagram.client.structure.storage.TLKey;
import org.javagram.client.structure.storage.TLLastKnownSalt;
import org.javagram.client.structure.storage.TLOldSession;
import org.javagram.client.structure.storage.TLStorage;
import org.javagram.utils.BotLogger;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.javagram.MyTLAppConfiguration;

public class TLPersistence<T extends TLObject> extends TLContext {

    private static final String LOGTAG = "[KernelPersistence]";

    private T object;
    private SafeFileWriter writer;

    public TLPersistence() {
        super();
    }

    public void build(MyTLAppConfiguration config, Class<T> destClass) {
        super.build(config);
        long start = System.currentTimeMillis();
        this.writer = new SafeFileWriter(this.config.getTemporaryFolder().toString() + File.separator + this.config.getAuthFilename());
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