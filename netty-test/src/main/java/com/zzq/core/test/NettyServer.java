package com.zzq.core.test;

import com.zzq.core.test.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhou Zhong Qing
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2019/7/31 17:31
 */
public class NettyServer {


    public static void main(String[] args) {
        Map<String, Object> handlerMap = new HashMap<>();
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //启动netty服务
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);

            //springmvc web.xml
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel channel) {
                    //业务代码写这里
                    ChannelPipeline pipeline = channel.pipeline();

                    //netty编程 关注handler
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
                            Integer.MAX_VALUE, 0, 4, 0, 4
                    ));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("encoder", new ObjectEncoder());
                    pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE
                            , ClassResolvers.cacheDisabled(null)
                    ));


                    pipeline.addLast(new RpcServerHandler(handlerMap));
                }

            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            //netty 监听
            String serviceAddress = "127.0.0.1:8080";
            String[] addrs = serviceAddress.split(":");
            String ip = addrs[0];
            int port = Integer.parseInt(addrs[1]);
            ChannelFuture future = bootstrap.bind(ip, port).sync();
            System.out.println("接口服务启动成功，等待客户端连接...[ "+ serviceAddress + "]");
            //使用sync方法进行阻塞，等待服务端链路关闭之后Main函数才退出
            future.channel().closeFuture().sync();
            //  logger.info("服务关闭 [ {} ]", serviceAddress);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //退出，释放线程池资源
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
