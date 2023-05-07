package github.middlewaremagic.redismagic.commandstruct.impl.list;

import github.middlewaremagic.redismagic.commandstruct.CommandType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lpush extends Push {
    public Lpush() {
        super(RedisList::lpush);
    }

    @Override
    public CommandType type() {
        return CommandType.lpush;
    }
}
