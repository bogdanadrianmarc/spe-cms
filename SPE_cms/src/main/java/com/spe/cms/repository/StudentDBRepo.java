package com.spe.cms.repository;

import com.spe.cms.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentDBRepo implements IDBRepo<String, Student> {

    private DBUtils dbUtils;

    public StudentDBRepo(Properties props) {
        dbUtils = new DBUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Students")) {
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    return result.getInt("SIZE");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return 0;
    }

    @Override
    public void save(Student entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Students VALUES (?,?,?)")) {
            preStmt.setString(1, entity.getId());
            preStmt.setString(2, entity.getPassword());
            preStmt.setString(3, entity.getFullName());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(String integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Students WHERE id=?")) {
            preStmt.setString(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(String integer, Student entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Students SET password=?, fullName=? WHERE id=?")) {
            preStmt.setString(1, entity.getPassword());
            preStmt.setString(2, entity.getFullName());
            preStmt.setString(3, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Student findOne(String integer) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Students WHERE id=?")) {
            preStmt.setString(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String fullName = result.getString("fullName");
                    Student s = new Student(id, password, fullName);
                    return s;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Student> findAll() {
        Connection con = dbUtils.getConnection();
        List<Student> projects = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Students")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String fullName = result.getString("fullName");
                    Student s = new Student(id, password, fullName);
                    projects.add(s);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return projects;
    }
}
