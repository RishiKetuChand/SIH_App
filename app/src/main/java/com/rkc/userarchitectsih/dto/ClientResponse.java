package com.rkc.userarchitectsih.dto;

public class ClientResponse {
    private  ClientDataJson[] client;

    public ClientDataJson[] getClient() {
        return client;
    }

    public void setClient(ClientDataJson[] client) {
        this.client = client;
    }

    public ClientResponse(ClientDataJson[] client) {
        this.client = client;
    }
}
