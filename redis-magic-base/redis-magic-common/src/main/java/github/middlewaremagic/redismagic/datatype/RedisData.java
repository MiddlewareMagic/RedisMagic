package github.middlewaremagic.redismagic.datatype;/*
 * ClassName: RedisData
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public interface RedisData {
    long timeout();

    void setTimeout(long timeout);
}

