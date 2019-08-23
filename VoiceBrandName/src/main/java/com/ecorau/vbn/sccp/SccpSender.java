package com.ecorau.vbn.sccp;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.M3uaSender;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.message.transfer.PayloadDataImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.EncodingResultData;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpDataMessageImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceDefault;

public class SccpSender implements PackageSender<SccpDataMessage> {

    private static final Logger logger = Logger.getLogger(SccpSender.class);

    @Override
    public void send(SccpDataMessage msg, RequestContex requestContex) {
        try {
            PayloadData oldMsg = (PayloadData) requestContex.getM3uaMessage();
            ProtocolData oldProtocolData = oldMsg.getData();
            PayloadData m3uaPayloadData = new PayloadDataImpl();

            Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceDefault();
            SccpStackImpl sccpStackImpl = new SccpStackImpl(null, ss7ExtInterface);
            SccpDataMessageImpl sccpDataMsg = (SccpDataMessageImpl) msg;
            EncodingResultData sData = sccpDataMsg.encode(sccpStackImpl, LongMessageRuleType.LONG_MESSAGE_FORBBIDEN, Integer.MAX_VALUE, logger, true, SccpProtocolVersion.ANSI);

            ProtocolData protocolData = new ParameterFactoryImpl()
                    .createProtocolData(oldProtocolData.getDpc(), oldProtocolData.getOpc(),
                            oldProtocolData.getSI(), oldProtocolData.getNI(),
                            oldProtocolData.getMP(), oldProtocolData.getSLS(), sData.getSolidData());
            m3uaPayloadData.setData(protocolData);
//            logger.info("M3UA sender send message: " + m3uaPayloadData.toString());

            PackageSender packageSender = new M3uaSender();
            packageSender.send(m3uaPayloadData, requestContex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
