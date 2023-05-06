package github.middlewaremagic.redismagic.core;/*
 * ClassName: RedisCore
 * Description:
 * @Author: zjh
 * @Create: 2023/5/5
 */

import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

import java.util.List;
import java.util.Set;

public interface RedisCore {
    Set<BytesWrapper> keys();

    boolean exist(BytesWrapper key);

    void put(BytesWrapper key, RedisData redisData);

    RedisData get(BytesWrapper key);

    long remove(List<BytesWrapper> keys);

    void cleanAll();
}
