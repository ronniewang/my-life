package com.test.controller;

import com.test.global.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.global.Log;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected JsonResult handleException(Exception e) {

        Log.ex(e);
        return JsonResult.error(Integer.parseInt(e.getMessage()));
    }
}
