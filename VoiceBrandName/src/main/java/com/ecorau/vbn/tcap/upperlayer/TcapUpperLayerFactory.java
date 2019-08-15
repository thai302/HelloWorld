package com.ecorau.vbn.tcap.upperlayer;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.cap.CapMessageHandler;
import com.ecorau.vbn.map.MapMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU;

public class TcapUpperLayerFactory {
    private static final Logger logger = Logger.getLogger(TcapUpperLayerFactory.class);

    public static TcapUpperLayerMessageHandler getTcapUpperLayerMessageHandler(DialogPortion dialogPortion, RequestContex requestContex) {
//        long[] oidCap = {0, 4, 0, 0, 1, 0, 50, 1};
//        long[] oidMap = {0, 4, 0, 0, 1, 0, 19, 2};
        long[] oid = ((DialogRequestAPDU) dialogPortion.getDialogAPDU()).getApplicationContextName().getOid();

        //check CAP
        CAPApplicationContext capApplicationContext = CAPApplicationContext.getInstance(oid);
        if (capApplicationContext != null) {
            return new CapMessageHandler(requestContex);
        } else {
            //check MAP
            MAPApplicationContext mapApplicationContext = MAPApplicationContext.getInstance(oid);
            if (mapApplicationContext != null) {
                return new MapMessageHandler(requestContex);

            } else {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < oid.length; i++) {
                    str.append(oid[i] + ".");
                }
                logger.error("Application context name is not handled" + str);
                return null;
            }
        }
    }
}
