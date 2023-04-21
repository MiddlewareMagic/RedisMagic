package github.middlewaremagic.redismagic.resp;/*
 * ClassName: RespInt
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public class RespInt implements Resp {
    int value;

    public RespInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
