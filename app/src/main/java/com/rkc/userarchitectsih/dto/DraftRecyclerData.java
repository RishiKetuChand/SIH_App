package com.rkc.userarchitectsih.dto;

public class DraftRecyclerData {
    private String tempID;
    private String buildingOwner;
    private String buildingName;

    public DraftRecyclerData(String tempID, String buildingOwner, String buildingName) {
        this.tempID = tempID;
        this.buildingOwner = buildingOwner;
        this.buildingName = buildingName;
    }

    public String getTempID() {
        return tempID;
    }

    public void setTempID(String tempID) {
        this.tempID = tempID;
    }

    public String getBuildingOwner() {
        return buildingOwner;
    }

    public void setBuildingOwner(String buildingOwner) {
        this.buildingOwner = buildingOwner;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
