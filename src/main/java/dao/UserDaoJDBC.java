package dao;

import model.User;
import util.DBConnect;
import util.DBConnectJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDao {
    private Connection connection;

    public UserDaoJDBC() {
        this.connection = DBConnect.getInstance().getConnection();
    }

//    public UserDaoJDBC(Connection connection) {
//        this.connection = connection;
//    }

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (id , name, surname, age) VALUES (null, ?, ? , ?)");
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setInt(3, user.getAge());
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> listUsers = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
//        ResultSet result = stmt.getResultSet();
        while (result.next()) {
            User client = new User(result.getLong("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getInt("age"));
            listUsers.add(client);
        }
        stmt.close();
        System.out.println("Все пользователи получены!");
        return listUsers;
    }

    @Override
    public User get(long id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE Id =?");
        pstmt.setLong(1, id);
        ResultSet resultSet = pstmt.executeQuery();

        resultSet.next();
        return new User(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4));
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET name = ?, surname = ?, age =? WHERE Id =?");
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setInt(3, user.getAge());
        pstmt.setLong(4, user.getId());
        int rowUpdated = pstmt.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Существующий пользователь был успешно обновлен!");
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE Id =?");
        pstmt.setLong(1, id);
        int rowUpdated = pstmt.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Пользователь был успешно удален!");
        }
    }

    public void createTable() throws SQLException {
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name varchar(256), surname varchar(256), age int)");
    }
}
