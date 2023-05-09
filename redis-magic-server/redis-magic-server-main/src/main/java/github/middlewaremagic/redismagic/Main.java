package github.middlewaremagic.redismagic;

import github.middlewaremagic.redismagic.impl.JRedisServerImpl;

/**
 * @program: `redisRewrite
 * @description:
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-08 18:32
 **/
public class Main {

    public static void main(String[] args) {
        JRedisServer jRedisServer = new JRedisServerImpl();
        jRedisServer.start();
    }
}
