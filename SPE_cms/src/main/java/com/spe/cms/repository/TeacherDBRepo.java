package com.spe.cms.repository;

import com.spe.cms.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TeacherDBRepo implements IDBRepo<String, Teacher> {

    private DBUtils dbUtils;

    public TeacherDBRepo(Properties props) {
        dbUtils = new DBUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Teachers")) {
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
    public void save(Teacher entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Teachers VALUES (?,?,?,?)")) {
            preStmt.setString(1, entity.getId());
            preStmt.setString(2, entity.getPassword());
            preStmt.setString(3, entity.getFullName());
            preStmt.setString(4, entity.getEmail());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(String integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Teachers WHERE id=?")) {
            preStmt.setString(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(String integer, Teacher entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Teachers SET password=?, fullName=?, email=? WHERE id=?")) {
            preStmt.setString(1, entity.getPassword());
            preStmt.setString(2, entity.getFullName());
            preStmt.setString(3, entity.getEmail());
            preStmt.setString(4, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Teacher findOne(String integer) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Teachers WHERE id=?")) {
            preStmt.setString(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String fullName = result.getString("fullName");
                    String email = result.getString("email");
                    Teacher s = new Teacher(id, password, fullName, email);
                    return s;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Teacher> findAll() {
        Connection con = dbUtils.getConnection();
        List<Teacher> projects = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Teachers")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String fullName = result.getString("fullName");
                    String email = result.getString("email");
                    Teacher s = new Teacher(id, password, fullName, email);
                    projects.add(s);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return projects;
    }
}
