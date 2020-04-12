/**
 * Abstract class to represent contact information
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
package org.javagram.api.contact.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent information of a blocked contact
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public class TLContactBlocked extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x561bc879;

    private int userId; ///< User id
    private int date; ///< Block date

    public TLContactBlocked() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "contactBlocked#561bc879";
    }

}