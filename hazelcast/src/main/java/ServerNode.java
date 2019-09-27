import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.kitcut.helloworld.cachingserver.dao.EmployeeDAO;
import entity.EmployeeEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Map;

public class ServerNode {
    private static SessionFactory factory;

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Long, List<EmployeeEntity>> map = hazelcastInstance.getMap("data");
//        IdGenerator idGenerator = hazelcastInstance.getIdGenerator("newid");
//        for (int i = 0; i < 10; i++) {
//            map.put(idGenerator.newId(), "message" + 1);
//        }


        //Hibernate
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        EmployeeDAO ME = new EmployeeDAO(factory);
        /* Add few employee records in database */
//        Integer empID1 = ME.addEmployee("firstName", "Ali", 1000);
        List<EmployeeEntity> list = ME.listEmployees();
        map.put(1L, list);
        int i = 1;
    }
}