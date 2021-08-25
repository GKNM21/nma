package ttl.larku.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ttl.larku.dao.TheFactory;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

/**
 * @author whynot
 */
public class LambdasAndGenerics {

    private StudentService studentService;

    public static void main(String[] args) {
        LambdasAndGenerics fa = new LambdasAndGenerics();
        fa.callBetterChecker();
    }

    public LambdasAndGenerics() {
//        studentService = new StudentService();
        studentService = TheFactory.getService();
        Utils.fillStudents(studentService);
    }


    public void callBetterChecker() {
    	List<Student> students = studentService.getAllStudents();
    	List<Student> r1 = betterChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);

    	List<Student> r2 = evenBetterChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);

    	List<Student> result = evenEvenBetterChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);
    	for(Student s : result) {
    		System.out.println(s);
    	}
    	
    	List<String> strings = List.of("one", "two", "threeeeee" );
    	
    	List<String> llt3 = evenEvenBetterChecker(strings, s -> s.length() > 3);
    	for(String str : llt3) {
    		System.out.println(str);
    	}
    	
    }

    public void callBestChecker() {
    	List<Student> students = studentService.getAllStudents();
    	List<Student> r1 = betterChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);

    	List<Student> r2 = bestChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);

    	List<Student> result = bestChecker(students, s -> s.getStatus() == Student.Status.HIBERNATING);
    	for(Student s : result) {
    		System.out.println(s);
    	}
    	
    	List<String> strings = List.of("one", "two", "threeeeee" );
    	
    	List<String> llt3 = bestChecker(strings, s -> s.length() > 3);
    	for(String str : llt3) {
    		System.out.println(str);
    	}
    	
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


    @FunctionalInterface
    public interface GenericChecker<T>
    {
        public boolean check(T s);
    }

    public <T> List<T> evenEvenBetterChecker(List<T> input, GenericChecker<T> checker) {
        List<T> result = new ArrayList<>();
        for(T s : input) {
            if(checker.check(s)) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> evenBetterChecker(List<Student> input, GenericChecker<Student> checker) {
        List<Student> result = new ArrayList<>();
        for(Student s : input) {
            if(checker.check(s)) {
                result.add(s);
            }
        }

        return result;
    }

    @FunctionalInterface
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


}
