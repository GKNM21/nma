package ttl.larku.app;

import java.time.LocalDate;
import java.util.List;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

/**
 * @author whynot
 */
public class RegistrationApp {

//	private StudentService studentService = new StudentService();
    public static void main(String[] args) {
    	//testDAO();
    	RegistrationApp rapp = new RegistrationApp();
    	rapp.postRequest();
    	
    	rapp.getRequest();
    }

    public void postRequest() {
    	StudentService studentService = new StudentService();
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        int id1 = studentService.createStudent(student);

        int id2 = studentService.createStudent("Bonaparte", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        
        List<Student> students = studentService.getAllStudents();
        for(Student s : students) {
        	System.out.println(s);
        }

    }
    
    public List<Student> getRequest() {
    	StudentService studentService = new StudentService();
        List<Student> students = studentService.getAllStudents();
        System.out.println("GetRequest");
        for(Student s : students) {
        	System.out.println(s);
        }
        
        return students;
    }


    
    public static void testStudent() {
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);

        System.out.println("s: " + student);

    }
    
    
    public static void testDAO() {
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
