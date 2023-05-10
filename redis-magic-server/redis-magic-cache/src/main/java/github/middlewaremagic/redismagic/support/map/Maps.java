package github.middlewaremagic.redismagic.support.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: `redisRewrite
 * @description: 自定义 map 实现策略
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-29 11:00
 **/
public final class Maps {

    private Maps(){}

    public static <K,V> Map<K,V> hashMap() {
        return new HashMap<>();
    }
}

