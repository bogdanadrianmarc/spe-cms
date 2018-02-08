package com.spe.cms.domain;

import com.spe.cms.domain.general.IEntity;

/**
 * Created by marc on 20/11/2017.
 */
public class Project implements IEntity<Integer> {

//    FIELDS
    private Integer id; //primary key in db
    private String tags;
    private String title;
    private String content;
    private Integer applicantsNr;
    private String imgUrl;
    private String projectUrl;
    private String clientId;

//    CONSTRUCTORS
    public Project(Integer id, String tags, String title, String content, Integer applicantsNr, String imgUrl, String projectUrl, String clientId) {
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.applicantsNr = applicantsNr;
        this.imgUrl = imgUrl;
        this.projectUrl = projectUrl;
        this.clientId = clientId;
    }

//    GETTERS
    public Integer getId() {
        return id;
    }

    public String getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getApplicantsNr() {
        return applicantsNr;
    }

    public String getImgUrl() { return imgUrl; }

    public String getProjectUrl() { return projectUrl; }

    public String getClientId() {
        return clientId;
    }

//    SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setApplicantsNr(Integer applicantsNr) {
        this.applicantsNr = applicantsNr;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
