package github.middlewaremagic.redismagic.datatype.impl;/*
 * ClassName: SingleRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.utils.StringUtil;
import github.middlewaremagic.redismagic.utils.TruncateString;

import java.util.List;

/**
 * 单个结果协议
 */
public abstract class SingleRESP implements RESP {

    @Override
    public List<String> parse(List<String> params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {
        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        results.add(truncateString.getBefore().substring(1));
        return truncateString.getAfter();
    }

}

