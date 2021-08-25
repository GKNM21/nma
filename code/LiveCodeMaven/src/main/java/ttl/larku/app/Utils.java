package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;

/**
 * @author whynot
 */
public class Utils {

    public static void fillStudents(StudentService service) {
        Student s = new Student("Joseph", LocalDate.of(1990, 10, 10), Student.Status.FULL_TIME,
                "202 034030 03303", "383 83839 93");
        service.createStudent(s);

        s = new Student("Yosemite", LocalDate.of(1934, 10, 10), Student.Status.PART_TIME);
        service.createStudent(s);

        s = new Student("Daffy", LocalDate.of(1970, 11, 2), Student.Status.PART_TIME, "908 4383 93939");
        service.createStudent(s);

        s = new Student("Radhika", LocalDate.of(2000, 10, 10), Student.Status.HIBERNATING, "38 93 39 0202",
                "383 903 393030");
        service.createStudent(s);

        s = new Student("Alice", LocalDate.of(1999, 5, 5), Student.Status.FULL_TIME);
        service.createStudent(s);

    }
}
