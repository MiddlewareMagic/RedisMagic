package github.middlewaremagic.redismagic.model;

import java.util.Arrays;

/**
 * @program: `redisRewrite
 * @description: AOF 持久化明细
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 14:46
 */
public class PersistAofEntry {

    /**
     * 参数信息
     * @since 0.0.10
     */
    private Object[] params;

    /**
     * 方法名称
     * @since 0.0.10
     */
    private String methodName;

    /**
     * 新建对象实例
     * @return this
     * @since 0.0.10
     */
    public static PersistAofEntry newInstance() {
        return new PersistAofEntry();
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PersistAofEntry{" +
                "params=" + Arrays.toString(params) +
                ", methodName='" + methodName + '\'' +
                '}';
    }

}
