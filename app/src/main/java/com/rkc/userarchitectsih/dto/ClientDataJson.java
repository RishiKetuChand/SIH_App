package com.rkc.userarchitectsih.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class ClientDataJson implements Parcelable {
    private String clientId;
    private String clientAadhar;
    private String clientName;
    private String clientAddress;
    private String clientMobile;
    private String clientEmail;

    public ClientDataJson(String clientId, String clientEmail, String clientAadhar, String clientName, String clientAddress, String clientMobile) {
        this.clientId = clientId;
        this.clientAadhar = clientAadhar;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientMobile = clientMobile;
        this.clientEmail = clientEmail;
    }

    protected ClientDataJson(Parcel in) {
        clientId = in.readString();
        clientAadhar = in.readString();
        clientName = in.readString();
        clientAddress = in.readString();
        clientMobile = in.readString();
        clientEmail = in.readString();
    }

    public static final Creator<ClientDataJson> CREATOR = new Creator<ClientDataJson>() {
        @Override
        public ClientDataJson createFromParcel(Parcel in) {
            return new ClientDataJson(in);
        }

        @Override
        public ClientDataJson[] newArray(int size) {
            return new ClientDataJson[size];
        }
    };

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientAadhar() {
        return clientAadhar;
    }

    public void setClientAadhar(String clientAadhar) {
        this.clientAadhar = clientAadhar;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(clientId);
        parcel.writeString(clientAadhar);
        parcel.writeString(clientName);
        parcel.writeString(clientAddress);
        parcel.writeString(clientMobile);
        parcel.writeString(clientEmail);
    }
}
