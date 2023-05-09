package github.middlewaremagic.redismagic;

/**
 * @program: `redisRewrite
 * @description: java 版 redis服务器接口
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-07 22:50
 **/
public interface JRedisServer {

    void start();

    void close();

}
