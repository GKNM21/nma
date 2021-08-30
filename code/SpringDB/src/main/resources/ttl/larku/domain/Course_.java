package ttl.larku.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-08-21T17:34:33.247-0400")
@StaticMetamodel(Course.class)
public class Course_ {
	public static volatile SingularAttribute<Course, Integer> id;
	public static volatile SingularAttribute<Course, String> title;
	public static volatile SingularAttribute<Course, String> code;
	public static volatile SingularAttribute<Course, Float> credits;
}
