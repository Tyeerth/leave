package cn.jxust.leave.exceptionResolver;

import org.springframework.security.access.AccessDeniedException;

/**
 * 登录失败后抛出的异常
 * @author Lenovo
 *
 */
//public class LoginFailedException extends RuntimeException {
public class LoginFailedException extends RuntimeException {
	public LoginFailedException() {
	}

	public LoginFailedException(String message) {
		super(message);
	}

	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailedException(Throwable cause) {
		super(cause);
	}

	public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
