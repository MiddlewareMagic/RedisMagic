package github.middlewaremagic.redismagic.util;

/*
 * ClassName: ClassUtils
 * Description:
 * @Author: zjh
 * @Create: 2023/5/12
 */
public class ClassUtils {
    public static ClassLoader getDefualtClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
