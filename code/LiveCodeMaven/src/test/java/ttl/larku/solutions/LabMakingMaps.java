package ttl.larku.solutions;

import org.junit.jupiter.api.Test;
import ttl.larku.app.Utils;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author whynot
 */
public class LabMakingMaps {

    /**
     * Write a method to calculate the average age of Students who have the status
     * PART_TIME.
     */
    @Test
    public void listToMap() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        Map<Integer, List<Student>> m1 =  students.stream()
                .collect(Collectors.groupingBy(s -> s.getId()));

        m1.forEach((k, v) -> System.out.println(k + ": " + v));

        Map<Student.Status, List<Student>> m5 =  students.stream()
                .collect(Collectors.groupingBy(s -> s.getStatus()));

        Map<Student.Status, Long> m6 =  students.stream()
                .collect(Collectors.groupingBy(s -> s.getStatus(), Collectors.counting()));

        m1.forEach((k, v) -> System.out.println(k + ": " + v));

        Map<Integer, Student> m2 =  students.stream()
                .collect(Collectors.toMap(s -> s.getId(), s -> s));

//        Map<Student.Status, Student> m4 =  students.stream()
//                .collect(Collectors.toMap(s -> s.getStatus(), s -> s));

        Map<Student.Status, Student> m3 =  students.stream()
                .collect(Collectors.toMap(s -> {
                   return  s.getStatus();
                }, s -> {
                    return s;
                }, (v1, v2) -> {
                   return  v1;
                }));

        m3.forEach((k, v) -> System.out.println(k + ": " + v));
    }

}