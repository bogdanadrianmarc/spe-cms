package com.spe.cms.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Preference {

//    FIELDS
    private Integer id; //primary key in the database
    private String studentId;
    private Integer projectId;
    private Integer priority;

//    CONSTRUCTORS
    public Preference(Integer id, String studentId, Integer projectId, Integer priority) {
        this.id = id;
        this.studentId = studentId;
        this.projectId = projectId;
        this.priority = priority;
    }

//    GETTERS AND SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
