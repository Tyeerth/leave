package cn.jxust.leave.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登入失败的处理
 *
 * @author tyeerth
 * @date 2020/5/2 - 13:47
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
        JSONObject jsonObject = new JSONObject() ;
        if (e instanceof AccountExpiredException) {
            //账号过期
            jsonObject.put("msg","账号过期");
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            jsonObject.put("msg","密码错误");
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            jsonObject.put("msg","密码过期");
        } else if (e instanceof DisabledException) {
            //账号不可用
            jsonObject.put("msg","账号不可用");
        } else if (e instanceof LockedException) {
            //账号锁定
            jsonObject.put("msg","账号锁定");
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            jsonObject.put("msg",e.getMessage());
            jsonObject.put("status","402");

        } else {
            //其他错误
            jsonObject.put("msg","验证码输入错误");
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(jsonObject.toJSONString());
    }
}
