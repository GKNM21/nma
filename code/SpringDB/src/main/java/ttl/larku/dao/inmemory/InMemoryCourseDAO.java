package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCourseDAO implements BaseDAO<Course> {

    private Map<Integer, Course> courses = new ConcurrentHashMap<Integer, Course>();
    private AtomicInteger nextId;

    public InMemoryCourseDAO() {
        nextId = new AtomicInteger(1);
    }

    @Override
    public void update(Course updateObject) {
        courses.computeIfPresent(updateObject.getId(), (key, oldValue) -> updateObject);
    }

    @Override
    public void delete(Course course) {
        courses.remove(course.getId());
    }

    @Override
    public Course create(Course newObject) {
        //Create a new Id
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        courses.put(newId, newObject);

        return newObject;
    }

    @Override
    public Course get(int id) {
        return courses.get(id);
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<Course>(courses.values());
    }

    @Override
    public void deleteStore() {
        courses = null;
    }

    @Override
    public void createStore() {
        courses = new HashMap<Integer, Course>();
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, Course> courses) {
        this.courses = courses;
    }
}
