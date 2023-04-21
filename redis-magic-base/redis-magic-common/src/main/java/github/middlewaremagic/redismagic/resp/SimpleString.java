package github.middlewaremagic.redismagic.resp;/*
 * ClassName: SimpleString
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public class SimpleString implements Resp {
    public static final SimpleString OK = new SimpleString("OK");
    private final String content;

    public SimpleString(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
