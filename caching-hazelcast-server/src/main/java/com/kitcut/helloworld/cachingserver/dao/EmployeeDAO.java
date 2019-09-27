package com.kitcut.helloworld.cachingserver.dao;

import com.kitcut.helloworld.database.entity.EmployeeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO extends BaseDAO {

    public EmployeeDAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

//    /* Method to CREATE an employee in the database */
//    public Integer addEmployee(String fname, String lname, int salary) {
//        Session session = factory.withOptions().openSession();
//        Transaction tx = null;
//        Integer employeeID = null;
//
//        try {
//            System.out.println(new Date());
//
//            tx = session.beginTransaction();
//            for (int i = 0; i < 1; i++) {
//                EmployeeEntity employee = new Employee();
//                employee.setFirstName(fname);
//                employee.setLastName(lname);
//                employee.setSalary(salary);
//                employeeID = (Integer) session.save(employee);
//
//                employee.setSalary(11);
//                session.save(employee);
//                employee.setSalary(12);
//                session.save(employee);
////                if (i % 50 == 0) { // Same as the JDBC batch size
////                    //flush a batch of inserts and release memory:
////                    session.flush();
////                    session.clear();
////                }
//
//            }
//
//            tx.commit();
//
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return employeeID;
//    }

    /* Method to  READ all the employees */
    public List<EmployeeEntity> listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

//            Query query = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class);
            Query query = session.createQuery("FROM com.kitcut.helloworld.database.entity.EmployeeEntity", EmployeeEntity.class);

            List<EmployeeEntity> employees = query.list();


            tx.commit();

            return employees;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
