package com.ecorau.vbn.cap.response;

import com.ecorau.vbn.tcap.upperlayer.TcapResponseMessageHandler;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ConnectRequest;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcap.asn.OperationCodeImpl;
import org.restcomm.protocols.ss7.tcap.asn.ParameterImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;

public class CapConnectMessageHandler extends TcapResponseMessageHandler {

    @Override
    protected Component setComponent(Object msg) throws Exception {
        AsnOutputStream aos = new AsnOutputStream();
        ((CAPAsnPrimitive) msg).encodeData(aos);
        ParameterImpl parameter = new ParameterImpl();
        parameter.setData(aos.toByteArray());
        parameter.setPrimitive(false);
        parameter.setTag(Tag.SEQUENCE);
        parameter.setTagClass(Tag.CLASS_UNIVERSAL);

        Invoke invoke = new InvokeImpl();
        invoke.setParameter(parameter);
        invoke.setInvokeId(((ConnectRequest) msg).getInvokeId());

        OperationCode operationCode = new OperationCodeImpl();
//            operationCode.setGlobalOperationCode(new long[]{1, 3, 2, 1});
        operationCode.setLocalOperationCode((long) CAPOperationCode.connect);
        invoke.setOperationCode(operationCode);

        return invoke;
    }

}
