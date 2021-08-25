package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class SpringDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        StudentService ss = context.getBean("studentService", StudentService.class);

        List<Student> students = ss.getAllStudents();
        out.println("Student:");
        students.forEach(out::println);
    }
}
