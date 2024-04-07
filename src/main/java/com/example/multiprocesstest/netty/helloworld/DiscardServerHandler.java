package com.example.multiprocesstest.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCounted;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // Discard the received data silently
//        ((ByteBuf)msg).release();



//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) {
//                System.out.println((char)in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            in.release();
//        }









        ctx.write(msg);
        ctx.flush();


    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
