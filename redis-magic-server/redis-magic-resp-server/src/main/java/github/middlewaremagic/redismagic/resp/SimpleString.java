package github.middlewaremagic.redismagic.resp;

/**
 * @program: `redisRewrite
 * @description: 简单String对象, 处理特殊命令
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 19:51
 **/
public class SimpleString {
    public static final github.middlewaremagic.redismagic.respstruct.SimpleString OK = new github.middlewaremagic.redismagic.respstruct.SimpleString("OK");
    private final String content;

    public SimpleString(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

