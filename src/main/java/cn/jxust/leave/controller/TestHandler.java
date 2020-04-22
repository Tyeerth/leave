package cn.jxust.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tyeerth
 * @date 2020/4/19 - 8:05
 */
@Controller
public class TestHandler {

    @RequestMapping("/test")
    @ResponseBody
    public  String testHanderl(){
        return "success";
    }

    @RequestMapping("/index")
    public ModelAndView toIndex(){
        return new ModelAndView("index");
    }
}
