package ttl.larku.dao;

import ttl.larku.service.StudentService;

import java.util.ResourceBundle;

/**
 * @author whynot
 */
public class TheFactory {

    public static BaseDAO getDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larku");
        String profile = bundle.getString("my.profile");
        switch (profile) {
            case "dev":
                return new InMemoryStudentDAO();
            case "prod":
                return new JpaStudentDAO();
            default:
                throw new RuntimeException("Unknown profile: " + profile);
        }
    }

    public static StudentService getService() {
        BaseDAO dao = getDAO();
        StudentService ss = new StudentService(dao);
        return ss;
    }
}
