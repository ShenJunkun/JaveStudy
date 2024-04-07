package com.example.multiprocesstest.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
    private int port;

    public DiscardServer(int p) {
        port = p;
    }

    public void run() throws Exception{
        // Nio是一个处理I/O操作的多线程事件循环。
        // 创建两个EventLoopGroup,其中boss负责接收来到的请求，
        // worker负责处理被boss接受后的请求
        //至于使用多少线程是由EventLoopGroup初始化决定的。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap是一个创建一个server的服务类。
            //可以通过Channel来创建，但是过程非常复杂。
            ServerBootstrap b = new ServerBootstrap();
            // 在这里，我们指定使用NioServerSocketChannel类，该类用于实例化一个新Channel以接受传入的连接。
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //这里指定的处理程序将始终由新接受的通道计算。ChannelInitializer是一个特殊的处理程序，
                    // 用于帮助用户配置新通道。您很可能希望通过添加一些处理程序(如DiscardServerHandler)
                    // 来配置新通道的ChannelPipeline，以实现您的网络应用程序。
                    // 随着应用程序变得复杂，您可能会向管道中添加更多处理程序，
                    // 并最终将这个匿名类提取到顶级类中。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // 您还可以设置特定于Channel实现的参数。我们正在编写一个TCP/IP服务器，
                    // 所以我们可以设置套接字选项，如tcpNoDelay和keepAlive。
                    // 请参考ChannelOption的apidocs和特定的ChannelConfig实现，以获得有关支持的
                    // ChannelOptions的概述。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();


            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 9011;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }

}
