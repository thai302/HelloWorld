package com.ecorau.vbn.sctp.channelhandler;

import com.ecorau.vbn.RequestContex;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.sctp.SctpMessage;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ChannelHandlerBase extends ChannelInboundHandlerAdapter {
    final static Logger logger = Logger.getLogger(ChannelHandlerBase.class);

    protected ChannelHandlerContext ctx;
    private int protocolIdentifier;

    public ChannelHandlerBase(int protocolIdentifier) {
        this.protocolIdentifier = protocolIdentifier;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Connected to server!!!");
        this.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof SctpMessage) {
                SctpMessage sctpMessage = (SctpMessage) msg;
                logger.info("Start: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
                logger.info("Server said(hex): " + ByteBufUtil.hexDump(sctpMessage.content()));

                if (sctpMessage.protocolIdentifier() == this.protocolIdentifier)
                    processMessage(sctpMessage);
                else
                    logger.warn("Can not handle message with protocolIdentifier = " + sctpMessage.protocolIdentifier());
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Disconnect to server!!!");
    }

    protected void sendMessage(SctpMessage msg) {
        if (ctx != null) {
            ctx.write(msg);
            ctx.flush();

            logger.info("Client Sent!!!");
        } else {
            logger.info("Client not ready!!!");
        }
    }

    protected abstract void processMessage(SctpMessage sctpMessage);
}