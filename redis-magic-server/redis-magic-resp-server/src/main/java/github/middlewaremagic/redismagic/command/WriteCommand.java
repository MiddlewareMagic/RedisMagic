package github.middlewaremagic.redismagic.command;

import github.middlewaremagic.redismagic.core.RedisCore;

/**
 * @author lilan
 */
public interface WriteCommand extends Command {
    /**
     * for aof
     *
     * @param redisCore
     */
    void handle(RedisCore redisCore);

}
