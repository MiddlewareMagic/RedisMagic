package github.middlewaremagic.redismagic.commandstruct.impl.list;

import github.middlewaremagic.redismagic.commandstruct.CommandType;
import github.middlewaremagic.redismagic.commandstruct.impl.AbstractPush;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rpush extends AbstractPush {

    public Rpush() {
        super(RedisList::rpush);
    }

    @Override
    public CommandType type() {
        return CommandType.rpush;
    }
}
