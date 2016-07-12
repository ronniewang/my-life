package wang.ronnie.service;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.db.repository.UserRepository;
import wang.ronnie.exception.SystemException;
import wang.ronnie.global.ErrorCode;

/**
 * Created by ronniewang on 16/7/12.
 */
@Service
public class PassportService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity login(String username, String password) {

        UserEntity user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username);
        } else {
            user = userRepository.findByMobilePhone(username);
        }
        if (Md5Crypt.md5Crypt(password.getBytes()).equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public UserEntity register(UserEntity user) {

        if (userRepository.findByMobilePhone(user.getMobilePhone()) == null) {
            user.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes()));
            return userRepository.save(user);
        }
        throw new SystemException(ErrorCode.Register.USER_HAS_REGISTERED);
    }
}
