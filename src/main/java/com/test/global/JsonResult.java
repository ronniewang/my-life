package com.test.global;

/**
 * Created by ronnie on 2016/4/25.
 */
public class JsonResult {

    public static final JsonResult SUCCESS = new JsonResult(null, 0, "");

    private static final int DEFAULT_ERROR_CODE = 1;

    private Object result;

    private int errorCode;

    private String errorMessage;

    public JsonResult(Object result, int errorCode, String errorMessage) {

        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public JsonResult(String errorMessage) {

        this(null, DEFAULT_ERROR_CODE, errorMessage);
    }

    public JsonResult() {

    }

    public static JsonResult success(Object object) {

        return new JsonResult(object, 0, "");
    }

    public static JsonResult error(int errorCode) {

        return new JsonResult(null, errorCode, ErrorCode.getErrorMessage(errorCode));
    }

    public Object getResult() {

        return result;
    }

    public void setResult(Object result) {

        this.result = result;
    }

    public int getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(int errorCode) {

        this.errorCode = errorCode;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }
}
