package wang.ronnie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ronnie.global.ErrorCode;
import wang.ronnie.global.JsonResult;

/**
 * @author ronnie
 */
@Controller
public class ErrorController {

    @RequestMapping("/dealError")
    @ResponseBody
    public JsonResult token(Integer errorCode) {

        return JsonResult.error(errorCode);
    }

}
