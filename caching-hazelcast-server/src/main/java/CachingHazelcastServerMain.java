import com.hazelcast.core.BaseMap;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.kitcut.helloworld.cachingserver.annotation.MapStore;
import com.kitcut.helloworld.cachingserver.mapstore.BaseMapStore;
import com.kitcut.helloworld.cachingserver.mapstore.EmployeeMapStore;
import com.kitcut.helloworld.reflection.MethodUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class CachingHazelcastServerMain {
    private static SessionFactory factory;

    public static void main(String[] args) {
        loadMapStore();
    }

    private static void loadMapStore() {
        Reflections reflections = new Reflections("com.kitcut.helloworld.cachingserver.mapstore");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(MapStore.class);
        try {
            for (Class<?> clazz : classSet) {
                Object object = clazz.newInstance();
                MethodUtils.invokeMethod(object, clazz, "init");
                MethodUtils.invokeMethod(object, clazz, "loadAll");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}