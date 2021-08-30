package ttl.larku.controller;


import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/sbdemo/student")
public class StudentController {
    private final StudentService studentService;

//    @GetMapping
//    public String doIt() {
//        return "Will this really work?????";
//    }
    //Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
       List<Student> students = studentService.getAllStudents();
       return students;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        return student;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);

        return newStudent;
    }
}
