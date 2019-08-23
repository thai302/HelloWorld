package com.kitcut.dao;

import com.kitcut.entity.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AddressDAO extends BaseDAO {

    public AddressDAO(SessionFactory sessionFactory){
        this.factory = sessionFactory;
    }

    /* Method to add an address record in the database */
    public Address addAddress(String street, String city, String state, String zipcode) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer addressID = null;
        Address address = null;

        try {
            tx = session.beginTransaction();
            address = new Address(street, city, state, zipcode);
            addressID = (Integer) session.save(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return address;
    }

    public Address getAdress(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer addressID = null;
        Address address = null;

        try {
            tx = session.beginTransaction();
            address = (Address) session.createQuery("FROM Address").list().get(0);
            address.setCity("ha noi3");
//            addressID = (Integer) session.save(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return address;
    }
}
