package ttl.larku.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ttl.larku.domain.StudentVersioned.Status;

@Generated(value="Dali", date="2021-08-21T17:34:33.300-0400")
@StaticMetamodel(StudentVersioned.class)
public class StudentVersioned_ {
	public static volatile SingularAttribute<StudentVersioned, Long> version;
	public static volatile SingularAttribute<StudentVersioned, Integer> id;
	public static volatile SingularAttribute<StudentVersioned, String> name;
	public static volatile SingularAttribute<StudentVersioned, String> phoneNumber;
	public static volatile SingularAttribute<StudentVersioned, Status> status;
}
