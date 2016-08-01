package wang.ronnie.service;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.db.repository.UserRepository;
import wang.ronnie.exception.SystemException;
import wang.ronnie.global.ErrorCode;
import wang.ronnie.global.TokenHolder;
import wang.ronnie.model.Token;
import wang.ronnie.utils.AES;

/**
 * Created by ronniewang on 16/7/12.
 */
@Service
public class PassportService {

    private static final String SALT = "ronnieBornIn1975!@#$";

    @Autowired
    private UserRepository userRepository;

    public void login(String loginName, String password) {

        UserEntity user;
        if (loginName.contains("@")) {
            user = userRepository.findByEmail(loginName);
        } else {
            user = userRepository.findByMobilePhone(loginName);
        }
        if (user == null) {
            throw new SystemException(ErrorCode.Login.USER_DOSENT_EXIST);
        }
        if (Md5Crypt.md5Crypt((password + SALT).getBytes()).equals(user.getPassword())) {
            String token = AES.encrypt(user.getId() + "|" + password + "|" + (System.currentTimeMillis() + ""));
            TokenHolder.put(user.getId(), token);
            return;
        }
        throw new SystemException(ErrorCode.Login.PASSWORD_DOSENT_MATCH);
    }

    public UserEntity register(UserEntity user) {

        if (userRepository.findByMobilePhone(user.getMobilePhone()) == null) {
            user.setPassword(Md5Crypt.md5Crypt((user.getPassword() + SALT).getBytes()));
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
        return false;
    }
}
