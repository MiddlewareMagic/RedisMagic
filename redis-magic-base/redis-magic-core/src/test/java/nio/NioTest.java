package nio;

import java.nio.IntBuffer;
/**
 * @program: `redisRewrite
 * @description: nio 链接 测试类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-21 13:39
 **/
public class NioTest {

    public static void main(String[] args) {

        //举例说明 Buffer 的使用(简单说明)
        //创建一个 Buffer，大小为 5，即可以存放 5 个 int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //如何从 buffer 读取数据
        //将 buffer 转换，读写切换(!!!)
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
