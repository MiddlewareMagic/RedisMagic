package github.middlewaremagic.redismagic.core.io;

/*
 * ClassName: ResourceLoader
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
