import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.M3uaMessageHandler;
import com.ecorau.vbn.m3ua.M3uaPayloadDataMessageHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.restcomm.protocols.ss7.m3ua.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;

public class VbnTest {
    public static void main(String[] args){
        String msgNokia = "0100" +
                "0101000000a002100095000005290000" +
                "0d0e030300050980030e190b12060011" +
                "044889200036010b1207001104488920" +
                "000501676265480429960d246b41283f" +
                "060700118605010101a0346032800207" +
                "80a109060704000001001302be21281f" +
                "060704000001010101a014a012800791" +
                "4868784987f68107914889200005f16c" +
                "1aa11802010002013c301004010f040b" +
                "3834383638373934373836000000";

        String msgHuawei = "0100" +
                "0101000000c4021000bc000005290000" +
                "0d0e0303030d0901030e190b12920011" +
                "044889200036010b1292001104488920" +
                "3024078e62818b48041005a83f6b1a28" +
                "18060700118605010101a00d600ba109" +
                "0607040000010032016c67a165020180" +
                "020100305d800200c882088410483338" +
                "5376f08308841348898902020785010a" +
                "8a088493488920102006bb0580038090" +
                "a39c010c9f320854020440838419f0bf" +
                "35038301119f3605262df2d3249f3707" +
                "914889203024f79f3908029170717100" +
                "0582";

        byte[] msgBytes = ByteBufUtil.decodeHexDump(msgNokia);
        ByteBuf msgByteBuf = Unpooled.copiedBuffer(msgBytes);

        M3UAMessage m3uaMsg = new MessageFactoryImpl().createMessage(msgByteBuf);
        RequestContex requestContex = new RequestContex();
        requestContex.setM3uaMessage(m3uaMsg);

        M3uaMessageHandler m3uaMessageHandler = new M3uaPayloadDataMessageHandler();
        m3uaMessageHandler.process(requestContex);
    }
}
