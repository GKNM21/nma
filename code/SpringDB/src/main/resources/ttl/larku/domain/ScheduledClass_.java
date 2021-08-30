package ttl.larku.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-08-21T17:34:33.296-0400")
@StaticMetamodel(ScheduledClass.class)
public class ScheduledClass_ {
	public static volatile SingularAttribute<ScheduledClass, Integer> id;
	public static volatile ListAttribute<ScheduledClass, Student> students;
	public static volatile SingularAttribute<ScheduledClass, String> startDate;
	public static volatile SingularAttribute<ScheduledClass, String> endDate;
	public static volatile SingularAttribute<ScheduledClass, Course> course;
}
