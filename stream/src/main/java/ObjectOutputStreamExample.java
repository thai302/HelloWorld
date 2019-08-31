import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamExample {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("D:\\testout.txt"));
            // create student
            Student student = new Student(1, "Tran Hao Phong", "Ha Noi", 17);
            StudentAbstract studentAbstract = new Student1();
            ((Student1) studentAbstract).setAddress("hehe");
//            student.setStudentAbstract(studentAbstract);
            //            student.setX(1);
            // write student
            byte[] bytes = serialize(student);
            oos.writeObject(bytes);
//            oos.writeObject(student);
            System.out.println("Success...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            oos.close();
        }
    }

    private static byte[] serialize(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(obj);
    }
}
