package com.kitcut.helloworld.cachingserver.mapstore;

import com.hazelcast.core.HazelcastInstance;
import com.kitcut.helloworld.cachingserver.annotation.MapStore;
import com.kitcut.helloworld.database.entity.EmployeeEntity;
import org.hibernate.SessionFactory;

@MapStore
public class EmployeeMapStore extends BaseMapStore<Long, EmployeeEntity> {
//    public EmployeeMapStore() {
//        super.init();
//    }
}
