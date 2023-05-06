package github.middlewaremagic.redismagic.command;

import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.core.impl.RedisCoreImpl;
import github.middlewaremagic.redismagic.utils.TRACEID;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    static Map<String, Supplier<Command>> map = new HashMap<>();

    static {
        for (CommandType each : CommandType.values()) {
            map.put(each.name(), each.getSupplier());
        }
    }

    public static Command from(List<String> commandList, RedisCore redisCore) {
        String commandName = commandList.get(0);
        Supplier<Command> supplier = map.get(commandName);
        if (supplier == null) {
            LOGGER.debug("traceId:" + TRACEID.currentTraceId() + " 不支持的命令：" + commandName);
            System.out.println("不支持的命令：" + commandName);
            return null;
        } else {
            try {
                Command command = supplier.get();
                command.setContent(commandList);
                command.handle(redisCore);
                return command;
            } catch (Throwable e) {
                LOGGER.debug("traceId:" + TRACEID.currentTraceId() + " 不支持的命令：{},数据读取异常" + commandName);
                e.printStackTrace();
                return null;
            }
        }
    }

//    public static Command from(SimpleString string) {
//        String commandName = string.getContent().toLowerCase();
//        Supplier<Command> supplier = map.get(commandName);
//        if (supplier == null) {
//            LOGGER.debug("traceId:" + TRACEID.currentTraceId() + " 不支持的命令：" + commandName);
//            System.out.println("不支持的命令：" + commandName);
//            return null;
//        } else {
//            try {
//                return supplier.get();
//            } catch (Throwable e) {
//                LOGGER.debug("traceId:" + TRACEID.currentTraceId() + " 不支持的命令：{},数据读取异常" + commandName);
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
}
