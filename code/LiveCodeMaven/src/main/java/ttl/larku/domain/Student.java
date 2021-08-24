package ttl.larku.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author whynot
 */
public class Student implements Comparable<Student>{


    public enum Status {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }

    private int id;
    private String name;
    private List<String> phoneNumbers; // = new ArrayList<>();
    private LocalDate dob;
    private Status status = Status.FULL_TIME;
    

//    public Student() {}

    public Student(String name, LocalDate dob, Status status) {
//        this(id, name,  dob, status, phoneNumbers);
        this(name,  dob, status, new ArrayList<String>());
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//        this.status = status;
    }

    public Student(String name, LocalDate dob, Status status, List<String> phoneNumbers) {
//        init(id, name, dob, status, phoneNumbers);
//        this.id = nextId++;

        this.name = name;
        this.dob = dob;
        this.status = status;

        this.phoneNumbers = phoneNumbers;
    }

    public void init(int id, String name,  LocalDate dob, Status status, List<String> phoneNumbers) {
        this.id = id;
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

    public void addPhoneNumber(String pn) {
        phoneNumbers.add(pn);
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", dob=" + dob +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(phoneNumbers, student.phoneNumbers) && Objects.equals(dob, student.dob) && status == student.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumbers, dob, status);
    }

    @Override
    public int compareTo(Student other) {
//        if(this.id < other.id) {
//            return -1;
//        }else if(this.id == other.id) {
//            return 0;
//        }else {
//            return 1;
//        }

        return Integer.compare(this.id, other.id);
    }
}
