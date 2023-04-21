package github.middlewaremagic.redismagic.command.impl.base;/*
 * ClassName: Rpush
 * Description:
 * @Author: zjh
 * @Create: 2023/4/20
 */

import github.middlewaremagic.redismagic.command.CommandType;
import github.middlewaremagic.redismagic.datatype.RedisList;

public class Rpush extends Push {

    public Rpush() {
        super(RedisList::rpush);
    }

    @Override
    public CommandType type() {
        return CommandType.rpush;
    }
}