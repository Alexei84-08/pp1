package service;

import dao.UserDao;
import dao.UserDaoJDBC;
import model.User;
import util.DBConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private final Connection connection;

    private UserService() {
        connection = DBConnect.getMysqlConnection();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public void create(User user) {
        UserDao dao = new UserDaoJDBC(connection);
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        UserDao dao = new UserDaoJDBC(connection);
        try {
            return dao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        UserDao dao = new UserDaoJDBC(connection);
        try {
            return dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        UserDao dao = new UserDaoJDBC(connection);
        try {
            dao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        UserDao dao = new UserDaoJDBC(connection);
        try {
            dao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
