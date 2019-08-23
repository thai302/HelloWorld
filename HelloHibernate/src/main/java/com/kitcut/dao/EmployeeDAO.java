package com.kitcut.dao;

import com.kitcut.entity.Address;
import com.kitcut.entity.Certificate;
import com.kitcut.entity.Employee;
import com.kitcut.entity.EmployeeInfo;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
                employeeID = (Integer) session.save(employee);

                employee.setSalary(11);
                session.save(employee);
                employee.setSalary(12);
                session.save(employee);
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
        Session session2 = factory.openSession();
        Transaction tx = null;
        Transaction tx2 = null;

        try {
            tx = session.beginTransaction();

            String hql = "SELECT new com.kitcut.entity.EmployeeInfo(e.id, e.firstName) FROM Employee e WHERE id = :id";

            Query query = session.createQuery("SELECT e FROM Employee e");
            List employees = query.list();

            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
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

}
