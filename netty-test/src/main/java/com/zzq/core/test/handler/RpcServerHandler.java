package com.zzq.core.test.handler;

import com.alibaba.fastjson.JSONObject;
import com.zzq.core.test.bean.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


import java.util.Date;
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


        System.out.println(new Date().toString() + " server received data " + JSONObject.toJSONString(msg));

        //获得消费者传过来的数据

        // 发送数据给客户端
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setResult("response " + JSONObject.toJSONString(msg));
        // 从tail节点开始调用findContextOutbound一直往前得到handle，调用其write直到head节点;如果中间有handler传递write会被中断;进入AbstractChannel#write
        //ctx.channel().write(rpcResponse);
        // 从当前节点开始调用findContextOutbound一直往前得到handle，调用其write直到head节点;如果中间有handler传递write会被中断
        try {
            Thread.sleep(25000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.write(rpcResponse);
        ctx.flush();
        ctx.close();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        ctx.close();
    }


}
