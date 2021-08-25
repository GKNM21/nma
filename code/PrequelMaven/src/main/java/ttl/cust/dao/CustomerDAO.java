package ttl.cust.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ttl.cust.domain.Customer;

/**
 * @author whynot
 */
public class CustomerDAO {

    private Map<Integer, Customer> customers = new ConcurrentHashMap<>();
    private static AtomicInteger nextId = new AtomicInteger(1);

    public int insert(String name, LocalDate dob, Customer.Status status) {
        return insert(new Customer(name, dob, status));
    }

    public int insert(Customer customer) {
        int id = nextId.getAndIncrement();
        customer.setId(id);
        customers.put(id, customer);
        return id;
    }

    public boolean update(Customer customer) {
        Customer oldCustomer = customers.get(customer.getId());
        if(oldCustomer != null) {
            customers.put(customer.getId(), customer);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return customers.remove(id) != null;
    }

    public Customer get(int id) {
        return customers.get(id);
    }

    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }
}
