package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Teacher implements IEntity<Integer> {

//    FIELDS
    private Integer id;
    private String fullName;
    private String email;

//    CONSTRUCTORS
    public Teacher(Integer id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

//    GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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