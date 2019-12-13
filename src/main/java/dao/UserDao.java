package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException;

    User get(long id) throws SQLException;

    List<User> getAll() throws SQLException;

    void update(User user) throws SQLException;

    void delete(long id) throws SQLException;

}
