package ttl.cust.solutions;

import org.junit.jupiter.api.Test;
import ttl.cust.app.CustomerApp;
import ttl.cust.domain.Customer;
import ttl.cust.service.CustomerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
public class Labs1to3 {

    @Test
    public void lab1() {
        CustomerService service = new CustomerService();
        CustomerApp.fillCustomers(service);
        List<Customer> customers = service.getAllCustomers();

        Collections.sort(customers);
        customers.forEach(System.out::println);

        assertEquals(5, customers.size());
    }

    @Test
    public void lab2() {
        CustomerService service = new CustomerService();
        CustomerApp.fillCustomers(service);
        List<Customer> customers = service.getAllCustomers();

        Collections.sort(customers, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        customers.forEach(System.out::println);

        assertEquals("Alice", customers.get(0).getName());
    }

    @Test
    public void lab3() {
        CustomerService service = new CustomerService();
        CustomerApp.fillCustomers(service);
        List<Customer> customers = service.getAllCustomers();

        List<Customer> privileged = findBy(customers, c -> c.getStatus() == Customer.Status.Privileged);
        privileged.forEach(System.out::println);

        assertEquals(2, privileged.size());
    }

    public List<Customer> findBy(List<Customer> input, Predicate<Customer> pred) {
        List<Customer> result = new ArrayList<>();
        for(Customer c : input) {
            if(pred.test(c)) {
                result.add(c);
            }
        }
        return result;
    }
}
