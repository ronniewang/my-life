package wang.ronnie.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.global.JsonResult;
import wang.ronnie.global.TokenHolder;
import wang.ronnie.model.Token;
import wang.ronnie.passport.service.UserService;
import wang.ronnie.service.PassportService;

/**
 * Created by ronniewang on 16/7/12.
 */
@Controller
@RequestMapping("/passport/api")
public class PassportApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private PassportService passportService;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password) {

        // TODO: 16/7/12 param validate
        String token = Md5Crypt.md5Crypt((username + password + (System.currentTimeMillis() + "")).getBytes());
        UserEntity user = passportService.login(username, password);
        TokenHolder.put(user.getId(), new Token(token, System.currentTimeMillis()));
        JSONObject result = new JSONObject();
        result.put("token", token);
        return JsonResult.success(token);
    }

    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(UserEntity user) {

        // TODO: 16/7/12 param validate
        UserEntity registeredUser = passportService.register(user);
        return JsonResult.success(registeredUser);
    }
}
