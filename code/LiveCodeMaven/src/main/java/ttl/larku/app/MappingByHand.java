package ttl.larku.app;

import ttl.larku.dao.TheFactory;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author whynot
 */
public class MappingByHand {

    private StudentService studentService;

    public static void main(String[] args) {
        MappingByHand fa = new MappingByHand();
        fa.callBetterExtractor();
    }

    public MappingByHand() {
//        studentService = new StudentService();
        studentService = TheFactory.getService();
        Utils.fillStudents(studentService);
    }

    public void callBadExtractor() {
        List<Student> students = studentService.getAllStudents();

        List<String> names = extract(students);
        names.forEach(System.out::println);
    }

    public void callBetterExtractor() {
        List<Student> students = studentService.getAllStudents();

        List<String> names = betterExtractor(students, s -> s.getName());
//        names.forEach(System.out::println);

        List<String> oprop = betterExtractor(students, s -> s.getOtherProperty());
//        names.forEach(System.out::println);

        List<String> oprop2 = almostBestExtractorNotReally(students, s -> s.getOtherProperty());
//        names.forEach(System.out::println);

        List<String> oprop3 = almostBestExtractor(students, s -> s.getOtherProperty());
//        names.forEach(System.out::println);

        List<Student.Status> statuses = almostBestExtractor(students, s -> s.getStatus());

        List<String> strings = List.of("one", "two", "threeeee");
        List<Integer> lengths = almostBestExtractor(strings, s -> s.length());

        lengths.forEach(System.out::println);

        List<Integer> lengths2 = bestExtractor(strings, s -> s.length());

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


    public <T> List<T> bestChecker(List<T> input, Predicate<T> checker) {
        List<T> result = new ArrayList<>();
        for(T s : input) {
            if(checker.test(s)) {
                result.add(s);
            }
        }

        return result;
    }
}
