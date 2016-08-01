package wang.ronnie.global;

import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.model.Token;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ronniewang on 16/7/12.
 */
public class TokenHolder {

    private static ConcurrentMap<Token, UserEntity> tokenMap = new ConcurrentHashMap<>();

    public static UserEntity get(Token uid) {

        return tokenMap.get(uid);
    }

    public static void put(String token, UserEntity userEntity) {

        tokenMap.put(new Token(token, System.currentTimeMillis()), userEntity);
    }

    public static UserEntity check(String token) {

        return null;
    }
}
