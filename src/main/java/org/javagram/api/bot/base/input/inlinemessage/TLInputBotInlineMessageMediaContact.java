package org.javagram.api.bot.base.input.inlinemessage;

import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A contact
 */
public class TLInputBotInlineMessageMediaContact extends TLAbsInputBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa6edbffd;

    private static final int FLAG_REPLY_MARKUP  = 0x00000004; // 2

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String vCard;
    private TLAbsReplyMarkup replyMarkup;

    public TLInputBotInlineMessageMediaContact() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getvCard() {
        return vCard;
    }

    public void setvCard(String vCard) {
        this.vCard = vCard;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLString(this.firstName, stream);
        StreamingUtils.writeTLString(this.lastName, stream);
        StreamingUtils.writeTLString(this.vCard, stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.firstName = StreamingUtils.readTLString(stream);
        this.lastName = StreamingUtils.readTLString(stream);
        this.vCard = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "inputBotInlineMessageMediaContact#a6edbffd";
    }

}
