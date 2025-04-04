package com.HexBankAssign.exception;

public class OverDraftLimitExcededException extends Exception {
    public OverDraftLimitExcededException(String message) {
        super(message);
    }
}
