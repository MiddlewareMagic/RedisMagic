package github.middlewaremagic.redismagic;/*
 * ClassName: RedisCore
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Set;

public interface RedisCore {
    Set<BytesWrapper> keys();

    void putClient(BytesWrapper connectionName, Channel channelContext);

    boolean exist(BytesWrapper key);

    void put(BytesWrapper key, RedisData redisData);

    RedisData get(BytesWrapper key);

    long remove(List<BytesWrapper> keys);

    void cleanAll();
}
