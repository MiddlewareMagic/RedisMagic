package github.middlewaremagic.redismagic.constant.enums;

/**
 * @program: `redisRewrite
 * @description: 缓存移除类型
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 11:05
 **/
public enum CacheRemoveType {

    EXPIRE("expire", "过期"),
    EVICT("evict", "淘汰"),
    ;

    private final String code;

    private final String desc;


    CacheRemoveType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    @Override
    public String toString() {
        return "CacheRemoveType{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
