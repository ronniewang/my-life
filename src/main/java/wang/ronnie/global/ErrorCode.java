package wang.ronnie.global;

import java.util.HashMap;

/**
 * Created by ronniewang on 16/7/12.
 */
public class ErrorCode {

    public static final HashMap<Integer, String> errorMap = new HashMap<>();

    static {

        errorMap.put(Global.PARAM_ERROR, "参数错误");

        errorMap.put(Register.USER_HAS_REGISTERED, "用户已经注册");

        errorMap.put(Login.USER_DOSENT_EXIST, "用户不存在");
        errorMap.put(Login.PASSWORD_DOSENT_MATCH, "密码不正确");
        errorMap.put(Login.TOKEN_INVALID, "无效的token");
    }

    public static String getErrorMessage(Integer errorCode) {

        return errorMap.get(errorCode);
    }

    public class Register {

        public static final int USER_HAS_REGISTERED = 20001;
    }

    public class Global {

        public static final int PARAM_ERROR = 10001;
    }

    public class Login {

        public static final int USER_DOSENT_EXIST = 30001;

        public static final int PASSWORD_DOSENT_MATCH = 30002;
        public static final int TOKEN_INVALID = 30003;
    }
}
