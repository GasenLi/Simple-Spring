import Entity.Class;
import Entity.Student;
import org.gasen.IOC.Factory.ApplicationContext;

import java.util.Date;

public class SpringTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("Student");
        Class c = (Class) applicationContext.getBean("Class");


        System.out.println(student);
        System.out.println(student.getTheClass());
        System.out.println(c);

    }
}
