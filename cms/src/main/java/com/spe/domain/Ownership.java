package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Ownership {

//    FIELDS
    private int clientId; //future primary key in db
    private int projectId;

//    CONSTRUCTORS
    public Ownership(int clientId, int projectId) {
        this.clientId = clientId;
        this.projectId = projectId;
    }

//    GETTERS AND SETTERS
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
