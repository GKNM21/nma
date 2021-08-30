package ttl.larku.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ttl.larku.domain.Student;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

//To use @DataJpaTest for a Non Repository DAO, you also need to import all the
//application classes you need for the test
//@DataJpaTest
//@Import({StudentDaoService.class, JPAStudentDAO.class})

//Populate your DB.  From Most Expensive to least expensive
//This will make recreate the context after every test.
//In conjunction with appropriate 'schema[-XXX].sql' and 'data[-XXX].sql' files
//it will also drop and recreate the DB before each test.
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

//Or you can just re-run the sql files before each test method
//@Sql(scripts = { "/ttl/larku/db/createDB-h2.sql", "/ttl/larku/db/populateDB-h2.sql" }, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)

//This next one will roll back the transaction after
//each test, so the database will actually stay the
//same for the next test.
@Transactional
public class StudentDAOTest {

	private String name1 = "Bloke";
	private String name2 = "Blokess";
	private String newName = "Different Bloke";
	private Student student1;
	private Student student2;

	@Resource(name = "studentDAO")
//    @Resource (name = "ttl.larku.dao.jpahibernate.JPAStudentDAO")
	private BaseDAO<Student> dao;

	@Autowired
	private ApplicationContext context;

	@BeforeEach
	public void setup() {
		student1 = new Student(name1, "383 939 9393", LocalDate.of(1956, 4, 6), Student.Status.FULL_TIME);
		student2 = new Student(name2, "383 939 54784394", LocalDate.of(1996, 3, 6), Student.Status.PART_TIME);
//        for(String name: context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//        System.out.println(context.getBeanDefinitionCount() + " beans");
	}

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void testGetAll() {
		List<Student> students = dao.getAll();
		assertEquals(4, students.size());
	}

	@Test
	public void testCreate() {

		int newId = dao.create(student1).getId();

		Student resultStudent = dao.get(newId);

		System.out.println(resultStudent);

		assertEquals(newId, resultStudent.getId());
	}

	@Test
	public void testUpdate() {
		int newId = dao.create(student1).getId();

		Student resultStudent = dao.get(newId);

		assertEquals(newId, resultStudent.getId());

		resultStudent.setName(newName);
		dao.update(resultStudent);

		resultStudent = dao.get(resultStudent.getId());
		assertEquals(newName, resultStudent.getName());
	}

	@Test
	public void testDelete() {
		int id1 = dao.create(student1).getId();

		Student resultStudent = dao.get(id1);
		assertEquals(resultStudent.getId(), id1);

		dao.delete(resultStudent);

		resultStudent = dao.get(id1);

		assertEquals(null, resultStudent);

	}
}
