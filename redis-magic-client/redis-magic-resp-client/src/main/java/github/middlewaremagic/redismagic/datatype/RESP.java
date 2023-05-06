package github.middlewaremagic.redismagic.datatype;
/*
 * ClassName: Resp
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import java.util.List;

/**
 * RESP协议支持5种数据类型：简单字符串（Simple Strings）、错误数据（Errors）、整数（Integers）、批量字符串（Bulk Strings）、数组（Arrays）
 * 客户端请求服务器时，会以批量数据类型的数组进行请求封装；服务端发送响应给客户端时，根据命令实现的不同，返回相应的数据类型。
 * 不同的数据类型根据请求/响应报文的第一个字节进行区分：
 * 1. 简单字符串以+开头
 * 2. 错误数据以-开头
 * 3. 整数以:开头
 * 4. 批量字符串以$开头
 * 5. 数组以*开头
 * RESP协议的不同部分使用"\r\n"（CRLF）进行分隔
 */
public interface RESP {

    /**
     * RESP CRLF
     */
    String CRLF = "\r\n";

    /**
     * 开始标识
     *
     * @return 开始标识
     */
    String startFlag();

    /**
     * 将命令解析为RESP格式
     *
     * @param params 参数
     * @return 解析后命令
     */
    List<String> parse(List<String> params);

    /**
     * 逆向解析RESP格式命令
     *
     * @param longCommands
     * @param results
     * @return
     */
    String reverseParse(String longCommands, List<String> results);

}
