import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.UUID;

public class MqttSubscribeMain {

    public static void main(String [] args){
        try {
            String publisherId = UUID.randomUUID().toString();
//            String publisherId = "clientId-Kncea34QQ71";
//            IMqttClient publisher = new MqttClient("wss://mqtt.eclipse.org:8000", publisherId);
//            IMqttClient publisher = new MqttClient("tcp://broker.mqttdashboard.com:8000", publisherId);
//            IMqttClient publisher = new MqttClient("tcp://broker.hivemq.com:1883", publisherId);
            IMqttClient subscribe = new MqttClient("tcp://localhost:1883", publisherId);
//            IMqttClient publisher = new MqttClient("wss://localhost:8000/mqtt", publisherId);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            subscribe.connect(options);

            subscribe.subscribe(EngineTemperatureSensor.TOPIC, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println(payload.toString());
            });
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
