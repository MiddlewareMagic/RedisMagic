package github.middlewaremagic.redismagic.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端 channel
 * 
 * @author waylau.com
 * @date 2015-2-26
 */
public class ClientEchoHandler extends SimpleChannelInboundHandler<String> {

	JedisServerStrap jedisServerStrap;

	public ClientEchoHandler(JedisServerStrap jedisServerStrap) {
		this.jedisServerStrap = jedisServerStrap;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		// 结束时, 添加输出命令; exit 命令是否单独处理?
		System.out.println(s);
		System.out.print(jedisServerStrap.getHost() + ">");
	}
}
