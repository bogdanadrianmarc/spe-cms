package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Ownership {

//    FIELDS
    private Integer clientId; //future primary key in db
    private Integer projectId;

//    CONSTRUCTORS
    public Ownership(Integer clientId, Integer projectId) {
        this.clientId = clientId;
        this.projectId = projectId;
    }

//    GETTERS AND SETTERS
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
