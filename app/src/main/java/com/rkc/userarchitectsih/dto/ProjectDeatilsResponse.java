package com.rkc.userarchitectsih.dto;

public class ProjectDeatilsResponse {
    private  CaseWorkerData[] project;

    public CaseWorkerData[] getProject() {
        return project;
    }

    public void setProject(CaseWorkerData[] project) {
        this.project = project;
    }

    public ProjectDeatilsResponse(CaseWorkerData[] project) {
        this.project = project;
    }
}
