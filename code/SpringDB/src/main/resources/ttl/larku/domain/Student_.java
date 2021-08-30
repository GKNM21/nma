package ttl.larku.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ttl.larku.domain.Student.Status;

@Generated(value="Dali", date="2021-08-21T17:34:33.298-0400")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Integer> id;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, String> phoneNumber;
	public static volatile SingularAttribute<Student, Status> status;
	public static volatile ListAttribute<Student, ScheduledClass> classes;
}
