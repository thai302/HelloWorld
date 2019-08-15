package com.ecorau.vbn.tcap.upperlayer;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.tcap.TcapEndMessageHandler;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.*;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;

public abstract class TcapResponseMessageHandler implements PackageSender<Object> {

    private static final Logger logger = Logger.getLogger(TcapResponseMessageHandler.class);

    @Override
    public void send(Object msg, RequestContex requestContex) {
        try {
            //set component
            Component component = setComponent(msg);

            //set dialog portion
            TCBeginMessage tcBeginMessage = (TCBeginMessage) requestContex.getTcapMessage();
            DialogPortion dialogPortion = setDialogPortion(tcBeginMessage);

            TCEndMessage tcEndMessage = TcapFactory.createTCEndMessage();
            tcEndMessage.setComponent(new Component[]{component});
            tcEndMessage.setDialogPortion(dialogPortion);
            tcEndMessage.setDestinationTransactionId(tcBeginMessage.getOriginatingTransactionId());
            logger.info("TCapSender send message: " + tcEndMessage.toString());

            PackageSender packageSender = new TcapEndMessageHandler();
            packageSender.send(tcEndMessage, requestContex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private DialogPortion setDialogPortion(TCBeginMessage tcBeginMessage) throws Exception {
        DialogRequestAPDU dialogRequest = (DialogRequestAPDU) tcBeginMessage
                .getDialogPortion().getDialogAPDU();
        DialogResponseAPDU dialog = TcapFactory.createDialogAPDUResponse();
        dialog.setApplicationContextName(dialogRequest.getApplicationContextName());

        AsnOutputStream protocalVersion = new AsnOutputStream();
        protocalVersion.write(128);
        dialog.getProtocolVersion().encode(protocalVersion);

        Result result = TcapFactory.createResult();
        result.setResultType(ResultType.Accepted);
        dialog.setResult(result);

        ResultSourceDiagnostic resultSourceDiagnostic = TcapFactory.createResultSourceDiagnostic();
        resultSourceDiagnostic.setDialogServiceUserType(DialogServiceUserType.Null);
        dialog.setResultSourceDiagnostic(resultSourceDiagnostic);

        DialogPortion dialogPortion = TcapFactory.createDialogPortion();
        dialogPortion.setDialogAPDU(dialog);
        dialogPortion.setUnidirectional(false);

        return dialogPortion;
    }

    protected abstract Component setComponent(Object msg) throws Exception;
}
