package github.middlewaremagic.redismagic.resp;/*
 * ClassName: RespArray
 * Description:
 * @Author: zjh
 * @Create: 2023/4/21
 */

public class RespArray implements Resp {
    Resp[] array;

    public RespArray(Resp[] array) {
        this.array = array;
    }

    public Resp[] getArray() {
        return array;
    }
}

