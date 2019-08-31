package com.ecorau.vbn.cap;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.cache.BrandName;
import com.ecorau.vbn.cap.response.CapConnectMessageHandler;
import com.ecorau.vbn.utils.GSM7BitConverter;
import io.netty.buffer.ByteBufUtil;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.primitives.CriticalityType;
import org.restcomm.protocols.ss7.cap.api.primitives.ExtensionField;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ConnectRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.InitialDPRequest;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.primitives.ExtensionFieldImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.ConnectRequestImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.InitialDPRequestImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.DestinationRoutingAddressImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

import java.util.ArrayList;

public class CapInitialDPRequestMessageHandler implements CapMessageChildHandler {
    private static final Logger logger = Logger.getLogger(CapInitialDPRequestMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        InitialDPRequest initialDPRequest = buildInitialDPRequest(requestContex.getInvoke());
        ConnectRequest connectRequest = buildConnectRequest(initialDPRequest);
        PackageSender packageSender = new CapConnectMessageHandler();
        packageSender.send(connectRequest, requestContex);
    }

    private InitialDPRequest buildInitialDPRequest(Invoke invoke) {
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
        InitialDPRequestImpl initialDPRequest = new InitialDPRequestImpl(false);
        try {
            initialDPRequest.decodeData(ais, buf.length);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        initialDPRequest.setInvokeId(invoke.getInvokeId());

        return initialDPRequest;
    }

    private ConnectRequest buildConnectRequest(InitialDPRequest initialDPRequest) {
        try {
            //set DestinationRoutingAddress
            DestinationRoutingAddressImpl dra = new DestinationRoutingAddressImpl();
            dra.calledPartyNumber = new ArrayList<>();
            dra.calledPartyNumber.add(initialDPRequest.getCalledPartyNumber());

            //set CAPExtensions
            String brandNameStr = BrandName.getBrandName(initialDPRequest.getCallingPartyNumber().getCallingPartyNumber().getAddress());
            byte[] brandNameData = ByteBufUtil.decodeHexDump(GSM7BitConverter.stringToGsm7BitPackHexUssd(brandNameStr));
            AsnOutputStream brandNameAos = new AsnOutputStream();
//            brandNameAos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, 1);
            brandNameAos.write(ByteBufUtil.decodeHexDump("80010F8101"));
            brandNameAos.write(brandNameData.length);
            brandNameAos.write(ByteBufUtil.decodeHexDump("82"));
            brandNameAos.write(brandNameData.length);
            brandNameAos.write(brandNameData);

            ExtensionFieldImpl extensionField
                    = new ExtensionFieldImpl(new long[]{0, 4, 0, 1, 2}, CriticalityType.typeIgnore, brandNameAos.toByteArray());
            extensionField.setCriticalityType(CriticalityType.typeIgnore);
            extensionField.isPrimitive = false;

            ArrayList<ExtensionField> extensionFields = new ArrayList<>();
            extensionFields.add(extensionField);

            CAPExtensionsImpl capEx = new CAPExtensionsImpl(extensionFields);

            ConnectRequest connMsg = new ConnectRequestImpl(dra, null, null,
                    capEx, null, null, null, null, null, null, null,
                    null, null, false, false, false, null, false, false);
            connMsg.setInvokeId(initialDPRequest.getInvokeId());

            return connMsg;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
