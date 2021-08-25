package ttl.cust.service;

import java.time.LocalDate;
import java.util.List;

import ttl.cust.dao.CustomerDAO;
import ttl.cust.domain.Customer;

/**
 * @author whynot
 */
public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();
    public int addCustomer(String name, LocalDate dob, Customer.Status status) {
       return addCustomer(new Customer(name, dob, status));
    }

    public int addCustomer(Customer customer) {
       return customerDAO.insert(customer);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }

    public boolean deleteCustomer(int id) {
        return customerDAO.delete(id);
    }

    public Customer getCustomer(int id) {
        return customerDAO.get(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
}
