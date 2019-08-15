package com.ecorau.vbn.sctp;

import com.ecorau.vbn.sctp.channelhandler.M3uaChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SctpClient {
    final static Logger logger = Logger.getLogger(SctpClient.class);

    private M3uaChannelHandler channelHandler = new M3uaChannelHandler();

    private static SctpClient sctpClient = new SctpClient();

    private SctpClient() {
    }

    public static SctpClient getInstance() {
        return sctpClient;
    }

    private AppConfig getConfig() {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + path);
        String configFileDir = System.getProperty("user.dir") + File.separator + "config" + File.separator;
        System.out.println("configFileDir is: " + configFileDir);
        try (InputStream input = new FileInputStream(configFileDir + File.separator + "config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            AppConfig config = new AppConfig();
            config.setRemoteIp(prop.getProperty("server.ip"));
            config.setRemotePort(Integer.parseInt(prop.getProperty("server.port")));
            config.setLocalIp(prop.getProperty("local.ip"));
            config.setLocalPort(Integer.parseInt(prop.getProperty("local.port")));

            //            config.aspUpId = Long.parseLong(prop.getProperty("aspup.id", "-1"));
//            config.aspUpInfo = prop.getProperty("aspup.info");
//            config.brandName = prop.getProperty("brandName");
            return config;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void connect() {
        AppConfig appConfig = getConfig();

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
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
    }

//    public void writeMessage(SctpMessage msg) {
//        channelHandler.s.sendSctpMessage(msg);
//    }

    public void sendMessage(ByteBuf data) {
        channelHandler.sendMessage(data);
    }
}
