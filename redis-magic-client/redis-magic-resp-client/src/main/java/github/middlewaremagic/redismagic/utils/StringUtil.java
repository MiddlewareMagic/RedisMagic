package github.middlewaremagic.redismagic.utils;/*
 * ClassName: StringUtil
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import static github.middlewaremagic.redismagic.datatype.RESP.CRLF;

public class StringUtil {

    /**
     * 以CRLF为界将字符串分为两段
     *
     * @param raw raw string
     * @return {@link TruncateString}
     */
    public static TruncateString untilCRLF(String raw) {
        int indexOf = raw.indexOf(CRLF);
        String before = raw.substring(0, indexOf + CRLF.length());
        String after = raw.substring(indexOf + CRLF.length());
        return new TruncateString(before, after);
    }

}