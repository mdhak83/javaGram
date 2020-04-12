package org.telegram.client.structure;

public enum LoginStatus {
    CODESENT,
    ALREADYLOGGED,
    ERRORSENDINGCODE,
    UNEXPECTEDERROR,
    INVALIDPHONENUMBER,
    BOTLOGIN,
    BOTLOGINERROR
}
