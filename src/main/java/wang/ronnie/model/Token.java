package wang.ronnie.model;

import wang.ronnie.exception.SystemException;
import wang.ronnie.global.ErrorCode;

import java.util.concurrent.TimeUnit;

/**
 * Created by ronniewang on 16/7/12.
 */
public class Token {

    private String value;

    private long timestamp;

    public Token(String value, long timestamp) {

        this.value = value;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(long timestamp) {

        this.timestamp = timestamp;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public boolean isValidate(String tokenStr) {

        if (tokenStr.equals(this.value)
                && timestamp + TimeUnit.MINUTES.toMillis(30) > System.currentTimeMillis()) {
            return true;
        }
        throw new SystemException(ErrorCode.Login.TOKEN_INVALID);
    }
}
