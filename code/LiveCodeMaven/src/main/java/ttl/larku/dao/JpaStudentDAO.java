package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JpaStudentDAO implements BaseDAO{

	private Map<Integer, Student> students = new ConcurrentHashMap<>();
    //private static int nextId = 1;
    private static AtomicInteger nextId = new AtomicInteger(1);

	public int insert(Student s) {
        int id = nextId.getAndIncrement();
        s.setId(id);
        s.setName("JPA-" + s.getName());
		students.put(id, s);
		
		return id;
		
	}

	public boolean update(Student s) {
		Student oldStudent = students.get(s.getId());
		if (oldStudent != null) {
			students.put(s.getId(), s);
			return true;
		}
		return false;
	}

	public boolean delete(int id) {
		return students.remove(id) != null;
	}

	public Student get(int id) {
		return students.get(id);
	}

	public List<Student> getAll() {
		return new ArrayList<>(students.values());
	}

}
