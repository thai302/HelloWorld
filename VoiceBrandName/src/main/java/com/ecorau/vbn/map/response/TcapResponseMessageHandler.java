package com.ecorau.vbn.map.response;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponse;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.tcap.asn.OperationCodeImpl;
import org.restcomm.protocols.ss7.tcap.asn.ParameterImpl;
import org.restcomm.protocols.ss7.tcap.asn.ReturnResultLastImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

public class TcapResponseMessageHandler extends com.ecorau.vbn.tcap.upperlayer.TcapResponseMessageHandler {

    @Override
    protected Component setComponent(Object msg) throws Exception {
        AsnOutputStream aos = new AsnOutputStream();
        ((MAPAsnPrimitive) msg).encodeData(aos);
        ParameterImpl parameter = new ParameterImpl();
        parameter.setData(aos.toByteArray());
        parameter.setPrimitive(false);
        parameter.setTag(Tag.SEQUENCE);
        parameter.setTagClass(Tag.CLASS_UNIVERSAL);

        ReturnResultLast returnResultLast = new ReturnResultLastImpl();
        returnResultLast.setParameter(parameter);
        returnResultLast.setInvokeId(((UnstructuredSSResponse) msg).getInvokeId());

        OperationCode operationCode = new OperationCodeImpl();
//            operationCode.setGlobalOperationCode(new long[]{1, 3, 2, 1});
        operationCode.setLocalOperationCode((long) MAPOperationCode.unstructuredSS_Request);
        returnResultLast.setOperationCode(operationCode);

        return returnResultLast;
    }
}
