import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MqttPublisherMain {

    public static void main(String[] args) {
        try {
            long n = 1000;
            for (int i = 1; i <= n; i++) {
                String publisherId = UUID.randomUUID().toString();
//            String publisherId = "clientId-Kncea34QQ7";
//            IMqttClient publisher = new MqttClient("wss://mqtt.eclipse.org:8000", publisherId);
//            IMqttClient publisher = new MqttClient("tcp://broker.mqttdashboard.com:8000", publisherId);
//            IMqttClient publisher = new MqttClient("tcp://broker.hivemq.com:1883", publisherId);
                IMqttClient publisher = new MqttClient("tcp://localhost:80", publisherId);
//            IMqttClient publisher = new MqttClient("wss://localhost:8000/mqtt", publisherId);

                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                options.setCleanSession(true);
                options.setConnectionTimeout(10);
                publisher.connect(options);

                System.out.println(i);
//            EngineTemperatureSensor sensor = new EngineTemperatureSensor(publisher);
//            sensor.call();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
