package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class Streams1 {

    private StudentService studentService;

    public static void main(String[] args) {
        Streams1 fa = new Streams1();
        fa.callBetterExtractor();
    }

    public Streams1() {
        studentService = new StudentService();
        Utils.fillStudents(studentService);
    }


    public void callBetterExtractor() {
        List<Student> students = studentService.getAllStudents();

        List<Student> hiber = bestChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);
        List<String> allNames = bestExtractor(students, s -> s.getName());

        List<String> namesOfHibernators =
                bestExtractor(bestChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING),
                        s -> s.getName());

        List<String> namesOfHibernators2 = students.stream()
                .peek(s -> System.out.println("Peek 1: " + s))
                .filter(s -> s.getStatus() == Student.Status.HIBERNATING)
                .peek(s -> System.out.println("Peek 2: " + s))
                .map(s -> s.getName())
                .peek(s -> System.out.println("Peek 3: " + s))
                .collect(Collectors.toList());
        namesOfHibernators2.forEach(System.out::println);

        String csvList = students.stream()
                .peek(s -> System.out.println("Peek 1: " + s))
                .filter(s -> s.getStatus() == Student.Status.HIBERNATING || s.getStatus() == Student.Status.PART_TIME)
                .peek(s -> System.out.println("Peek 2: " + s))
//                .map(s -> s.getName())
                .map(this::complicatedMapping)
                .peek(s -> System.out.println("Peek 3: " + s))
                .collect(Collectors.joining(", "));
        System.out.println("csvList: " + csvList);

        long hibNum = students.stream()
                .peek(s -> System.out.println("Peek 1: " + s))
                .filter(s -> s.getStatus() == Student.Status.HIBERNATING)
                .peek(s -> System.out.println("Peek 2: " + s))
                .count();

        System.out.println("hibNum: " + hibNum);


    }

    public String complicatedMapping(Student s) {
        //complicated stuff
        return "blah";
    }

    public <T> List<T> bestChecker(List<T> input, Predicate<T> checker) {
        List<T> result = new ArrayList<>();
        for(T s : input) {
            if(checker.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public <T, R> List<R> bestExtractor(List<T> input, Function<T, R> picker) {
        List<R> result = new ArrayList<>();
        for(T s : input) {
            result.add(picker.apply(s));
        }
        return result;
    }

    @FunctionalInterface
    interface GenericPicker<T, R> {
        public R takeProperty(T student);
    }

    public <T, R> List<R> almostBestExtractor(List<T> input, GenericPicker<T, R> picker) {
        List<R> result = new ArrayList<>();
        for(T s : input) {
            result.add(picker.takeProperty(s));
        }
        return result;
    }

    public List<String> almostBestExtractorNotReally(List<Student> input, GenericPicker<Student, String> picker) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(picker.takeProperty(s));
        }
        return result;
    }

    @FunctionalInterface
    interface Picker {
        public String takeProperty(Student student);
    }

    public List<String> betterExtractor(List<Student> input, Picker picker) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(picker.takeProperty(s));
        }
        return result;
    }

    public List<String> extract(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(s.getName());
        }
        return result;
    }

    public List<String> extractOtherProp(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(s.getOtherProperty());
        }
        return result;
    }


}
