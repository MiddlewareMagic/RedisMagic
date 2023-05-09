package github.middlewaremagic.redismagic.client;

import lombok.Data;

/**
 * @program: `redisRewrite
 * @description: 链接的 Redis 服务相关信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-08 22:54
 **/
@Data
public class JedisServerStrap {

    String host;

    Integer port;
}
