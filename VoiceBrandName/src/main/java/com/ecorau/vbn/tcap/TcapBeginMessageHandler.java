package com.ecorau.vbn.tcap;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerFactory;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;

public class TcapBeginMessageHandler extends TcapMessageBase implements TcapMessageHandler {

    private static final Logger logger = Logger.getLogger(TcapBeginMessageHandler.class);

    public TcapBeginMessageHandler(RequestContex requestContex) {
        super(requestContex);
    }

    @Override
    public void process() {
        TCBeginMessage tcBeginMessage = (TCBeginMessage) requestContex.getTcapMessage();

        requestContex.setComponents(tcBeginMessage.getComponent());
        TcapUpperLayerMessageHandler tcapUpperLayerMessageHandler = TcapUpperLayerFactory.getTcapUpperLayerMessageHandler(
                tcBeginMessage.getDialogPortion(), requestContex);
        if (tcapUpperLayerMessageHandler != null) {
            tcapUpperLayerMessageHandler.process();
        } else
            logger.warn("Tcap message is not handled");
    }
}
