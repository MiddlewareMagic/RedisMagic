package github.middlewaremagic.redismagic.utils;

import java.util.UUID;

public class TRACEID {
    private static final ThreadLocal<String> TRACEID = new ThreadLocal<String>();

    public static String newTraceId() {
        String traceId = UUID.randomUUID().toString();
        TRACEID.set(traceId);
        return traceId;
    }

    public static String currentTraceId() {
        String result = TRACEID.get();
        if (result == null) {
            return newTraceId();
        } else {
            return result;
        }
    }

    public static void bind(String traceId) {
        TRACEID.set(traceId);
    }
}

