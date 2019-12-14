package service;

import dao.UserDAOHibernate;
import dao.UserDao;
import dao.UserDaoJDBC;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private final UserDao userDao;

    private UserService() {
        userDao = new UserDAOHibernate();
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
        try {
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
