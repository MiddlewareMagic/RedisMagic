package github.middlewaremagic.redismagic.datatype.impl;/*
 * ClassName: ErrorsRESP
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

/**
 * 错误数据（Errors）类型，以-（减号字符）开头，用于返回错误消息给客户端，格式类似于简单字符串类型
 * 用于发生错误时，返回消息给调用方，比如，使用了数据类型不支持的操作，错误消息的格式如下："-Error message\r\n"
 */
public class ErrorsRESP extends SingleRESP {

    @Override
    public String startFlag() {
        return "-";
    }

}
