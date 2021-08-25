package ttl.larku.service;

import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.TheFactory;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

//	private InMemoryStudentDAO studentDAO = new InMemoryStudentDAO();
	//private BaseDAO studentDAO = new InMemoryStudentDAO();
//	private BaseDAO studentDAO = new JpaStudentDAO();


	private BaseDAO studentDAO;   // = DAOFactory.getDAO();

	public StudentService() {
		studentDAO = TheFactory.getDAO();
	}

	public StudentService(BaseDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

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

	public BaseDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(BaseDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
}
