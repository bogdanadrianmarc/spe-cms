package com.spe.cms.repository;

import com.spe.cms.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientDBRepo implements IDBRepo<String, Client> {

    private DBUtils dbUtils;

    public ClientDBRepo(Properties props) {
        dbUtils = new DBUtils(props);
    }

    @Override
    public int size() {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Clients")) {
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
    public void save(Client entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Clients VALUES (?,?,?,?,?,?,?,?)")) {
            preStmt.setString(1, entity.getId());
            preStmt.setString(2, entity.getPassword());
            preStmt.setString(3, entity.getOrgName());
            preStmt.setString(4, entity.getOrgAddress());
            preStmt.setString(5, entity.getOrgPhone());
            preStmt.setString(6, entity.getPersName());
            preStmt.setString(7, entity.getPersPhone());
            preStmt.setString(8, entity.getPersEmail());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(String integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Clients WHERE id=?")) {
            preStmt.setString(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(String integer, Client entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("UPDATE Clients SET password=?, orgName=?, orgAddress=?, orgPhone=?, persName=?, persPhone=?, persEmail=? WHERE id=?")) {
            preStmt.setString(1, entity.getPassword());
            preStmt.setString(2, entity.getOrgName());
            preStmt.setString(3, entity.getOrgAddress());
            preStmt.setString(4, entity.getOrgPhone());
            preStmt.setString(5, entity.getPersName());
            preStmt.setString(6, entity.getPersPhone());
            preStmt.setString(7, entity.getPersEmail());
            preStmt.setString(8, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Client findOne(String integer) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Clients WHERE id=?")) {
            preStmt.setString(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String orgName = result.getString("orgName");
                    String orgAddress = result.getString("orgAddress");
                    String orgPhone = result.getString("orgPhone");
                    String persName = result.getString("persName");
                    String persPhone = result.getString("persPhone");
                    String persEmail = result.getString("persEmail");
                    Client s = new Client(id, password, orgName, orgAddress, orgPhone, persName, persPhone, persEmail);
                    return s;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        Connection con = dbUtils.getConnection();
        List<Client> projects = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("SELECT * FROM Clients")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    String id = result.getString("id");
                    String password = result.getString("password");
                    String orgName = result.getString("orgName");
                    String orgAddress = result.getString("orgAddress");
                    String orgPhone = result.getString("orgPhone");
                    String persName = result.getString("persName");
                    String persPhone = result.getString("persPhone");
                    String persEmail = result.getString("persEmail");
                    Client s = new Client(id, password, orgName, orgAddress, orgPhone, persName, persPhone, persEmail);
                    projects.add(s);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return projects;
    }
}
