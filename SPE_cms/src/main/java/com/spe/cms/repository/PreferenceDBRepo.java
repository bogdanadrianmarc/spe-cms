package com.spe.cms.repository;

import com.spe.cms.domain.Preference;
import com.spe.cms.repository.general.IDBRepo;
import com.spe.cms.repository.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PreferenceDBRepo implements IDBRepo<Integer, Preference> {

    private DBUtils dbUtils;

    public PreferenceDBRepo(Properties props) {
        dbUtils = new DBUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Preferences")) {
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
    public void save(Preference entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Preferences VALUES (?,?,?,?)")) {
            preStmt.setString(2, entity.getStudentId());
            preStmt.setInt(3, entity.getProjectId());
            preStmt.setInt(4, entity.getPriority());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(Integer integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Preferences WHERE id=?")) {
            preStmt.setInt(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    public void deleteByStudentAndProjectId(String studentId, Integer projectId) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Preferences WHERE studentId=? AND projectId=?")) {
            preStmt.setString(1, studentId);
            preStmt.setInt(2, projectId);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Integer integer, Preference entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Preferences SET studentId=?,projectId=?,priority=? WHERE id=?")) {
            preStmt.setString(1, entity.getStudentId());
            preStmt.setInt(2, entity.getProjectId());
            preStmt.setInt(3, entity.getPriority());
            preStmt.setInt(4, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Preference findOne(Integer integer) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Preferences WHERE id=?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String studentId = result.getString("studentId");
                    int projectId = result.getInt("projectId");
                    int priority = result.getInt("priority");
                    Preference p = new Preference(id, studentId, projectId, priority);
                    return p;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Preference> findAll() {
        Connection con = dbUtils.getConnection();
        List<Preference> preferences = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Preferences")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String studentId = result.getString("studentId");
                    int projectId = result.getInt("projectId");
                    int priority = result.getInt("priority");
                    Preference p = new Preference(id, studentId, projectId, priority);
                    preferences.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return preferences;
    }

    public Iterable<Preference> findAllByStudentId(String integer) {
        Connection con = dbUtils.getConnection();
        List<Preference> preferences = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Preferences WHERE studentId=?")) {
            preStmt.setString(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String studentId = result.getString("studentId");
                    int projectId = result.getInt("projectId");
                    int priority = result.getInt("priority");
                    Preference p = new Preference(id, studentId, projectId, priority);
                    preferences.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return preferences;
    }
}
