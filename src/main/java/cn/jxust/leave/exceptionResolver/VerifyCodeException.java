package cn.jxust.leave.exceptionResolver;

import org.springframework.security.core.AuthenticationException;

/**
 * 声明一个验证码异常，用于抛出特定的验证码异常
 * @author tyeerth
 * @date 2020/5/4 - 10:00
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}