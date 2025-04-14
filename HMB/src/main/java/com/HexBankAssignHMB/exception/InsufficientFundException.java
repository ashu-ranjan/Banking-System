// TASK 12.1

package com.HexBankAssignHMB.exception;

public class InsufficientFundException extends Exception {
    public InsufficientFundException(String message) {
        super(message);}

    public InsufficientFundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientFundException(Throwable cause) {
        super(cause);
    }

    public InsufficientFundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsufficientFundException() {
    }
}
