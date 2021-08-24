package ttl.larku.app;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

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
        fillStudents(studentService);
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        int id1 = studentService.createStudent(student);

        int id2 = studentService.createStudent("Bonaparte", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);

        List<Student> students = studentService.getAllStudents();
        for (Student s : students) {
            System.out.println(s);
        }

    }

    public List<Student> getRequest() {
        StudentService studentService = new StudentService();
        List<Student> students = studentService.getAllStudents();
        System.out.println("GetRequest");
        for (Student s : students) {
            System.out.println(s);
        }

        return students;
    }


    public static void fillStudents(StudentService service) {
        Student s = new Student("Joseph", LocalDate.of(1990, 10, 10), Student.Status.FULL_TIME);
        service.createStudent(s);

        s = new Student("Yosemite", LocalDate.of(1934, 10, 10), Student.Status.PART_TIME);
        service.createStudent(s);

        s = new Student("Daffy", LocalDate.of(1970, 11, 2), Student.Status.PART_TIME);
        service.createStudent(s);

        s = new Student("Radhika", LocalDate.of(2000, 10, 10), Student.Status.HIBERNATING);
        service.createStudent(s);

        s = new Student("Alice", LocalDate.of(1999, 5, 5), Student.Status.FULL_TIME);
        service.createStudent(s);

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
        for (Student s : students) {
            System.out.println(s);
        }

    }
}
