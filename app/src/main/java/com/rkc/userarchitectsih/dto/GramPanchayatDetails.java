package com.rkc.userarchitectsih.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class GramPanchayatDetails implements Parcelable {
    private String gramPanchayatName;
    private String gramPanchayatID;

    public GramPanchayatDetails(String gramPanchayatName, String gramPanchayatID) {
        this.gramPanchayatName = gramPanchayatName;
        this.gramPanchayatID = gramPanchayatID;
    }

    protected GramPanchayatDetails(Parcel in) {
        gramPanchayatName = in.readString();
        gramPanchayatID = in.readString();
    }

    public static final Creator<GramPanchayatDetails> CREATOR = new Creator<GramPanchayatDetails>() {
        @Override
        public GramPanchayatDetails createFromParcel(Parcel in) {
            return new GramPanchayatDetails(in);
        }

        @Override
        public GramPanchayatDetails[] newArray(int size) {
            return new GramPanchayatDetails[size];
        }
    };

    public String getGramPanchayatName() {
        return gramPanchayatName;
    }

    public void setGramPanchayatName(String gramPanchayatName) {
        this.gramPanchayatName = gramPanchayatName;
    }

    public String getGramPanchayatID() {
        return gramPanchayatID;
    }

    public void setGramPanchayatID(String gramPanchayatID) {
        this.gramPanchayatID = gramPanchayatID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(gramPanchayatName);
        parcel.writeString(gramPanchayatID);
    }
}
