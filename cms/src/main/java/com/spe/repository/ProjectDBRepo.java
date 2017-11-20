package com.spe.repository;

import com.spe.domain.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProjectDBRepo implements IDBRepo<Integer, Project> {

    private DBUtils dbUtils;

    public ProjectDBRepo(Properties props) {
        dbUtils = new DBUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Projects")) {
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
    public void save(Project entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Projects VALUES (?,?,?,?,?,?,?)")) {
            preStmt.setInt(1, entity.getId());
            preStmt.setString(2, entity.getTags());
            preStmt.setString(3, entity.getTitle());
            preStmt.setString(4, entity.getContent());
            preStmt.setInt(5, entity.getapplicantsNr());
            preStmt.setString(6, entity.getImgUrl());
            preStmt.setString(7, entity.getProjectUrl());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(Integer integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Projects WHERE id=?")) {
            preStmt.setInt(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Integer integer, Project entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Projects SET tags=?,title=?,content=?,applicantsNr=?,imgUrl=?,projectUrl=? WHERE id=?")) {
            preStmt.setString(1, entity.getTags());
            preStmt.setString(2, entity.getTitle());
            preStmt.setString(3, entity.getContent());
            preStmt.setInt(4, entity.getapplicantsNr());
            preStmt.setString(5, entity.getImgUrl());
            preStmt.setString(6, entity.getProjectUrl());
            preStmt.setInt(7, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Project findOne(Integer integer) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Projects WHERE id=?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String tags = result.getString("tags");
                    String title = result.getString("title");
                    String content = result.getString("content");
                    int applicantsNr = result.getInt("applicantsNr");
                    String imgUrl = result.getString("imgUrl");
                    String projectUrl = result.getString("projectUrl");
                    Project p = new Project(id, tags, title, content, applicantsNr, imgUrl, projectUrl);
                    return p;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Project> findAll() {
        Connection con = dbUtils.getConnection();
        List<Project> projects = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Projects")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String tags = result.getString("tags");
                    String title = result.getString("title");
                    String content = result.getString("content");
                    int applicantsNr = result.getInt("applicantsNr");
                    String imgUrl = result.getString("imgUrl");
                    String projectUrl = result.getString("projectUrl");
                    Project p = new Project(id, tags, title, content, applicantsNr, imgUrl, projectUrl);
                    projects.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return projects;
    }
}
