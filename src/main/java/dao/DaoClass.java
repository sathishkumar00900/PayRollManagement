package dao;
import config.DBConnection;
import model.Employee;
import model.LoginResult;
import java.sql.*;
import java.util.*;

public class DaoClass {

    public LoginResult checkLogin(String username, String password) {
        boolean status = false;
        String sql = "SELECT * FROM employees WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return new LoginResult(true, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginResult(false, null);
    }

    public List<Employee> getAllUsers() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee u = new Employee();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setBaseSalary(rs.getDouble("baseSalary"));
                u.setHra(rs.getDouble("hra"));
                u.setDa(rs.getDouble("da"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addUser(Employee emp) {
        String sql = "INSERT INTO employees(name,username,password,role,baseSalary,hra,da) VALUES (?, ?, ?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getUsername());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getRole());
            ps.setDouble(5, emp.getBaseSalary());
            ps.setDouble(6, emp.getHra());
            ps.setDouble(7, emp.getDa());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(Employee user) {
        String sql = "UPDATE employees SET name=?, username=?, password=?,role=?,baseSalary=?, hra=?, da=? WHERE id=?";
        boolean updated = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4,user.getRole());
            ps.setDouble(5,user.getBaseSalary());
            ps.setDouble(6,user.getHra());
            ps.setDouble(7,user.getDa());
            ps.setInt(8, user.getId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }


    public Employee getEmployeeByUsername(String username) {
        Employee emp = null;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM employees WHERE username =?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setUsername(rs.getString("username"));
                emp.setRole(rs.getString("role"));
                emp.setBaseSalary(rs.getDouble("baseSalary"));
                emp.setHra(rs.getDouble("hra"));
                emp.setDa(rs.getDouble("da"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }
}
