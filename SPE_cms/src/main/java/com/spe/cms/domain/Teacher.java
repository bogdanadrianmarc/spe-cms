package com.spe.cms.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Teacher implements IEntity<String> {

//    FIELDS
    private String id;
    private String password;
    private String fullName;
    private String email;

//    CONSTRUCTORS
    public Teacher(String id, String password, String fullName, String email) {
        this.id = id;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

//    GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
