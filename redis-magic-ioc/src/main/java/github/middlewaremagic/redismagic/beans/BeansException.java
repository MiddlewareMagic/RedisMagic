package github.middlewaremagic.redismagic.beans;

/*
 * ClassName: BeansException
 * Description:
 * @Author: zjh
 * @Create: 2023/5/11
 */

public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}