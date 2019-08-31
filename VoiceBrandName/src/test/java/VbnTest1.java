import com.ecorau.vbn.queue.Consumer;
import com.ecorau.vbn.queue.Producer;
import com.ecorau.vbn.sctp.AppConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VbnTest1 {
    private static final Logger logger = Logger.getLogger(VbnTest1.class);

    public static void main(String[] args) {
        int numberOfThread = AppConfig.getInstance().getNumberOfThread();
        ExecutorService executionException = Executors.newFixedThreadPool(numberOfThread);

        Consumer consumer = new Consumer(Producer.queue);
        for (int i = 0; i < numberOfThread; i++) {
            executionException.execute(consumer);
        }

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
                "0d0e0303030f0901030e190b12920011" +
                "044889200036010b1292001104488920" +
                "3024078e62818b480410067d506b1a28" +
                "18060700118605010101a00d600ba109" +
                "0607040000010032016c67a165020180" +
                "020100305d800200c882088410483352" +
                "2701f98308841348735425740585010a" +
                "8a088493488920102008bb0580038090" +
                "a39c010c9f320854020440838439f4bf" +
                "35038301119f3605202257d3249f3707" +
                "914889203024f79f3908029180121282" +
                "9282";


        int numberOfMessage = 1;
        for (int i = 0; i < numberOfMessage; i++) {
            logger.info("Start: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));

            byte[] msgBytes = ByteBufUtil.decodeHexDump(msgHuawei);
            ByteBuf msgByteBuf = Unpooled.copiedBuffer(msgBytes);

            //queue with bytebuff
//            M3uaChannelHandler.isHandShakeSuccess = true;
//            Producer.addMessageToQueue(msgByteBuf);

            //queue with payload
//            M3UAMessage m3uaMsg = new MessageFactoryImpl().createMessage(msgByteBuf);
//            RequestContex requestContex = new RequestContex();
//            requestContex.setM3uaMessage(m3uaMsg);
//
//            M3uaMessageHandler m3uaMessageHandler = new M3uaPayloadDataMessageHandler();
//            m3uaMessageHandler.process(requestContex);

        }
    }
}
