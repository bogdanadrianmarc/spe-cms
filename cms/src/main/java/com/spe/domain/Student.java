package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Student {

//    FIELDS
    private int id; //the index we will use to iterate on, just a simple id
    private String uniId; //the student's uni id: ab12345
    private String fakeId; //the id we are going to display
    private String fullName; //first and last name and middle name and nickname, ...

//    CONSTRUCTORS
    public Student(int id, String uniId, String fakeId, String fullName) {
        this.id = id;
        this.uniId = uniId;
        this.fakeId = fakeId;
        this.fullName = fullName;
    }

//    GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
