package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public interface BaseDAO {
    int insert(Student s);

    boolean update(Student s);

    boolean delete(int id);

    Student get(int id);

    List<Student> getAll();
}
