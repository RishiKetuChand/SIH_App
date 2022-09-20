package com.rkc.userarchitectsih.dto;

public class ProjectDetailsJosn {
    private String project_id;
    private String client_id;
    private String gram_id;
    private String eng_email;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getGram_id() {
        return gram_id;
    }

    public void setGram_id(String gram_id) {
        this.gram_id = gram_id;
    }

    public String getEng_email() {
        return eng_email;
    }

    public void setEng_email(String eng_email) {
        this.eng_email = eng_email;
    }

    public ProjectDetailsJosn(String project_id, String client_id, String gram_id, String eng_email) {
        this.project_id = project_id;
        this.client_id = client_id;
        this.gram_id = gram_id;
        this.eng_email = eng_email;
    }
}
