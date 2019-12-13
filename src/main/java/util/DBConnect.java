package util;

import com.mysql.cj.xdevapi.SessionFactory;
import model.User;
import service.UserService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
//    private static SessionFactory sessionFactory;


    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("pp_1?").          //db name
                    append("user=asd&").            //login
                    append("password=123").         //password
                    append("&serverTimezone=UTC");  //setup server time

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
//        userService.create();
        System.out.println(userService.getUserById(1));

//        userService.update(new User(1L, "asd", "zxc", 25));
//        userService.delete(2);
//        userService.createTable();
    }

//    private static BankClientDAO getBankClientDAO() {
//        return new BankClientDAO(getMysqlConnection());
//    }
}
