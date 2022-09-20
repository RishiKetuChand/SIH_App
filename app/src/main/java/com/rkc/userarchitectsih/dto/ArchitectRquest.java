package com.rkc.userarchitectsih.dto;

public class ArchitectRquest {
    private String engEmail;

    public ArchitectRquest(String engEmail) {
        this.engEmail = engEmail;
    }

    public String getEngEmail() {
        return engEmail;
    }

    public void setEngEmail(String engEmail) {
        this.engEmail = engEmail;
    }
}
