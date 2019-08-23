package com.zzq.core.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zhou Zhong Qing
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2019/8/614:54
 */
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object respone;

    public Object getRespone() {
        return respone;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client received  data "+ msg );


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        System.out.println("出现异常 ["+cause.getMessage() +"]" + " [" +cause.getStackTrace()+" ]");
        ctx.close();
    }
}
