package com.rkc.userarchitectsih.dto;

public class ClientRequest {
    private String clientEmail;

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public ClientRequest(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
