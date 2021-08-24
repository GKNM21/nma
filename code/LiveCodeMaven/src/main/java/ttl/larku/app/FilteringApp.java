package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whynot
 */
public class FilteringApp {

    private StudentService studentService;

    public static void main(String[] args) {
        FilteringApp fa = new FilteringApp();
//        fa.callBadChecker();
//        fa.callBetterChecker();
        fa.callBetterCheckerWithLambdas();
    }

    public FilteringApp() {
        studentService = new StudentService();
        Utils.fillStudents(studentService);
    }

    public void callBadChecker() {
        List<Student> students = studentService.getAllStudents();

        List<Student> withM = firstNameStartingWithJ(students, "J");
        System.out.println("Students:");
        for(Student s : withM) {
            System.out.println(s);
        }
    }

    public void callBetterChecker() {
        List<Student> students = studentService.getAllStudents();
        Checker sc = new StatusChecker();
        Checker sm = new StudentsWithM();

        Checker ageGreaterThan50 = new Checker() {
            @Override
            public boolean check(Student s) {
                long age = s.getDob().until(LocalDate.now(), ChronoUnit.YEARS);
                return age > 50;
            }
        };


//        List<Student> result = betterChecker(students, sc);
        //List<Student> result = betterChecker(students, sm);
        List<Student> result = betterChecker(students, ageGreaterThan50);
        System.out.println("Students:");
        for(Student s : result) {
            System.out.println(s);
        }

    }

    public void callBetterCheckerWithLambdas() {
        List<Student> students = studentService.getAllStudents();
        Checker sc = new StatusChecker();
        Checker sm = new StudentsWithM();

        Checker ageGreaterThan50 = new Checker() {
            @Override
            public boolean check(Student s) {
                long age = s.getDob().until(LocalDate.now(), ChronoUnit.YEARS);
                return age > 50;
            }
        };

        Checker ageGreaterThan50AsLambda = (Student s) -> {
//                long age = s.getDob().until(LocalDate.now(), ChronoUnit.YEARS);
//                return age > 50;
                return s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 50;
            };

        Checker c1 = (Student s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 50;

        Checker c2 = (s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 50;

        Checker c3 = s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 50;


//        List<Student> result = betterChecker(students, sc);
        //List<Student> result = betterChecker(students, sm);
        //List<Student> result = betterChecker(students, ageGreaterThan50);
//        List<Student> result = betterChecker(students, c3);
        List<Student> result = betterChecker(students,
                s -> {
                    boolean yesno = s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 50;
                    ///Lots of other work
                    return yesno;
                });
        System.out.println("Students:");
        for(Student s : result) {
            System.out.println(s);
        }

    }

    class StudentsWithM implements Checker {
        @Override
        public boolean check(Student s) {
            return s.getName().startsWith("J");
        }
    }

    class StatusChecker implements Checker {
        @Override
        public boolean check(Student s) {
            return s.getStatus() == Student.Status.FULL_TIME;
        }
    }

    public interface Checker
    {
        public boolean check(Student s);
    }


    public List<Student> betterChecker(List<Student> input, Checker checker) {
        List<Student> result = new ArrayList<>();
        for(Student s : input) {
            if(checker.check(s)) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> firstNameStartingWithJ(List<Student> input, String prefix) {
        List<Student> result = new ArrayList<>();
        for(Student s : input) {
            if(s.getName().startsWith(prefix)) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> findWithStatus(List<Student> input, Student.Status status) {
        List<Student> result = new ArrayList<>();
        for(Student s : input) {
            if(s.getStatus() == status) {
                result.add(s);
            }
        }

        return result;
    }
}
