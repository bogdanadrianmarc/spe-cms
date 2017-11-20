package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Preference {

//    FIELDS
    private int studentId;
    private int projectIdFirst;
    private int projectIdSecond;
    private int projectIdThird;
    private Iterable<Integer> projectIds;

//    CONSTRUCTORS
    public Preference(int studentId, int projectIdFirst, int projectIdSecond, int projectIdThird, Iterable<Integer> projectIds) {
        this.studentId = studentId;
        this.projectIdFirst = projectIdFirst;
        this.projectIdSecond = projectIdSecond;
        this.projectIdThird = projectIdThird;
        this.projectIds = projectIds;
    }

//    GETTERS AND SETTERS
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getProjectIdFirst() {
        return projectIdFirst;
    }

    public void setProjectIdFirst(int projectIdFirst) {
        this.projectIdFirst = projectIdFirst;
    }

    public int getProjectIdSecond() {
        return projectIdSecond;
    }

    public void setProjectIdSecond(int projectIdSecond) {
        this.projectIdSecond = projectIdSecond;
    }

    public int getProjectIdThird() {
        return projectIdThird;
    }

    public void setProjectIdThird(int projectIdThird) {
        this.projectIdThird = projectIdThird;
    }

    public Iterable<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Iterable<Integer> projectIds) {
        this.projectIds = projectIds;
    }
}
