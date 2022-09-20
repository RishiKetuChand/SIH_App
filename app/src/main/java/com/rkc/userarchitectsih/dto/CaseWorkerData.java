package com.rkc.userarchitectsih.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class CaseWorkerData  implements Parcelable {


    private String client_aadhar;
    private String client_id;
    private String client_name;
    private String eng_email;
    private String gram_id;
    private  String project_id;

    protected CaseWorkerData(Parcel in) {
        client_aadhar = in.readString();
        client_id = in.readString();
        client_name = in.readString();
        eng_email = in.readString();
        gram_id = in.readString();
        project_id = in.readString();
    }

    public static final Creator<CaseWorkerData> CREATOR = new Creator<CaseWorkerData>() {
        @Override
        public CaseWorkerData createFromParcel(Parcel in) {
            return new CaseWorkerData(in);
        }

        @Override
        public CaseWorkerData[] newArray(int size) {
            return new CaseWorkerData[size];
        }
    };

    public String getClient_aadhar() {
        return client_aadhar;
    }

    public void setClient_aadhar(String client_aadhar) {
        this.client_aadhar = client_aadhar;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getEng_email() {
        return eng_email;
    }

    public void setEng_email(String eng_email) {
        this.eng_email = eng_email;
    }

    public String getGram_id() {
        return gram_id;
    }

    public void setGram_id(String gram_id) {
        this.gram_id = gram_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public CaseWorkerData(String client_aadhar, String client_id, String client_name, String eng_email, String gram_id, String project_id) {
        this.client_aadhar = client_aadhar;
        this.client_id = client_id;
        this.client_name = client_name;
        this.eng_email = eng_email;
        this.gram_id = gram_id;
        this.project_id = project_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(client_aadhar);
        parcel.writeString(client_id);
        parcel.writeString(client_name);
        parcel.writeString(eng_email);
        parcel.writeString(gram_id);
        parcel.writeString(project_id);
    }
}
