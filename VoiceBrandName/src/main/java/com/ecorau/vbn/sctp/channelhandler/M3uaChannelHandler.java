package com.ecorau.vbn.sctp.channelhandler;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.constants.PayloadProtocolIdentifier;
import com.ecorau.vbn.m3ua.M3uaMessageFactory;
import com.ecorau.vbn.m3ua.M3uaMessageHandler;
import com.sun.nio.sctp.MessageInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import org.restcomm.protocols.ss7.m3ua.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPUpImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.message.MessageFactory;

public class M3uaChannelHandler extends ChannelHandlerBase {

    private MessageFactory messageFactory = new MessageFactoryImpl();

    public M3uaChannelHandler() {
        super(PayloadProtocolIdentifier.M3UA.value());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        sendM3uaAspUp();
    }

    private void sendM3uaAspUp() {
        ASPUpImpl aspUp = new ASPUpImpl();
        ByteBuf data = Unpooled.buffer();
        aspUp.encode(data);

        sendMessage(data);
    }

    public void sendMessage(ByteBuf data) {
        final MessageInfo messageInfo = MessageInfo.createOutgoing(null, 0);
        messageInfo.payloadProtocolID(PayloadProtocolIdentifier.M3UA.value());
        SctpMessage msg = new SctpMessage(messageInfo, data);

        super.sendMessage(msg);
    }

    @Override
    protected void processMessage(RequestContex requestContex) {
        M3UAMessage m3uaMessage = messageFactory.createMessage(requestContex.getSctpMessage().content());
        requestContex.setM3uaMessage(m3uaMessage);
        M3uaMessageHandler m3uaMessageHandler = M3uaMessageFactory.getM3uaMessageHandler(m3uaMessage);

        if (m3uaMessageHandler != null)
            m3uaMessageHandler.process(requestContex);
        else
            logger.warn("M3ua message is not handled");
    }
}
