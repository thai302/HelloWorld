import com.kitcut.dao.EmployeeDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) {

        //Jdbc
//        Jdbc.executeBatch();

        //JPA
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
//        EntityManager entitymanager = emfactory.createEntityManager();
////        SubEmployee subEmployee = new SubEmployee();
////        subEmployee.setSubId(1);
////        subEmployee.setFirstName("a");
////        entitymanager.persist(subEmployee);
//        Employee employee = entitymanager.find(Employee.class, 1);

//        Query query = entitymanager.createQuery("SELECT e FROM Employee e WHERE id = :id", Employee.class);
//        Query query = entitymanager.createNamedQuery("test", Employee.class);
//        query.setParameter("id", 1);
//        List<Employee> list = query.getResultList();

        //Hibernate
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//            factory = new AnnotationConfiguration().
//                    configure().
//                    //addPackage("com.xyz") //add package if used.
//                            addAnnotatedClass(Employee.class).
//                            buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        EmployeeDAO ME = new EmployeeDAO(factory);
        /* Add few employee records in database */
//        Integer empID1 = ME.addEmployee("firstName", "Ali", 1000);
        ME.testSecondLevelCache();
    }


}
