package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import model.RequestData;

import java.net.InetSocketAddress;

public class NettyTcpClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();

            clientBootstrap.group(group);
            clientBootstrap.channel(NioSocketChannel.class);
            clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 9999));
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline().addLast(new ClientHandler());
                    socketChannel.pipeline().addLast(new RequestDataEncoder(),
                            new ResponseDataDecoder(), new ClientHandler());
                }
            });
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

//    public static class ClientHandler extends SimpleChannelInboundHandler {
//
//        @Override
//        public void channelActive(ChannelHandlerContext channelHandlerContext) {
////            SctpMessage sctpMessage = new SctpMessage(0, 0, Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
//            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
//        }
//
////        @Override
////        public void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) {
////            System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
////        }
//
//        @Override
//        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//            System.out.println("Client received: " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
//            cause.printStackTrace();
//            channelHandlerContext.close();
//        }
//    }
}
