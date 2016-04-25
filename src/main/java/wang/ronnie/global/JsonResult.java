package wang.ronnie.global;

/**
 * Created by ronnie on 2016/4/25.
 */
public class JsonResult {

    public static final JsonResult SUCCESS = new JsonResult(null, 0, "");

    public JsonResult(Object object, int errorCode, String errorMessage) {

        this.object = object;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public JsonResult() {

    }

    private Object object;

    private int errorCode;

    private String errorMessage;

    public Object getObject() {

        return object;
    }

    public void setObject(Object object) {

        this.object = object;
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
