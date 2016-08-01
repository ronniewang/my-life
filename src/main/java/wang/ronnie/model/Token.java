package wang.ronnie.model;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (timestamp != token.timestamp) return false;
        return value != null ? value.equals(token.value) : token.value == null;

    }

    @Override
    public int hashCode() {

        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
