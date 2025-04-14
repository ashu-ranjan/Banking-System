// TASK 12.3

package com.HexBankAssignHMB.exception;

public class OverDraftLimitExcededException extends Exception {
    public OverDraftLimitExcededException(String message) {
        super(message);
    }

    public OverDraftLimitExcededException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverDraftLimitExcededException(Throwable cause) {
        super(cause);
    }

    public OverDraftLimitExcededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OverDraftLimitExcededException() {
    }
}
