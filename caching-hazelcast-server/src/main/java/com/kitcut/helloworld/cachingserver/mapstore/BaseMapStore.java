package com.kitcut.helloworld.cachingserver.mapstore;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.kitcut.helloworld.database.entity.EmployeeEntity;
import lombok.val;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseMapStore<K, V> {
    protected static SessionFactory factory;

    protected static HazelcastInstance hazelcastInstance;

    private Class<V> clazzEntity;

    protected Map<K, V> map;

    private Field fieldId;

    static {
        hazelcastInstance = Hazelcast.newHazelcastInstance();

        //Hibernate
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    //called when start project
    protected void init() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        clazzEntity = (Class<V>) paramType.getActualTypeArguments()[1];

        map = hazelcastInstance.getMap(clazzEntity.getSimpleName());

        for (Field field : clazzEntity.getDeclaredFields()) {
            Annotation annotation = field.getAnnotation(Id.class);
            if (annotation != null) {
                fieldId = field;
                fieldId.setAccessible(true);
                break;
            }
        }
    }

    //called when start project
    protected void loadAll() {
        Session session = factory.openSession();

        try {
            Query query = session.createQuery("FROM " + clazzEntity.getName(), clazzEntity);
            List<V> values = query.list();
            if (values.size() > 0) {

                if (fieldId != null) {
                    for (V value : values) {
                        K key = (K) fieldId.get(value);
                        map.put(key, value);
                    }
                }
            }

            int i = 1;
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
    }

    protected void save(V value) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(value);

            K key = (K) fieldId.get(value);
            if (map.containsKey(key))
                map.replace(key, value);
            else
                map.put(key, value);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected void delete(V value) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(value);

            K key = (K) fieldId.get(value);
            map.remove(key);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected void deleteById(K key) {
        V value = map.get(key);
        if(value != null)
            delete(value);
    }
}
