package ttl.larku.app;

import ttl.larku.dao.TheFactory;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
//        studentService = new StudentService();
        studentService = TheFactory.getService();
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

        Collections.sort(students, (s1, s2) -> {
            //Lots of code
            return s1.getName().compareTo(s2.getName());
        });


        Collections.sort(students, this::complicatedComparison);

        System.out.println("By name");

        //students.forEach(s -> System.out.println(s));
//        students.forEach(System.out::println);

        students.forEach(this::prettyPrint);
        students.forEach(s -> this.prettyPrint(s));

//        for(Student s : students) {
//            System.out.println(s);
//        }
    }

    public int complicatedComparison(Student s1, Student s2) {
        ////Lots of code
        return s1.getName().compareTo(s2.getName());
    }


    public int pp(String s) {
        return 10;
    }
    public void prettyPrint(Student st) {
        System.out.println("<<<" + st + ">>>");
    }



}
