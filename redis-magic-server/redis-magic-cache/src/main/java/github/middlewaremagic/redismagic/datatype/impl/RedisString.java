package github.middlewaremagic.redismagic.datatype.impl;

import github.middlewaremagic.redismagic.datatype.BytesWrapper;
import github.middlewaremagic.redismagic.datatype.RedisData;

/**
 * @author lilan
 */
public class RedisString implements RedisData {

    private BytesWrapper value;

    public RedisString(BytesWrapper value){
        this.value = value;
    }
    public RedisString(){

    }
    public BytesWrapper getValue()
    {
        return value;
    }

    public void setValue(BytesWrapper value)
    {
        this.value = value;
    }
}
