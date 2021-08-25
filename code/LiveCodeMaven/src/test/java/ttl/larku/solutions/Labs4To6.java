package ttl.larku.solutions;

import org.junit.jupiter.api.Test;
import ttl.larku.app.Utils;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author whynot
 */
public class Labs4To6 {

    @Test
    public void lab4() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        //List<String> partTime = students.stream()
        List<LocalDate> partTime = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                //.map(s -> s.getName())
                .map(Student::getDob)
                .collect(toList());

    }

    /**
     * Write a method to return a list of the ages of all Students who have a status of
     * HIBERNATING.
     */
    @Test
    public void lab5() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        //List<Long> listOfAges = students.stream()
        Set<Long> listOfAges = students.stream()
                .filter(s -> s.getStatus() == Student.Status.HIBERNATING)
                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .collect(toSet());


    }

    /**
     * Write a method to return the number of students who are 20 years or older. To
     * calculate number of years from a LocalDate use:
     * myDate.until(LocalDate.now(), ChronoUnit.YEARS)
     */
    @Test
    public void lab6() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        long count = students.stream()
                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20)
                .collect(counting());

    }
}
