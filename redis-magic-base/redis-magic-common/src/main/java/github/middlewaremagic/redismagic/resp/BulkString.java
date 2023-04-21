package github.middlewaremagic.redismagic.resp;/*
 * ClassName: BulkString
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

import github.middlewaremagic.redismagic.datatype.BytesWrapper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BulkString implements Resp {
    public static final BulkString NullBulkString = new BulkString(null);
    static final Charset CHARSET = StandardCharsets.UTF_8;
    BytesWrapper content;

    public BulkString(BytesWrapper content) {
        this.content = content;
    }

    public BytesWrapper getContent() {
        return content;
    }
}
