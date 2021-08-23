package ttl.larku.app;

import java.time.LocalDate;
import java.util.List;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;

/**
 * @author whynot
 */
public class RegistrationApp {

    public static void main(String[] args) {
    	testDAO();
    }
    
    public static void testStudent() {
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);

        System.out.println("s: " + student);

    }
    
    
    public  static void testDAO() {
    	StudentDAO sd = new StudentDAO();
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        sd.insert(student);

        student = new Student("Bonaparte", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        sd.insert(student);
        
        List<Student> students = sd.getAll();
        for(Student s : students) {
        	System.out.println(s);
        }

    }
}
