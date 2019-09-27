package service;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.kitcut.helloworld.database.entity.EmployeeEntity;

import java.util.List;

public class BaseService<E> {
    protected static HazelcastInstance hzClient;

    static {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");

        hzClient
                = HazelcastClient.newHazelcastClient(config);
    }

    public List<E> getAll() {
        IMap<Long, List<E>> map = hzClient.getMap("data");
        return map.get(1L);
    }
}
