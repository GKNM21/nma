package ttl.larku.service;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

	private StudentDAO studentDAO = new StudentDAO();
	
	public int createStudent(String name, LocalDate dob, Status status) {
		Student s = new Student(name, dob, status);
		return createStudent(s);
	}
	
	public int createStudent(Student student) {
		studentDAO.insert(student);
		return student.getId();
	}
	
	public Student getStudent(int id) {
		return studentDAO.get(id);
		
	}

	public List<Student> getAllStudents() {
		return studentDAO.getAll();
	}
}
