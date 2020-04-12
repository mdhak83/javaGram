/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.javagram.api.sendmessage.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL send message upload document action.
 * @author Ruben Bermudez
 * @version 2.0
 * TLSendMessageUploadDocumentAction
 * @since 12/NOV/2014
 */
public class TLSendMessageUploadDocumentAction extends TLAbsSendMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaa0cd9e4;

    private int progress;

    public TLSendMessageUploadDocumentAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public int getProgress() {
        return this.progress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.progress, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.progress = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "sendMessageUploadDocumentAction#aa0cd9e4";
    }

}
