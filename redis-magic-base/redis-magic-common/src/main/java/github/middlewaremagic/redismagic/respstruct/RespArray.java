package github.middlewaremagic.redismagic.respstruct;

public class RespArray implements Resp {

    Resp[] array;

    public RespArray(Resp[] array)
    {
        this.array = array;
    }

    public Resp[] getArray()
    {
        return array;
    }
}
