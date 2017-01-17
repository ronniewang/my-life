package com.test.service;

import com.test.model.Token;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.db.entity.UserEntity;
import com.test.db.repository.UserRepository;
import com.test.exception.SystemException;
import com.test.global.Constants;
import com.test.global.ErrorCode;
import com.test.global.TokenHolder;
import com.test.utils.AES;

/**
 * Created by ronniewang on 16/7/12.
 */
@Service
public class PassportService {

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
            throw new SystemException(ErrorCode.Login.USER_DOSE_NOT_EXIST);
        }
        if (Md5Crypt.md5Crypt(password.getBytes(), Constants.SALT).equals(user.getPassword())) {
            String token = AES.encrypt(user.getId() + "|" + password + "|" + (System.currentTimeMillis() + ""));
            TokenHolder.put(user.getId(), token);
            return;
        }
        throw new SystemException(ErrorCode.Login.PASSWORD_DOSE_NOT_MATCH);
    }

    public UserEntity register(UserEntity user) {

        if (userRepository.findByMobilePhone(user.getMobilePhone()) == null) {
            user.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes(), Constants.SALT));
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
