package com.test.exception;

/**
 * Created by ronniewang on 16/7/12.
 */
public class SystemException extends RuntimeException {

    public SystemException(int errorCode) {

        super(errorCode + "");
    }
}
