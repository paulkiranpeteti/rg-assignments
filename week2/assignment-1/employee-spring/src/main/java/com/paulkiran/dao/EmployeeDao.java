package com.paulkiran.dao;

import com.paulkiran.model.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private final DataSource dataSource;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() {
       try{
        Connection connection = dataSource.getConnection();

        String sql = "CREATE TABLE IF NOT EXISTS employee ( id INT PRIMARY KEY, name VARCHAR(255), department VARCHAR(255))";
        Statement stmt  = connection.createStatement();
        stmt.execute(sql);
       }
       catch(Exception e){
        e.printStackTrace();
       }
    }

    public void create(Employee employee) {
        String sql = "INSERT INTO employee (id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getDepartment());
            ps.executeUpdate();
            System.out.println("Employee created: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("department"),
                        rs.getString("name"));
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, department = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getDepartment());
            ps.setInt(3, employee.getId());
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " record(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted " + rows + " record(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
