package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author whynot
 */
public class Sorting {

    private StudentService studentService;

    public static void main(String[] args) {
        Sorting fa = new Sorting();
        fa.sortByNaturalOrder();
    }

    public Sorting() {
        studentService = new StudentService();
        Utils.fillStudents(studentService);
    }

    public static <T extends Comparable<T>> void mysort(List<T> list) {

    }

    public static <T> void sort(List<T> list, Comparator<T> c) {

    }

    public void sortByNaturalOrder() {
    	List<Student> students = studentService.getAllStudents();
    	
    	Collections.sort(students);

    	for(Student s : students) {
            System.out.println(s);
        }

    	Comparator<Student> byName = (s1, s2) -> s1.getName().compareTo(s2.getName());

    	Collections.sort(students, byName);

        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));

        System.out.println("By name");
        for(Student s : students) {
            System.out.println(s);
        }
    }


    //int 	compare(T o1, T o2)

}
