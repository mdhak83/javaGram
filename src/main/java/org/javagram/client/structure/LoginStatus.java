package org.javagram.client.structure;

public enum LoginStatus {
    CODESENT,
    ALREADYLOGGED,
    ERRORSENDINGCODE,
    UNEXPECTEDERROR,
    INVALIDPHONENUMBER,
    BOTLOGIN,
    BOTLOGINERROR
}
