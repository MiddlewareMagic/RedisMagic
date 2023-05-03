package github.middlewaremagic.redismagic.datatype.impl;/*
 * ClassName: IntegersRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

/**
 * 整数（Integers）类型，以:开头，以CRLF结尾，可以理解为表示整数的简单字符串类型，中间的字符串都由整数组成
 * 比如：整数 1000 编码后，格式如下：:1000\r\n
 */
public class IntegersRESP extends SingleRESP {

    @Override
    public String startFlag() {
        return ":";
    }


}

