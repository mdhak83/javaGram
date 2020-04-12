package org.javagram.api.message.base.media;

import org.javagram.api.document.base.web.TLWebDocument;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invoice
 * messageMediaInvoice#84551347 flags:# shipping_address_requested:flags.1?true test:flags.3?true title:string description:string photo:flags.0?WebDocument receipt_msg_id:flags.2?int currency:string total_amount:long start_param:string = MessageMedia;
 */
public class TLMessageMediaInvoice extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84551347;

    private static final int FLAG_PHOTO                      = 0x00000001; // 0
    private static final int FLAG_SHIPPING_ADDRESS_REQUEST   = 0x00000002; // 1
    private static final int FLAG_RECEIPT_MSG_ID             = 0x00000004; // 2
    private static final int FLAG_TEST                       = 0x00000008; // 3

    /**
     * Product name, 1-32 characters
     */
    private String title;
    
    /**
     * Product description, 1-255 characters
     */
    private String description;
    
    /**
     * URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service. People like it better when they see what they are paying for.
     */
    private TLWebDocument photo;
    
    /**
     * Message ID of receipt: if set, clients should change the text of the first @see <a href="https://core.telegram.org/constructor/keyboardButtonBuy">keyboardButtonBuy</a> button always attached to the @see <a href="https://core.telegram.org/constructor/message">message</a> to a localized version of the word Receipt
     */
    private int receiptMsgId;
    
    /**
     * Three-letter ISO 4217 @see <a href="https://core.telegram.org/bots/payments#supported-currencies">currency</a> code
     */
    private String currency;
    
    /**
     * Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145.
     * See the exp parameter in @see <a href="https://core.telegram.org/bots/payments/currencies.json">currencies.json</a>, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    private Long totalAmount;
    
    /**
     * Unique bot deep-linking parameter that can be used to generate this invoice
     */
    private String startParam;

    public TLMessageMediaInvoice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isTest() {
        return (flags & FLAG_TEST) != 0;
    }

    public boolean hasShippingAddressRequested() {
        return (flags & FLAG_SHIPPING_ADDRESS_REQUEST) != 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasPhoto() {
        return this.isFlagSet(FLAG_PHOTO);
    }

    public TLWebDocument getPhoto() {
        return photo;
    }

    public void setPhoto(TLWebDocument photo) {
        this.photo = photo;
    }

    public boolean hasReceiptMsgId() {
        return this.isFlagSet(FLAG_RECEIPT_MSG_ID);
    }

    public int getReceiptMsgId() {
        return receiptMsgId;
    }

    public void setReceiptMsgId(int receiptMsgId) {
        this.receiptMsgId = receiptMsgId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStartParam() {
        return startParam;
    }

    public void setStartParam(String startParam) {
        this.startParam = startParam;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.description, stream);
        if (this.hasPhoto()) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if (this.hasReceiptMsgId()) {
            StreamingUtils.writeInt(this.receiptMsgId, stream);
        }
        StreamingUtils.writeTLString(this.currency, stream);
        StreamingUtils.writeLong(this.totalAmount, stream);
        StreamingUtils.writeTLString(this.startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.description = StreamingUtils.readTLString(stream);
        if (this.hasPhoto()) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLWebDocument.class);
        }
        if (this.hasReceiptMsgId()) {
            this.receiptMsgId = StreamingUtils.readInt(stream);
        }
        this.currency = StreamingUtils.readTLString(stream);
        this.totalAmount = StreamingUtils.readLong(stream);
        this.startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageMediaInvoice#84551347";
    }

}