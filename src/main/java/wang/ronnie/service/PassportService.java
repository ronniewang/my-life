package wang.ronnie.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.db.repository.UserRepository;
import wang.ronnie.exception.SystemException;
import wang.ronnie.global.ErrorCode;
import wang.ronnie.global.TokenHolder;
import wang.ronnie.model.Token;

/**
 * Created by ronniewang on 16/7/12.
 */
@Service
public class PassportService {

    @Autowired
    private UserRepository userRepository;

    public JSONObject login(String loginName, String password) {

        UserEntity user;
        if (loginName.contains("@")) {
            user = userRepository.findByEmail(loginName);
        } else {
            user = userRepository.findByMobilePhone(loginName);
        }
        if (user == null) {
            throw new SystemException(ErrorCode.Login.USER_DOSENT_EXIST);
        }
        if (Md5Crypt.md5Crypt(password.getBytes()).equals(user.getPassword())) {
            // TODO: 16/7/31 替换成aes加密
            String token = Md5Crypt.md5Crypt((loginName + password + (System.currentTimeMillis() + "")).getBytes());
            TokenHolder.put(user.getId(), new Token(token, System.currentTimeMillis()));
            JSONObject result = new JSONObject();
            return result;
        }
        throw new SystemException(ErrorCode.Login.PASSWORD_DOSENT_MATCH);
    }

    public UserEntity register(UserEntity user) {

        if (userRepository.findByMobilePhone(user.getMobilePhone()) == null) {
            user.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes()));
            return userRepository.save(user);
        }
        throw new SystemException(ErrorCode.Register.USER_HAS_REGISTERED);
    }

    public boolean checkToken(Long uid, String tokenStr) {

        Token token = TokenHolder.get(uid);
        if (token != null) {
            if (token.isValidate(tokenStr)) {
                token.setTimestamp(System.currentTimeMillis());
                return true;
            }
        }
        throw new SystemException(ErrorCode.Login.TOKEN_INVALID);
    }
}
