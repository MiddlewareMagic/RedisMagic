package github.middlewaremagic.redismagic.exception;

/**
 * @program: `redisRewrite
 * @description: 缓存运行时异常
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 14:46
 **/
public class CacheRuntimeException extends RuntimeException{

    public CacheRuntimeException() {
    }

    public CacheRuntimeException(String message) {
        super(message);
    }

    public CacheRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheRuntimeException(Throwable cause) {
        super(cause);
    }

    public CacheRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
