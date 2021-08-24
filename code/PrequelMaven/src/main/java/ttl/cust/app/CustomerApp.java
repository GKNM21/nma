package ttl.cust.app;

import ttl.cust.Customer;
import ttl.cust.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class CustomerApp {

    private CustomerService customerService = new CustomerService();
    public static void main(String[] args) {
        System.out.println("Customers ahoy");
        CustomerApp ca = new CustomerApp();
        fillCustomers(ca.customerService);

        Customer c = new Customer("Joseph", LocalDate.of(1990, 10, 10), Customer.Status.Privileged);
        ca.postACustomer(c);

        //Some time later, another request
        ca.getAllCustomers();
    }

    public void postACustomer(Customer newCustomer) {
       int newId = customerService.addCustomer(newCustomer);

        List<Customer> custs = customerService.getAllCustomers();
        System.out.println("Customers in Post");
        for(Customer c : custs) {
            System.out.println(c);
        }
    }

    public void getAllCustomers() {

        List<Customer> custs = customerService.getAllCustomers();
        System.out.println("Customers in Get All");
        for(Customer c : custs) {
            System.out.println(c);
        }
    }

    public static void fillCustomers(CustomerService service) {
        Customer c = new Customer("Joseph", LocalDate.of(1990, 10, 10), Customer.Status.Privileged);
        service.addCustomer(c);

        c = new Customer("Yosemite", LocalDate.of(1934, 10, 10), Customer.Status.Privileged);
        service.addCustomer(c);

        c = new Customer("Daffy", LocalDate.of(1970, 11, 2), Customer.Status.Restricted);
        service.addCustomer(c);

        c = new Customer("Radhika", LocalDate.of(2000, 10, 10), Customer.Status.Normal);
        service.addCustomer(c);

        c = new Customer("Alice", LocalDate.of(1999, 5, 5), Customer.Status.Normal);
        service.addCustomer(c);
    }
}
