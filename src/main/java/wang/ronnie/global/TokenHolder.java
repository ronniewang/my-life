package wang.ronnie.global;

import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.model.Token;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ronniewang on 16/7/12.
 */
public class TokenHolder {

    private static ConcurrentMap<Long, Token> tokenMap = new ConcurrentHashMap<>();

    public static Token get(Long uid) {

        return tokenMap.get(uid);
    }

    public static void put(Long uid, String token) {

        tokenMap.put(uid, new Token(token, System.currentTimeMillis()));
    }

    public static UserEntity check(String token) {

        return null;
    }
}
