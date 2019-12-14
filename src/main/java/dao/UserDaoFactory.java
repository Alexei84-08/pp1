package dao;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDao create() {
        switch (getProperties()) {
            case "JDBC":
                return new UserDaoJDBC();
            case "Hibernate":
                return new UserDAOHibernate();
            default:
                return null;
        }
    }

    private static String getProperties() {
        try {
            String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            String appConfigPath = rootPath + "property.properties";
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            return appProps.getProperty("db.connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
