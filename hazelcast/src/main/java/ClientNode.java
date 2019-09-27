import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

public class ClientNode {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");

//        Config config = new Config();
//        NetworkConfig network = config.getNetworkConfig();
//        network.setPort(5701).setPortCount(20);
//        network.setPortAutoIncrement(true);
//        JoinConfig join = network.getJoin();
//        join.getMulticastConfig().setEnabled(false);
//        join.getTcpIpConfig()
//                .addMember("machine1")
//                .addMember("localhost").setEnabled(true);


        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(config);

        IMap<Long, List<EmployeeEntity>> map = hzClient.getMap("data");
        int i = 1;
    }
}
