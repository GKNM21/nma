package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.ScheduledClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryClassDAO implements BaseDAO<ScheduledClass> {

    private Map<Integer, ScheduledClass> classes = new HashMap<Integer, ScheduledClass>();
    private AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public void update(ScheduledClass updateObject) {
        classes.computeIfPresent(updateObject.getId(), (key, oldValue) -> updateObject);
    }

    @Override
    public void delete(ScheduledClass sc) {
        classes.remove(sc.getId());
    }

    @Override
    public ScheduledClass create(ScheduledClass newObject) {
        //Create a new Id
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        classes.put(newId, newObject);

        return newObject;
    }

    @Override
    public ScheduledClass get(int id) {
        return classes.get(id);
    }

    @Override
    public List<ScheduledClass> getAll() {
        return new ArrayList<ScheduledClass>(classes.values());
    }

    @Override
    public void deleteStore() {
        classes = null;
    }

    @Override
    public void createStore() {
        classes = new HashMap<Integer, ScheduledClass>();
    }

    public void setClasses(Map<Integer, ScheduledClass> classes) {
        this.classes = classes;
    }
}
