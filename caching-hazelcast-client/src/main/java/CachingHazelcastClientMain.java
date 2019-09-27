import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.kitcut.helloworld.database.entity.EmployeeEntity;
import service.CachingEmployeeService;

import java.util.List;
import java.util.Map;

public class CachingHazelcastClientMain {
    public static void main(String[] args) {
        CachingEmployeeService cachingEmployeeService = new CachingEmployeeService();
        List<EmployeeEntity> employeeEntityList = cachingEmployeeService.getAll();
int i = 1;
    }
}
