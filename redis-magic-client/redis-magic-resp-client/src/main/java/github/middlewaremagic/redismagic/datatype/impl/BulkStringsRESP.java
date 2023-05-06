package github.middlewaremagic.redismagic.datatype.impl;
/*
 * ClassName: BulkStringsRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import github.middlewaremagic.redismagic.parser.CommandList;
import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.utils.StringUtil;
import github.middlewaremagic.redismagic.utils.TruncateString;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量字符串（Bulk Strings）类型，用于表示二进制安全的字符串，最大长度支持512MB
 * 1. 以$开头，紧接着是一个用于标识包含传输字符串字节个数的数字，由CRLF终止，
 * 2. 接着是待传输的字符串数据
 * 3. 接着是最终的CRLF
 * 比如：字符串"foobar"编码后，格式如下："$6\r\nfoobar\r\n"
 */
public class BulkStringsRESP implements RESP {

    @Override
    public List<String> parse(List<String> params) {

        ArrayList<String> strings = new CommandList(params.size());

        for (String param : params) {
            strings.add(startFlag() + param.length());
            strings.add(param);
        }

        return strings;
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {
        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        if (truncateString.getBefore().equals("$-1\r\n")) {
            results.add(null);
            return truncateString.getAfter();
        }

        TruncateString untilCRLF = StringUtil.untilCRLF(truncateString.getAfter());
        results.add(untilCRLF.getBefore().replaceFirst(CRLF, ""));

        return untilCRLF.getAfter();
    }


    @Override
    public String startFlag() {
        return "$";
    }
}
