package github.middlewaremagic.redismagic.datatype.impl;/*
 * ClassName: SimpleStringsRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

/**
 * 简单字符串（Simple Strings）类型，以+（加号字符）开始，后跟一个不能包含CR或LF字符的字符串（不允许换行符），
 * 以CRLF结尾，用于以最小开销传输非二进制安全的字符串
 * 比如，许多命令回复OK作为操作成功的标识，格式如下："+OK\r\n"
 */
public class SimpleStringsRESP extends SingleRESP {

    @Override
    public String startFlag() {
        return "+";
    }

}
