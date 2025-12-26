package dao;

import config.DBConnection;
import model.Employee;
import model.User;

import java.sql.*;
import java.util.*;

public class DaoClass {

    public boolean checkLogin(String username, String password) {
        boolean status = false;
        String sql = "SELECT * FROM admin WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public boolean checkEmpLogin(String username, String password) {
        boolean status = false;
        String sql = "SELECT * FROM employee WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setCity(rs.getString("city"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addUser(User user) {
        String sql = "INSERT INTO users(name, email, city) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCity());

            int rowsInserted = ps.executeUpdate(); // ✅ CORRECT
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate(); // ✅ CORRECT
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET name=?, email=?, city=? WHERE id=?";
        boolean updated = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCity());
            ps.setInt(4, user.getId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public Employee getEmployeeByUsername(String username) {

        Employee emp = new Employee();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement("SELECT username, baseSalary, hra, da FROM employee WHERE username=?");

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp.setUsername(rs.getString("username"));
                emp.setBaseSalary(rs.getDouble("baseSalary"));
                emp.setHra(rs.getDouble("hra"));
                emp.setDa(rs.getDouble("da"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

}
