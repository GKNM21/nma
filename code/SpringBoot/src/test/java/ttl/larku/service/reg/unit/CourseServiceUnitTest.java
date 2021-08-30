package ttl.larku.service.reg.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;
import ttl.larku.service.CourseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class CourseServiceUnitTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private BaseDAO<Course> courseDAO;

    @Mock
    private List<Course> allCourses;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testCreateCourse() {
        Course c1 = new Course("Math-101", "Intro to Math");
        c1.setId(1);

        Mockito.when(courseDAO.create(c1)).thenReturn(c1);
        Mockito.when(courseDAO.get(1)).thenReturn(c1);

        Course course = courseService.createCourse("Math-101", "Intro to Math");

        Course result = courseService.getCourse(course.getId());

        assertTrue(result.getCode().contains("Math-101"));

        Mockito.verify(courseDAO).create(c1);
        Mockito.verify(courseDAO).get(1);
    }

    @Test
    public void testDeleteCourse() {
        Course c1 = new Course("Math-101", "Intro to Math");
        c1.setId(1);

        Course c2 = new Course("Phys-101", "Intro to Physics");
        c2.setId(2);

        Mockito.when(courseDAO.create(c1)).thenAnswer(inv -> c1);
        Mockito.when(courseDAO.create(c2)).thenAnswer(inv -> c2);

        Mockito.when(courseDAO.getAll()).thenReturn(allCourses);
        Mockito.when(allCourses.size()).thenReturn(2).thenReturn(1);
        Mockito.when(courseDAO.get(1)).thenReturn(c1);


        Mockito.doNothing().when(courseDAO).delete(c1);

        Course course1 = courseService.createCourse("Math-101", "Intro to Math");
        Course course2 = courseService.createCourse("Phys-101", "Intro to Physics");

        assertEquals(2, courseService.getAllCourses().size());

        courseService.deleteCourse(course1.getId());

        assertEquals(1, courseService.getAllCourses().size());

//        Mockito.verify(courseDAO, atMost(3)).getAll();

        Mockito.verify(courseDAO, atLeastOnce()).create(c1);
        Mockito.verify(courseDAO, atLeastOnce()).create(c2);

        Mockito.verify(courseDAO, atLeastOnce()).delete(c1);
    }

    @Test
    public void testDeleteNonExistentCourse() {
        //Non existent Id
        courseService.deleteCourse(9999);
        Mockito.verify(courseDAO, never()).delete(any());

    }

    @Test
    public void testUpdateCourse() {
        Course c1 = new Course("Math-101", "Intro to Math");
        c1.setId(1);

        Mockito.when(courseDAO.create(c1)).thenAnswer(inv -> c1);

        Mockito.when(courseDAO.getAll()).thenReturn(allCourses);
        Mockito.when(allCourses.size()).thenReturn(1);

        Mockito.doNothing().when(courseDAO).update(c1);

        Course course1 = courseService.createCourse("Math-101", "Intro to Math");

        assertEquals(1, courseService.getAllCourses().size());

        course1.setCode("Math-202");
        courseService.updateCourse(course1);

        Mockito.verify(courseDAO, atMostOnce()).create(c1);
        Mockito.verify(courseDAO, atMostOnce()).update(c1);

    }

    @Test
    public void testGetByCourseCode() {
        Course c1 = new Course("MATH-101", "Intro to Math");
        c1.setId(1);
        List<Course> ls = List.of(c1);
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> ls);

        Course c = courseService.getCourseByCode("MATH-101");
        assertNotNull(c);
        assertEquals("MATH-101", c.getCode());

        Mockito.verify(courseDAO).findBy(any());
    }

    private void assertNotNull(Course c) {
    }

    @Test
    public void testGetByNonExistentCourseCode() {
        Mockito.when(courseDAO.findBy(any())).thenAnswer(inv -> List.of());

        Course c = courseService.getCourseByCode("NOT THERE");
        assertNull(c);

        Mockito.verify(courseDAO).findBy(any());
    }
}
