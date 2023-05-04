package github.middlewaremagic.redismagic;

import com.sun.tools.jdeprscan.scan.Scan;
import github.middlewaremagic.redismagic.api.ICache;
import github.middlewaremagic.redismagic.bs.CacheBs;
import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;
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

    RedisMagicApplication main;

    Thread mainThread;

    public RedisMagicApplication getInstance() {
        if(main == null) {
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
        mainThread.start();
    }

    class MainThread implements Runnable {

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);

            while(scanner.hasNext()) {
                String order = scanner.nextLine();

            }
        }

        private String parseCommand(String order) {
            // 通过日志系统输出
            return null;
        }
    }


}
