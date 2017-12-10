package com.spe.cms.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Preference {

//    FIELDS
    private Integer studentId;
    private Integer projectIdFirst;
    private Integer projectIdSecond;
    private Integer projectIdThird;
    private Iterable<Integer> projectIds;

//    CONSTRUCTORS
    public Preference(Integer studentId, Integer projectIdFirst, Integer projectIdSecond, Integer projectIdThird, Iterable<Integer> projectIds) {
        this.studentId = studentId;
        this.projectIdFirst = projectIdFirst;
        this.projectIdSecond = projectIdSecond;
        this.projectIdThird = projectIdThird;
        this.projectIds = projectIds;
    }

//    GETTERS AND SETTERS
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProjectIdFirst() {
        return projectIdFirst;
    }

    public void setProjectIdFirst(Integer projectIdFirst) {
        this.projectIdFirst = projectIdFirst;
    }

    public Integer getProjectIdSecond() {
        return projectIdSecond;
    }

    public void setProjectIdSecond(Integer projectIdSecond) {
        this.projectIdSecond = projectIdSecond;
    }

    public Integer getProjectIdThird() {
        return projectIdThird;
    }

    public void setProjectIdThird(Integer projectIdThird) {
        this.projectIdThird = projectIdThird;
    }

    public Iterable<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Iterable<Integer> projectIds) {
        this.projectIds = projectIds;
    }
}
