import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Student implements  Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private int age;
    private byte[] bytes = new byte[]{1, 2, 12};
//    private StudentAbstract studentAbstract;

    public Student() {
    }

    public Student(int id, String name, String address, int age) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

}
