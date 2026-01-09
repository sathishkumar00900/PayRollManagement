package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application started. Initializing database...");
        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                // 1. Create Table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "username VARCHAR(255) UNIQUE, " +
                        "password VARCHAR(255), " +
                        "role VARCHAR(50), " +
                        "baseSalary DOUBLE, " +
                        "hra DOUBLE, " +
                        "da DOUBLE" +
                        ")";
                try (Statement stmt = con.createStatement()) {
                    stmt.execute(createTableSQL);
                    System.out.println("Table 'employees' checked/created successfully.");
                }

                // 2. Check for Admin
                String checkAdminSQL = "SELECT COUNT(*) FROM employees WHERE username = 'admin'";
                boolean adminExists = false;
                try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(checkAdminSQL)) {
                    if (rs.next()) {
                        adminExists = rs.getInt(1) > 0;
                    }
                }

                // 3. Create Admin if not exists
                if (!adminExists) {
                    String insertAdminSQL = "INSERT INTO employees (name, username, password, role, baseSalary, hra, da) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = con.prepareStatement(insertAdminSQL)) {
                        ps.setString(1, "Administrator");
                        ps.setString(2, "admin");
                        ps.setString(3, "admin123"); // Default password
                        ps.setString(4, "Admin"); // Role as used in code (e.g. DaoClass or logic)
                        ps.setDouble(5, 0.0);
                        ps.setDouble(6, 0.0);
                        ps.setDouble(7, 0.0);
                        ps.executeUpdate();
                        System.out.println("Default admin user created.");
                    }
                } else {
                    System.out.println("Admin user already exists.");
                }

            } else {
                System.err.println("Failed to establish database connection during initialization.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if necessary
    }
}
