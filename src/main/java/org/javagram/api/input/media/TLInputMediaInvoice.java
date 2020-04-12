package org.javagram.api.input.media;

import org.javagram.api.document.base.input.TLInputWebDocument;
import org.javagram.api.payment.base.TLInvoice;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.json.base.TLDataJSON;

public class TLInputMediaInvoice extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf4e096c3;

    private static final int FLAG_PHOTO = 0x00000001; // 0

    private String title;
    private String description;
    private TLInputWebDocument photo;
    private TLInvoice invoice;
    private TLBytes payload;
    private String provider;
    private TLDataJSON providerData;
    private String startParam;

    public TLInputMediaInvoice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLDataJSON getProviderData() {
        return providerData;
    }

    public void setProviderData(TLDataJSON providerData) {
        this.providerData = providerData;
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

    public TLInputWebDocument getPhoto() {
        return photo;
    }

    public void setPhoto(TLInputWebDocument photo) {
        if (photo == null) {
            flags &= ~FLAG_PHOTO;
        } else {
            flags |= FLAG_PHOTO;
        }
        this.photo = photo;
    }

    public TLInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(TLInvoice invoice) {
        this.invoice = invoice;
    }

    public TLBytes getPayload() {
        return payload;
    }

    public void setPayload(TLBytes payload) {
        this.payload = payload;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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
        if ((flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        StreamingUtils.writeTLObject(this.invoice, stream);
        StreamingUtils.writeTLBytes(this.payload, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLObject(this.providerData, stream);
        StreamingUtils.writeTLString(this.startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.description = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_PHOTO) != 0) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLInputWebDocument.class);
        }
        this.invoice = StreamingUtils.readTLObject(stream, context, TLInvoice.class);
        this.payload = StreamingUtils.readTLBytes(stream, context);
        this.provider = StreamingUtils.readTLString(stream);
        this.providerData = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
        this.startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputMediaInvoice#f4e096c3";
    }

}