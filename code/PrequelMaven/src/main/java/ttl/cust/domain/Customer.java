package ttl.cust.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author whynot
 */
public class Customer {

    public enum Status {
        Privileged,
        Normal,
        Restricted
    }

    private int id;
    private String name;
    private LocalDate dob;
    private Status status;
    private List<String> phoneNumbers;

    public Customer(String name, LocalDate dob, Status status) {
        this(name, dob, status, new ArrayList<>());
    }

    public Customer(String name, LocalDate dob, Status status, List<String> phoneNumbers) {
        this.name = name;
        this.dob = dob;
        this.status = status;
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", status=" + status +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(dob, customer.dob) && status == customer.status && Objects.equals(phoneNumbers, customer.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dob, status, phoneNumbers);
    }
}
