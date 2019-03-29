package net.m56.ckkj.mobile.tourism.exception;

/**
 * 自定义异常
 * <p>
 * 系统中所有异常都转化为Exceptions
 * <p>
 * Created by 幻月 on 2015/9/17.
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

}
