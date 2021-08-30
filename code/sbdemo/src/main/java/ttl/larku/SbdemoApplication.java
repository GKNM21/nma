package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

import static java.lang.System.out;

//@ComponentScan
//@EnableAutoConfiguration
//@Configuration
//@SpringBootApplication(scanBasePackages = {"ttl.larku.jconfig", "ttl.larku.sbdemo"})
@SpringBootApplication
public class SbdemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SbdemoApplication.class, args);

        out.println(context.getBeanDefinitionCount() + " beans in context");
    }

}

@Component
class MyRunner implements CommandLineRunner
{

    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = studentService.getAllStudents();
        out.println("students");
        students.forEach(out::println);
    }
}
