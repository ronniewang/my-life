package wang.ronnie.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ronnie.global.JsonResult;
import wang.ronnie.global.Log;

@ControllerAdvice
public class ControllerAdviceTest {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected JsonResult handleException(Exception e) {

        Log.ex(e);
        String[] messages = e.getMessage().split("\\|");
        return JsonResult.error(Integer.parseInt(messages[0]), messages[1]);
    }
}
