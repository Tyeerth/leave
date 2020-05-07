package cn.jxust.leave.exceptionResolver;

import cn.jxust.leave.constant.LoginConsts;
import cn.jxust.leave.utils.ResultEntity;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理类
 * @author tyeerth
 * @date 2020/5/2 - 8:47
 */
// @ControllerAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
@ResponseBody
public class ExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-add";

        return commonResolve(viewName, exception, request, response);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "/index";
        logger.info("登入异常处理。。"+exception);
        return commonResolve(viewName, exception, request, response);
    }

    /**
     *@ExceptionHandler将一个具体的异常类型和一个方法关联起来
     * @param viewName
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolve(
            // 异常处理完成后要去的页面
            String viewName,
            // 实际捕获到的异常类型
            Exception exception,
            // 当前请求对象
            HttpServletRequest request,
            // 当前响应对象
            HttpServletResponse response) throws IOException {
        // 1.判断当前请求类型
        boolean judgeResult = judgeRequestType(request);
        // 2.如果是Ajax请求
        if (judgeResult) {
            // 3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            // 4.创建Gson对象
            Gson gson = new Gson();
            // 5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);
            // 6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);
            // 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将Exception对象存入模型
        modelAndView.addObject(LoginConsts.ATTR_NAME_EXCEPTION, exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回modelAndView对象
        return modelAndView;
    }
    /**
     * 判断当前请求是否为Ajax请求
     * @param request 请求对象
     * @return
     * 		true：当前请求是Ajax请求
     * 		false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))

                ||

                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }
}
