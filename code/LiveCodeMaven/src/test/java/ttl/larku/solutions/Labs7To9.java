package ttl.larku.solutions;

import org.junit.jupiter.api.Test;
import ttl.larku.app.Utils;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author whynot
 */
public class Labs7To9 {

    /**
     * Write a method to calculate the average age of Students who have the status
     * PART_TIME.
     */
    @Test
    public void lab7() {
        int i;

        Integer it = Integer.valueOf(0);
        Integer it2 = 10;

        int r = it + it2;

        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        //List<String> partTime = students.stream()
        OptionalDouble avg = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                //.map(s -> s.getName())
                .mapToDouble(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average();

        double dd = avg.orElse(0);

        double davg = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                //.map(s -> s.getName())
                .mapToDouble(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average().orElse(0);


        Double d = fun();
        if (d != null) {

        } else {

        }
    }


    @Test
    public void terminalVsIntermediateOperations() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        OptionalDouble ds = students.stream()
                .peek(s -> {
                    System.out.println("In Peek 1: " + s);
                })
                .filter(s -> {
                    return s.getStatus() == Student.Status.PART_TIME;
                })
                .peek(s -> System.out.println("In Peek 2: " + s))
                //.map(s -> s.getName())
                .mapToDouble(s -> {
                    return s.getDob().until(LocalDate.now(), ChronoUnit.YEARS);
                })
//                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .peek(s -> {
                    System.out.println("In Peek 2: " + s);
                })
                .average();

    }

    @Test
    public void testOptionReferenceType() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        //Optional<Student> student = students.stream()
        Student student = students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .findFirst().orElseThrow();

    }

    public Double fun() {
        return null;
    }

    /**
     * Write a method to return all the phone numbers of all Students. Make sure your test
     * data includes at least some students with phone numbers.
     */
    @Test
    public void lab8() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        Stream<Student> s1 = students.stream();

        Stream<Stream<String>> s2 = s1.map(s -> s.getPhoneNumbers().stream());

        Stream<String> s3 = s1.flatMap(s -> s.getPhoneNumbers().stream());

        List<String> result1 = s3.collect(toList());

        List<String> result2 = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream().filter(st -> !st.startsWith("202")))
                .collect(toList());
//                .map(s -> s.getPhoneNumbers())
//                .collect(Collectors.toList());

    }

    @Test
    public void lab8WithForLoops() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        List<String> allPhoneNumbers = new ArrayList<>();
        for (Student s : students) {
            for (String phoneNumber : s.getPhoneNumbers()) {
                allPhoneNumbers.add(phoneNumber);
            }
        }


        List<List<String>> strangeList = new ArrayList<>();
        for (Student s : students) {
            strangeList.add(s.getPhoneNumbers());
        }

    }

    /**
     *Write a method to return only the first phone number, if any, for all students. For
     * your test data, make sure that some of your students have multiple phone numbers,
     * and at least one student has no phone numbers.
     */
    @Test
    public void lab9() {
        StudentService ss = new StudentService();
        Utils.fillStudents(ss);

        List<Student> students = ss.getAllStudents();

        List<String> result2 = students.stream()
                .map(s -> s.getPhoneNumbers().stream().findFirst())
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .collect(toList());

//        List<String> result2 = students.stream()
//                .map(s -> s.getPhoneNumbers().stream().findFirst())
//                .collect(toList());
//                .map(s -> s.getPhoneNumbers())
//                .collect(Collectors.toList());

    }


    @Test
    public void blowUPOptional() {
        Optional<String> opt = Optional.empty();

        System.out.println(opt.get());
    }
}
