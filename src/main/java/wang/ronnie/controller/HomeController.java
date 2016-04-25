package wang.ronnie.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wang.ronnie.db.entity.MyLifeEventEntity;
import wang.ronnie.global.JsonResult;
import wang.ronnie.service.EventTagService;
import wang.ronnie.service.MyLifeEventService;

import javax.servlet.http.HttpSession;

/**
 * @author ronnie
 */
@Controller
public class HomeController {

    @Autowired
    private MyLifeEventService myLifeEventService;

    @Autowired
    private EventTagService eventTagService;

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/timeLine")
    public String timeLine() {

        return "time_line";
    }

    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@RequestParam String entity) {

        MyLifeEventEntity lifeEventEntity = JSON.parseObject(entity, MyLifeEventEntity.class);
        try {
            myLifeEventService.add(lifeEventEntity);
        } catch (IllegalArgumentException e) {
            return new JsonResult(e.getMessage());
        }
        return JsonResult.SUCCESS;
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(required = false) Integer error) {

        ModelAndView mav = new ModelAndView("login");
        if (error != null) {
            if (error == 1) {
                mav.addObject("error", "Maybe invalid combination of username and password");
            }
        }
        return mav;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {

        httpSession.invalidate();
        return "redirect:/login";
    }
}
