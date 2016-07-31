package wang.ronnie.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.ronnie.global.JsonResult;
import wang.ronnie.global.Log;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected JsonResult handleException(Exception e) {

        Log.ex(e);
        return JsonResult.error(Integer.parseInt(e.getMessage()));
    }
}
