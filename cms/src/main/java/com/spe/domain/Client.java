package com.spe.domain;

/**
 * Created by marc on 20/11/2017.
 */
public class Client implements IEntity<Integer> {

//    FIELDS
    private Integer id;
    private String orgName;
    private String orgAddress;
    private String orgPhone;
    private String persName;
    private String persPhone;
    private String persEmail;

//    CONSTRUCTORS
    public Client(Integer id, String orgName, String orgAddress, String orgPhone, String persName, String persPhone, String persEmail) {
        this.id = id;
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.orgPhone = orgPhone;
        this.persName = persName;
        this.persPhone = persPhone;
        this.persEmail = persEmail;
    }

//    GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getPersName() {
        return persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getPersPhone() {
        return persPhone;
    }

    public void setPersPhone(String persPhone) {
        this.persPhone = persPhone;
    }

    public String getPersEmail() {
        return persEmail;
    }

    public void setPersEmail(String persEmail) {
        this.persEmail = persEmail;
    }
}
