package com.ecorau.vbn.map;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerMessageBase;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

public class MapMessageHandler extends TcapUpperLayerMessageBase implements TcapUpperLayerMessageHandler {
    private static final Logger logger = Logger.getLogger(MapMessageHandler.class);

    public MapMessageHandler(RequestContex requestContex) {
        super(requestContex);
    }

    @Override
    public void process() {
        if (requestContex.getComponents().length > 0) {
            Component component = requestContex.getComponents()[0];
            Invoke invoke = (Invoke) component;
            MapMessageChildHandler mapMessageChildHandler = MapMessageFactory.getMapMessageHandlers(invoke);

            if (mapMessageChildHandler != null) {
                requestContex.setInvoke(invoke);
                mapMessageChildHandler.process(requestContex);
            } else
                logger.warn("Map message is not handled");
        }
    }
}
