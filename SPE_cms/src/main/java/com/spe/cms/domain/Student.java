package com.spe.cms.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Student implements IEntity<String> {

//    FIELDS
    private String id; //student uni id, also primary key in db
    private String password;
    private String fullName; //first and last name and middle name and nickname, ...

//    CONSTRUCTORS
    public Student(String id, String password, String fullName) {
        this.id = id;
        this.password = password;
        this.fullName = fullName;
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
}
