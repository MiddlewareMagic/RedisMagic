package github.middlewaremagic.redismagic;/*
 * ClassName: RedisServer
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public interface RedisServer {
    void start();

    void close();

    RedisCore getRedisCore();
}
