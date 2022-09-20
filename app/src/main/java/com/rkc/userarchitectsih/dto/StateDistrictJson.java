package com.rkc.userarchitectsih.dto;

import android.os.Parcel;
import android.os.Parcelable;


public class StateDistrictJson implements Parcelable {
    private String State;
    private String District;

    protected StateDistrictJson(Parcel in) {
        State = in.readString();
        District = in.readString();
    }

    public static final Creator<StateDistrictJson> CREATOR = new Creator<StateDistrictJson>() {
        @Override
        public StateDistrictJson createFromParcel(Parcel in) {
            return new StateDistrictJson(in);
        }

        @Override
        public StateDistrictJson[] newArray(int size) {
            return new StateDistrictJson[size];
        }
    };

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public StateDistrictJson(String state, String district) {
        State = state;
        District = district;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(State);
        parcel.writeString(District);
    }
}
