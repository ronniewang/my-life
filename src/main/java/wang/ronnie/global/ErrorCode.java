package wang.ronnie.global;

import java.util.HashMap;

/**
 * Created by ronniewang on 16/7/12.
 */
public class ErrorCode {

    public static final HashMap<Integer, String> errorMap = new HashMap<>();

    static {

    }

    public class Register {

        public static final int USER_HAS_REGISTERED = 10001;
    }
}
