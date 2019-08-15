package com.ecorau.vbn.map;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.map.response.TcapResponseMessageHandler;
import io.netty.buffer.ByteBufUtil;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.supplementary.UnstructuredSSRequest;
import org.restcomm.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponse;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.UnstructuredSSRequestImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.UnstructuredSSResponseImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

public class MapUnstructuredSSRequestMessageHandler implements MapMessageChildHandler {
    private static final Logger logger = Logger.getLogger(MapUnstructuredSSRequestMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        UnstructuredSSRequest unstructuredSSRequest = buildUnstructuredSSRequest(requestContex.getInvoke());
        UnstructuredSSResponse UnstructuredSSResponse = buildUnstructuredSSResponse(unstructuredSSRequest);
        PackageSender packageSender = new TcapResponseMessageHandler();
        packageSender.send(UnstructuredSSResponse, requestContex);
    }

    private UnstructuredSSRequest buildUnstructuredSSRequest(Invoke invoke) {
        //        if (parameter == null) {
//            throw new CAPParsingComponentException(
//                    "Error while decoding initialDpRequest: Parameter is mandatory but not found",
//                    CAPParsingComponentExceptionReason.MistypedParameter);
//        }
//
//        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive()) {
//            throw new CAPParsingComponentException(
//                    "Error while decoding initialDpRequest: Bad tag or tagClass or parameter is primitive, received tag="
//                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);
//        }

        byte[] buf = invoke.getParameter().getData();
        AsnInputStream ais = new AsnInputStream(buf);
        UnstructuredSSRequestImpl unstructuredSSRequest = new UnstructuredSSRequestImpl();
        try {
            unstructuredSSRequest.decodeData(ais, buf.length);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        unstructuredSSRequest.setInvokeId(invoke.getInvokeId());

        return unstructuredSSRequest;
    }

    private UnstructuredSSResponse buildUnstructuredSSResponse(UnstructuredSSRequest unstructuredSSRequest) {
        AsnOutputStream brandNameAos = new AsnOutputStream();
        brandNameAos.write(ByteBufUtil.decodeHexDump("80010F8101"));
        byte[] brandNameData="Viettel@".getBytes();
        brandNameAos.write(brandNameData.length);
        brandNameAos.write(ByteBufUtil.decodeHexDump("82"));
        brandNameAos.write(brandNameData.length);
        brandNameAos.write(brandNameData);

//            CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(
//                    CBSDataCodingGroup.DataCodingMessageClass,
//                    CharacterSet.GSM8,
//                    null,
//                    null,
//                    false);
//            USSDString ussdString = new USSDStringImpl(brandNameData, cbsDataCodingScheme);
//            UnstructuredSSResponseImpl unstructuredSSResponse = new UnstructuredSSResponseImpl(cbsDataCodingScheme, ussdString);

        USSDString ussdString = new USSDStringImpl(brandNameData, unstructuredSSRequest.getDataCodingScheme());
        UnstructuredSSResponse unstructuredSSResponse = new UnstructuredSSResponseImpl(unstructuredSSRequest.getDataCodingScheme(), ussdString);
        unstructuredSSResponse.setInvokeId(unstructuredSSRequest.getInvokeId());

        return unstructuredSSResponse;
    }
}
