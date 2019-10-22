package com.kitcut.dao;

import com.kitcut.entity.Employee;
import com.kitcut.entity.EmployeeRs;
import net.sf.ehcache.CacheManager;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmployeeDAO extends BaseDAO {

    public EmployeeDAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary) {
        Session session = factory.withOptions().openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            System.out.println(new Date());

            tx = session.beginTransaction();
            for (int i = 0; i < 1; i++) {
                Employee employee = new Employee();
                employee.setFirstName(fname);
                employee.setLastName(lname);
                employee.setSalary(salary);
//                employeeID = (Integer) session.save(employee);
//                session.persist(employee);
                session.persist(employee);

//                employee.setSalary(11);
//                session.save(employee);
//                employee.setSalary(12);
//                session.save(employee);
//                if (i % 50 == 0) { // Same as the JDBC batch size
//                    //flush a batch of inserts and release memory:
//                    session.flush();
//                    session.clear();
//                }

            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("SELECT e FROM Employee e");
            List employees = query.list();

            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                System.out.print("  Id: " + employee.getId());
                System.out.print("  First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to  READ all the employees */
    public void listEmployeesRs() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String hql = "SELECT new com.kitcut.entity.EmployeeRs(e.id, e.firstName) FROM Employee e WHERE id = :id";
            Query<EmployeeRs> query = session.createQuery(hql);
            query.setParameter("id", 1);
            List<EmployeeRs> results = query.list();

            for (EmployeeRs employeeRs : results) {
                System.out.print("  Id: " + employeeRs.getId());
                System.out.print("  First Name: " + employeeRs.getFirstName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void testSecondLevelCache() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            int id = 716870;

            Session session1 = factory.openSession();
            Employee emp1 = session1.get(Employee.class, id);
            session1.close();

            Session session2 = factory.openSession();
            Employee emp2 = session2.get(Employee.class, id);
            session2.close();

            Employee employee = session.get(Employee.class, id);

            int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                    .getCache("com.kitcut.entity.Employee").getSize();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
