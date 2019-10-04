package service;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.kitcut.helloworld.database.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class BaseService<K, V> {
    protected static HazelcastInstance hzClient;

    private Class<V> clazzEntity;

    static {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");

        hzClient
                = HazelcastClient.newHazelcastClient(config);
    }

    public List<V> getAll() {
        IMap<K, V> map = hzClient.getMap(EmployeeEntity.class.getSimpleName());
        return new ArrayList<>(map.values());
    }

    public void  delete(K k) {
        IMap<K, V> map = hzClient.getMap(EmployeeEntity.class.getSimpleName());
        map.remove(1);
        int i = 1;
    }
}
