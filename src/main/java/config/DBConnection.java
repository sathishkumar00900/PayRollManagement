package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || url.isEmpty()) {
                url = "jdbc:mysql://localhost:3306/PayRollManagement";
            }
            if (user == null || user.isEmpty()) {
                user = "root";
            }
            if (password == null || password.isEmpty()) {
                password = "1111";
            }

            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
