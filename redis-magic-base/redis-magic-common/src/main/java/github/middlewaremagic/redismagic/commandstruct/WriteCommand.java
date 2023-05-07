package github.middlewaremagic.redismagic.commandstruct;

import github.middlewaremagic.redismagic.api.ICache;
/**
 * @author lilan
 */
public interface WriteCommand extends Command {
    // TODO 如果要实现主从复制, 还是要通过 netty 来做, 少不了进行修改
    /**
     * for aof
     *
     * @param redisCore
     */
    void handle(ICache redisCore);

}
