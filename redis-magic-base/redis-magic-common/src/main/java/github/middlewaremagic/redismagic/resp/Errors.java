package github.middlewaremagic.redismagic.resp;/*
 * ClassName: Errors
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public class Errors implements Resp {
    String content;

    public Errors(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

