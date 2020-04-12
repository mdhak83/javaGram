package org.javagram.client.structure;

import org.javagram.utils.BotLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import org.javagram.utils.StreamingUtils;

class SafeFileWriter {

    private static final String LOGTAG = "[SafeFileWriter]";

    private final String fileName;

    public SafeFileWriter(String fileName) {
        this.fileName = fileName;
    }

    private File getFile() {
        return new File(this.fileName);
    }

    public synchronized void saveData(byte[] data) {
        File file = this.getFile();
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            StreamingUtils.writeInt(data.length, os);
            StreamingUtils.writeByteArray(data, os);
            CRC32 crc32 = new CRC32();
            crc32.update(data);
            StreamingUtils.writeLong(crc32.getValue(), os);
            os.flush();
            os.getFD().sync();
            os.close();
            os = null;
            file.renameTo(getFile());
        } catch (IOException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
    }

    public synchronized byte[] loadData() {
        File file = this.getFile();
        if (!file.exists())
            return null;

        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            int len = StreamingUtils.readInt(is);
            byte[] res = StreamingUtils.readBytes(len, is);
            CRC32 crc32 = new CRC32();
            crc32.update(res);
            long crc = StreamingUtils.readLong(is);
            if (crc32.getValue() != crc) {
                BotLogger.error(LOGTAG, "CRC32 error in file '" + file.getAbsolutePath());
                return null;
            }
            return res;
        } catch (IOException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
        return null;
    }

}