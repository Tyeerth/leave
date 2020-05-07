package cn.jxust.leave.exceptionResolver;

import cn.jxust.leave.security.CustomizeAuthenticationFailureHandler;
import cn.jxust.leave.utils.ContextHolderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * @author tyeerth
 * @date 2020/5/4 - 10:03
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(VerifyCodeFilter.class);
    @Autowired
    private CustomizeAuthenticationFailureHandler loginFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals("/login")&&request.getMethod().equalsIgnoreCase("post")){
            try {
                validate(request);
            } catch (VerifyCodeException e) {
                loginFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);
    }
    /* 验证保存在session的验证码和表单提交的验证码是否一致 */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        String captcha = ServletRequestUtils.getStringParameter(request, "captcha");
//        String captcha = (String) ContextHolderUtils.getSession().getAttribute("captcha");
//        String code = (String) ContextHolderUtils.getSession().getAttribute("uuid");
        String code = (String) request.getSession().getAttribute(request.getParameter("uuid"));
        log.info("获取提交表单中的的code",captcha);
        log.info("获取保存的code",code);
        if(!code.equalsIgnoreCase(captcha)){
            throw new VerifyCodeException("验证码不正确！");
        }
        request.getSession().removeAttribute(request.getParameter("uuid"));
    }
}