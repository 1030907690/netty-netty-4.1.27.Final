package com.zzq.core.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;



import java.util.Map;

public class RpcServerHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> handlerMap;

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx 可以用来向客户端发送数据
        //msg 可以接收到数据


        System.out.println(" server received data " + msg);

        //获得消费者传过来的数据
        ctx.write("response " + ((RpcRequest)msg).getName());
        ctx.flush();
        ctx.close();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        ctx.close();
    }



}
