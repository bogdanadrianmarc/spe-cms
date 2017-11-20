package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Project {

//    FIELDS
    private int id;
    private String tags;
    private String title;
    private String content;
    private int applicantsNr;
    private String imgUrl;
    private String projectUrl;

//    CONSTRUCTORS
    public Project(int id, String tags, String title, String content, int applicantsNr, String imgUrl, String projectUrl) {
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.applicantsNr = applicantsNr;
        this.imgUrl = imgUrl;
        this.projectUrl = projectUrl;
    }

//    GETTERS
    public int getId() {
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

    public int getapplicantsNr() {
        return applicantsNr;
    }

    public String getImgUrl() { return imgUrl; }

    public String getProjectUrl() { return projectUrl; }

//    SETTERS
    public void setId(int id) {
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

    public void setApplicantsNr(int applicantsNr) {
        this.applicantsNr = applicantsNr;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}
