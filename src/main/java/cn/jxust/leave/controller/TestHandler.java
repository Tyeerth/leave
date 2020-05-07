package cn.jxust.leave.controller;

import cn.jxust.leave.exceptionResolver.LoginFailedException;
import cn.jxust.leave.pojo.Student;
import cn.jxust.leave.pojo.vo.ImgVO;
import cn.jxust.leave.utils.BaseResult;
import cn.jxust.leave.utils.ReturnCode;
import cn.jxust.leave.utils.UUIDUtil;
import cn.jxust.leave.utils.VerifyCodeUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author tyeerth
 * @date 2020/4/19 - 8:05
 */
@Controller
public class TestHandler {

    @RequestMapping("/test")
    @ResponseBody
    public  String testHanderl(HttpServletRequest request){
        SecurityContextImpl spring_security_context = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = spring_security_context.getAuthentication();
        System.out.println(authentication.getCredentials());
        return "success";
    }

    @RequestMapping("/index")
    public ModelAndView toIndex(){

        return new ModelAndView("index");
    }
    @RequestMapping("/index3")
    public ModelAndView toIndex3(){

        return new ModelAndView("index3");
    }

    @RequestMapping("/exception/{id}")
    @ResponseBody
    public String test(@PathVariable(name = "id") Integer id) throws LoginFailedException {

        if (id == 1) {
            throw new LoginFailedException("id不能为1");
        } else if (id == 2) {
            throw new LoginFailedException("id不能为2");
        }

        return "SUCCESS";
    }
    @GetMapping("/getCode")
    @ResponseBody
    public Object getCode(HttpServletRequest request) {
        /* 生成验证码字符串 */
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        String uuid = UUIDUtil.GeneratorUUIDOfSimple();
        request.getSession().setAttribute(uuid,verifyCode);
        int w = 111,h = 36;
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            VerifyCodeUtil.outputImage(w, h, stream, verifyCode);
            return new BaseResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage(),new ImgVO("data:image/gif;base64,"+ Base64Utils.encodeToString(stream.toByteArray()),uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
