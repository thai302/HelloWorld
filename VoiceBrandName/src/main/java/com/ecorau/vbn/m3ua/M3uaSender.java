package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.sctp.SctpClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;

public class M3uaSender implements PackageSender<M3UAMessage> {

    private static final Logger logger = Logger.getLogger(M3uaSender.class);

    @Override
    public void send(M3UAMessage msg, RequestContex requestContex) {
        ByteBuf data = Unpooled.buffer();
        ((M3UAMessageImpl) msg).encode(data);

        logger.info("M3UA message to send hex: " + ByteBufUtil.hexDump(data));

        SctpClient.getInstance().sendMessage(data);
    }
}
