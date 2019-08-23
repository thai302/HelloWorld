package com.ecorau.vbn.sctp;

import com.ecorau.vbn.sctp.channelhandler.M3uaChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class SctpClient implements Runnable {
    private static SctpClient sctpClient = new SctpClient();
    private M3uaChannelHandler channelHandler = new M3uaChannelHandler();

    private SctpClient() {
    }

    public static SctpClient getInstance() {
        return sctpClient;
    }

    @Override
    public void run() {
        connect();
    }

    private void connect() {
        AppConfig appConfig = AppConfig.getInstance();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();

            clientBootstrap.group(group);
            clientBootstrap.channel(NioSctpChannel.class);
            clientBootstrap.handler(new ChannelInitializer<NioSctpChannel>() {
                protected void initChannel(NioSctpChannel channel) throws Exception {
                    channel.pipeline().addLast(channelHandler);
                }
            });
            SocketAddress remoteAddress = new InetSocketAddress(appConfig.getRemoteIp(), appConfig.getRemotePort());
            SocketAddress localAddress = new InetSocketAddress(appConfig.getLocalIp(), appConfig.getLocalPort());
            ChannelFuture channelFuture = clientBootstrap.connect(remoteAddress, localAddress).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendMessage(ByteBuf data) {
        channelHandler.sendMessage(data);
    }
}
