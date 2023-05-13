package github.middlewaremagic.redismagic.core.io;

import java.io.IOException;
import java.io.InputStream;

/*
 * ClassName: Resource
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
