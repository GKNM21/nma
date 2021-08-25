package ttl.larku.app;

import ttl.larku.dao.TheFactory;
import ttl.larku.dao.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class PreSpringApp {

    private StudentService studentService = TheFactory.getService();

    public static void main(String[] args) {
        //testDAO();
        PreSpringApp rapp = new PreSpringApp();
        rapp.postRequest();

        rapp.getRequest();
    }

    public void postRequest() {
        Utils.fillStudents(studentService);
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);
        int id1 = studentService.createStudent(student);

        int id2 = studentService.createStudent("Bonaparte", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);

        List<Student> students = studentService.getAllStudents();
        for (Student s : students) {
            System.out.println(s);
        }

    }

    public List<Student> getRequest() {
        List<Student> students = studentService.getAllStudents();
        System.out.println("GetRequest");
        for (Student s : students) {
            System.out.println(s);
        }

        return students;
    }


    public static void testStudent() {
        Student student = new Student("Josephine", LocalDate.of(1970, 2, 2), Student.Status.FULL_TIME);

        System.out.println("s: " + student);

    }


    public static void testDAO() {
        InMemoryStudentDAO sd = new InMemoryStudentDAO();
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
