package github.middlewaremagic.redismagic;

import github.middlewaremagic.redismagic.client.RedisClient;

/**
 * @program: `redisRewrite
 * @description: Resp 客户端启动类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-10 14:21
 **/
public class Main {

    public static void main(String[] args) throws Exception{
        String host = "127.0.0.1";
        Integer port = 15125;
        RedisClient client = new RedisClient(host, port);
        client.start();
    }
}
