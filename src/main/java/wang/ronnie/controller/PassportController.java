package wang.ronnie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.exception.SystemException;
import wang.ronnie.global.ErrorCode;
import wang.ronnie.global.JsonResult;
import wang.ronnie.service.PassportService;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by ronniewang on 16/7/12.
 */
@Controller
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportService passportService;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String loginName, String password) {

        if (isEmpty(loginName) || isEmpty(password)) {
            throw new SystemException(ErrorCode.Global.PARAM_ERROR);
        }

        passportService.login(loginName, password);
        return JsonResult.success("success");
    }

    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(UserEntity user) {

        if (isEmpty(user.getMobilePhone())
                || !user.getMobilePhone().matches("\\d{11}")
                || isEmpty(user.getPassword())
                || isEmpty(user.getEmail())
                || !user.getEmail().contains("@")) {
            throw new SystemException(ErrorCode.Global.PARAM_ERROR);
        }

        UserEntity registeredUser = passportService.register(user);
        return JsonResult.success(registeredUser);
    }
}
