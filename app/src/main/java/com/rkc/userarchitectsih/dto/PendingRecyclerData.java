package com.rkc.userarchitectsih.dto;

public class PendingRecyclerData {

    private String applicationID;
    private String buildingOwner;
    private String buildingName;
    private String currentStage;
    private String estimatedDate;
    private String allottedDate;
    private String previousStage;
    private String previousStageCompDate;
    private String progressBarColor;
    private String progressValue;

    public PendingRecyclerData(String applicationID,String buildingOwner, String buildingName, String currentStage, String estimatedDate, String allottedDate, String previousStage, String previousStageCompDate, String progressBarColor, String progressValue) {
        this.applicationID = applicationID;
        this.buildingOwner = buildingOwner;
        this.buildingName = buildingName;
        this.currentStage = currentStage;
        this.estimatedDate = estimatedDate;
        this.allottedDate = allottedDate;
        this.previousStage = previousStage;
        this.previousStageCompDate = previousStageCompDate;
        this.progressBarColor = progressBarColor;
        this.progressValue = progressValue;
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

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(String estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public String getAllottedDate() {
        return allottedDate;
    }

    public void setAllottedDate(String allottedDate) {
        this.allottedDate = allottedDate;
    }

    public String getPreviousStage() {
        return previousStage;
    }

    public void setPreviousStage(String previousStage) {
        this.previousStage = previousStage;
    }

    public String getPreviousStageCompDate() {
        return previousStageCompDate;
    }

    public void setPreviousStageCompDate(String previousStageCompDate) {
        this.previousStageCompDate = previousStageCompDate;
    }

    public String getProgressBarColor() {
        return progressBarColor;
    }

    public void setProgressBarColor(String progressBarColor) {
        this.progressBarColor = progressBarColor;
    }

    public String getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(String progressValue) {
        this.progressValue = progressValue;
    }
}
