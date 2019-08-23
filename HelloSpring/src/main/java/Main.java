import com.kitcut.bean.GreetingService;
import com.kitcut.bean.HelloWorld;
import com.kitcut.config.AppConfiguration;
import com.kitcut.dao.JDBCExample;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        GreetingService greetingService = ctx.getBean(GreetingService.class);

        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        helloWorld.setMessage("Hello World!");
        helloWorld.getMessage();

//        Student student = (Student) context.getBean("student");
//        student.getName();
//        student.getAge();
//        student.printThrowException();

        JDBCExample.excute();
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        StudentDAO studentJDBCTemplate =
//                (StudentDAO)context.getBean("studentJDBCTemplate");
//
//        System.out.println("------Records creation--------" );
//        studentJDBCTemplate.create("Zara", 11, 99, 2010);
////        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
////        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
//
//        System.out.println("------Listing all the records--------" );
//        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
//
//        for (StudentMarks record : studentMarks) {
//            System.out.print("ID : " + record.getId() );
//            System.out.print(", Name : " + record.getName() );
//            System.out.print(", Marks : " + record.getMarks());
//            System.out.print(", Year : " + record.getYear());
//            System.out.println(", Age : " + record.getAge());
//        }

    }
}
