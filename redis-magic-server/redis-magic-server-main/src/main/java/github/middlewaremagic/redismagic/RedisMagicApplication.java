package github.middlewaremagic.redismagic;

import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.bs.CacheBs;
import github.middlewaremagic.redismagic.command.CommandFactory;
import github.middlewaremagic.redismagic.command.CommandHandler;
import github.middlewaremagic.redismagic.core.RedisCore;
import github.middlewaremagic.redismagic.core.impl.RedisCoreImpl;
import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;
import github.middlewaremagic.redismagic.parser.CommandParser;

import java.util.Scanner;

/**
 * @program: `redisRewrite
 * @description: redis 服务端 启动类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-03 20:51
 **/
public class RedisMagicApplication {

    ICache<BytesWrapper, RedisData> iCache;

    CommandParser commandParser;

    private static RedisMagicApplication main;

    private final RedisCore redisCore = new RedisCoreImpl();

    Thread mainThread;

    public static RedisMagicApplication getInstance() {
        if (main == null) {
            main = new RedisMagicApplication();
        }
        return main;
    }

    private RedisMagicApplication() {
        // 单例限制只会启动一个主线程
        iCache = CacheBs.<BytesWrapper, RedisData>newInstance().build();
        commandParser = new CommandParser();
        mainThread = new Thread(new MainThread());

        start();
    }

    public void start() {
        mainThread.run();
    }

    class MainThread implements Runnable {

        @Override
        public void run() {
            Scanner scan = new Scanner(System.in);
            CommandHandler commandHandler = new CommandHandler(redisCore);
            while (!scan.hasNext("#")) {
                String longCommands = scan.nextLine();
                String resp = commandParser.parse(longCommands);
                System.out.println(commandParser.parseResp(resp));
                CommandFactory.from(commandParser.parseResp(resp), commandHandler.getRedisCore());

            }
            scan.close();
            System.out.println("over");

        }

        private String parseCommand(String order) {
            // 通过日志系统输出
            return null;
        }
    }


}
