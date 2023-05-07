package github.middlewaremagic.redismagic.datastruct.impl;

import github.middlewaremagic.redismagic.datastruct.BytesWrapper;
import github.middlewaremagic.redismagic.datastruct.RedisData;

/**
 * @author gaoxiang
 */
public class RedisString implements RedisData {

    private BytesWrapper value;

    public RedisString(BytesWrapper value) {
        this.value = value;
    }

    public RedisString() {

    }

    public BytesWrapper getValue() {
        return value;
    }

    public void setValue(BytesWrapper value) {
        this.value = value;
    }
}
