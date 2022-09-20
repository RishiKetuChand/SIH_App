package com.rkc.userarchitectsih.dto;

public class SanctionedRecyclerData {
    private String applicationID;
    private String buildingOwner;
    private String buildingName;
    private String completionDate;

    public SanctionedRecyclerData(String applicationID, String buildingOwner, String buildingName, String completionDate) {
        this.applicationID = applicationID;
        this.buildingOwner = buildingOwner;
        this.buildingName = buildingName;
        this.completionDate = completionDate;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
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

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }
}
