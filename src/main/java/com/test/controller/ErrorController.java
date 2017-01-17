package com.test.controller;

import com.test.global.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
