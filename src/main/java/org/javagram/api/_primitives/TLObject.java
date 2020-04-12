package org.javagram.api._primitives;

import org.javagram.utils.DeserializeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.javagram.utils.StreamingUtils;
import static org.javagram.utils.StreamingUtils.readInt;

/**
 * Basic class for all tl-objects. Contains methods for serializing and deserializing object.
 * Each tl-object has class id for using in object header for identifying object class for deserialization.
 * This number might be unique and often equals to crc32 of tl-record of tl-constructor.
 * <br>
 * It is recommended to declare public static final CLASS_ID with tl class id and
 * return this in getClassId and passing it to TLContext.registerClass method during class registration
 */
public abstract class TLObject implements Serializable {

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    protected int flags;
    
    /**
     * Getting TL Class identification
     *
     * @return id of class
     */
    public abstract int getClassId();

    /**
     * Custom toString method
     *
     * @return String that represent this class
     */
    @Override
    public abstract String toString();

    protected boolean isFlagSet(int flag) {
        return (this.flags & flag) != 0;
    }

    protected void setFlag(int flag, boolean enabled) {
        if (enabled) {
            this.flags |= flag;
        } else {
            this.flags &= ~flag;
        }
    }
    
    public int getFlags() {
        return this.flags;
    }

    /**
     * Serializing object to byte array
     *
     * @return serialized object with header
     * @throws IOException an IOException
     */
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.serialize(stream);
        return stream.toByteArray();
    }

    /**
     * Serializing object to stream
     *
     * @param stream destination stream
     * @throws IOException an IOException
     */
    public void serialize(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(getClassId(), stream);
        this.serializeBody(stream);
    }

    /**
     * Deserializing object from stream and current TLContext
     *
     * @param stream  source stream
     * @param context tl context
     * @throws IOException an IOException
     */
    public void deserialize(InputStream stream, TLContext context) throws IOException {
        int classId = readInt(stream);
        if (classId != getClassId()) {
            throw new DeserializeException("Wrong class id. Founded:" + Integer.toHexString(classId) +
                    ", expected: " + Integer.toHexString(getClassId()));
        }
        deserializeBody(stream, context);
    }

    /**
     * Serializing object body to stream
     *
     * @param stream destination stream
     * @throws IOException an IOException
     */
    public void serializeBody(OutputStream stream) throws IOException {

    }

    /**
     * Deserializing object from stream and context
     *
     * @param stream  source stream
     * @param context tl context
     * @throws IOException an IOException
     */
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {

    }

}