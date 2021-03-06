package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.RequestData;
import model.ResponseData;

import java.nio.charset.Charset;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

//        RequestData requestData = (RequestData) msg;
//        ResponseData responseData = new ResponseData();
//        responseData.setIntValue(requestData.getIntValue() * 2);
//        ChannelFuture future = ctx.writeAndFlush(responseData);
//        future.addListener(ChannelFutureListener.CLOSE);
//        System.out.println(requestData);
        String hello = ((ByteBuf) msg).readCharSequence(100, Charset.forName("UTF-8")).toString();
        System.out.println(hello);

    }
}