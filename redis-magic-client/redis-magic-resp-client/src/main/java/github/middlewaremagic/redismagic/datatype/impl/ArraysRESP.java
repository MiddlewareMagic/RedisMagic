package github.middlewaremagic.redismagic.datatype.impl;
/*
 * ClassName: ArraysRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import github.middlewaremagic.redismagic.parser.CommandParser;
import github.middlewaremagic.redismagic.parser.CommandList;
import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.utils.StringUtil;
import github.middlewaremagic.redismagic.utils.TruncateString;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组（Arrays）类型，redis客户端请求命令均是存放在数组类型中，如果一个命令需要返回多条数据也可以使用数组类型
 * 一个数组类型不要求其数组元素为同一种类型，可以是混合类型，数组类型同样可以嵌套数组类型
 * 1. 以*开头，随后跟一个用于标识数组元素数量的实数，以CRLF结尾
 * 2. 跟随多个数组元素，数量与上面指定的数量相同，每个数组元素都是嵌套RESP中的一种数据类型的数据
 * 比如：存放"foo"和"bar"两个批量字符串的数组，编码后格式如下："*2\r\n$3\r\nfoo\r\n$3\r\nbar\r\n"
 */
public class ArraysRESP implements RESP {

    private final CommandParser commandParser;

    public ArraysRESP(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    @Override
    public List<String> parse(List<String> params) {

        ArrayList<String> strings = new CommandList(params.size());

        strings.add(startFlag() + params.size());
        strings.addAll(params);
        return strings;
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {

        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        int arrLen = Integer.parseInt(truncateString.getBefore().replaceFirst(CRLF, "").substring(1));
        String after = truncateString.getAfter();
        // null array
        if (arrLen == -1) {
            results.add(null);
        } else {
            for (int i = 0; i < arrLen; i++) {
                //递归解析
                after = this.commandParser.reverseParse(after, results);
            }
        }
        return after;
    }


    @Override
    public String startFlag() {
        return "*";
    }
}