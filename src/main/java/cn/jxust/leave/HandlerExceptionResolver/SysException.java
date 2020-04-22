package cn.jxust.leave.HandlerExceptionResolver;

/**
 * 如果抛出此异常，系统会以json格式向前台返回异常信息
 * 使用方式： throw new CustomException("这里填入异常信息，会发送到前台");
 * @author tyeerth
 * @date 2020/2/3 - 9:18
 */
public class SysException extends  Exception {
    //异常信息
    public String message;
    public SysException (String message) {
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
