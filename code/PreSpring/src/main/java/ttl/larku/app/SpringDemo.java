package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class SpringDemo {

    public static void main(String[] args) {
//        studentStuff();
        courseStuff();
    }

    public static void courseStuff() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        CourseService cs = context.getBean("courseService", CourseService.class);
        RegistrationApp.init(cs);

        List<Course> courses = cs.getAllCourses();
        out.println("Courses:");
        courses.forEach(out::println);

    }

    public static void studentStuff() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        StudentService ss = context.getBean("studentService", StudentService.class);

        StudentService ss2 = context.getBean("studentService", StudentService.class);

        List<Student> students = ss.getAllStudents();
        out.println("Student:");
        students.forEach(out::println);

    }
}
