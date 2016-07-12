package wang.ronnie.passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ronnie.db.repository.UserRepository;
import wang.ronnie.model.User;

/**
 * Created by ronniewang on 16/7/12.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String telOrEmail) {

        User user = new User();
        user.setId(1);
        return user;
    }
}
