package cn.jxust.leave.HandlerExceptionResolver;

/**
 * @ClassName: ReturnViewException
 * @Description: 此异常类型用来向前台返回页面
 * @author tyeerth
 * @date 2020/2/3 - 9:22
 */
public class ReturnViewException extends Exception {
    //异常信息
    public String message;

    public ReturnViewException (String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
