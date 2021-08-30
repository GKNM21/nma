package ttl.larku.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.jpahibernate.JPAClassDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.ScheduledClass;

public class ClassDaoService implements ClassService {

    private CourseService courseService;
    private JPAClassDAO classDAO;


    @Override
    public ScheduledClass createScheduledClass(String courseCode, String startDate, String endDate) {
        Course course = courseService.getCourseByCode(courseCode);
        if (course != null) {
            ScheduledClass sClass = new ScheduledClass(course, startDate, endDate);
            sClass = classDAO.create(sClass);
            return sClass;
        }
        return null;
    }

    @Override
    public void deleteScheduledClass(int id) {
        ScheduledClass course = classDAO.get(id);
        if (course != null) {
            classDAO.delete(course);
        }
    }

    @Override
    public void updateScheduledClass(ScheduledClass course) {
        classDAO.update(course);
    }

    @Override
    public List<ScheduledClass> getScheduledClassesByCourseCode(String code) {
        List<ScheduledClass> result = classDAO.getByCourseCode(code);

        return result;
    }

    @Override
    public List<ScheduledClass> getScheduledClassesByCourseCodeAndStartDate(String code, String startDate) {
        List<ScheduledClass> result = classDAO.getByCourseCodeAndStartDateForStudents(code, startDate);
        return result;
    }

    @Override
    public ScheduledClass getScheduledClass(int id) {
        return classDAO.get(id);
    }

    @Override
    public List<ScheduledClass> getAllScheduledClasses() {
        return classDAO.getAll();
    }

    public BaseDAO<ScheduledClass> getClassDAO() {
        return classDAO;
    }

    public void setClassDAO(JPAClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void clear() {
        classDAO.deleteStore();
        classDAO.createStore();
    }
}
