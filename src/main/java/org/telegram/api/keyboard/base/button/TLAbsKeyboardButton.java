package org.telegram.api.keyboard.base.button;

import org.telegram.api._primitives.TLObject;

/**
 * KeyboardButton type.
 */
public abstract class TLAbsKeyboardButton extends TLObject {

    /**
     * Button text
     */
    protected String text;

    public TLAbsKeyboardButton() {
        super();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}