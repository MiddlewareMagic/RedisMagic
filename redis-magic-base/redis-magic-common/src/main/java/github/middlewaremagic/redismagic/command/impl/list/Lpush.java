package github.middlewaremagic.redismagic.command.impl.list;/*
 * ClassName: Lpush
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.RedisList;
import github.middlewaremagic.redismagic.command.impl.base.Push;

public class Lpush extends Push {

    public Lpush() {
        super(RedisList::lpush);
    }

    @Override
    public CommandType type() {
        return CommandType.lpush;
    }
}
