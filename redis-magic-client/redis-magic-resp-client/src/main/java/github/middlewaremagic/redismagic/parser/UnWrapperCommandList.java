package github.middlewaremagic.redismagic.parser;/*
 * ClassName: UnWrapperCommandList
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import java.util.ArrayList;
import java.util.Objects;

import static github.middlewaremagic.redismagic.datatype.RESP.CRLF;

public class UnWrapperCommandList extends ArrayList<String> {

    public UnWrapperCommandList(int size) {
        super(size);
    }

    public UnWrapperCommandList() {
        super();
    }

    @Override
    public boolean add(String s) {
        if (Objects.nonNull(s) && s.endsWith(CRLF)) {
            return super.add(s.replaceFirst(CRLF, ""));
        }
        return super.add(s);
    }

}

