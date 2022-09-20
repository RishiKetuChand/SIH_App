package com.rkc.userarchitectsih.dto;

public class StatusUpdate {
    private String project_id;
    private String emp_id;
    private String assign_date;
    private String p_step;
    private String p_status;
    private String p_status_brief;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getP_step() {
        return p_step;
    }

    public void setP_step(String p_step) {
        this.p_step = p_step;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }

    public String getP_status_brief() {
        return p_status_brief;
    }

    public void setP_status_brief(String p_status_brief) {
        this.p_status_brief = p_status_brief;
    }

    public StatusUpdate(String project_id, String emp_id, String assign_date, String p_step, String p_status, String p_status_brief) {
        this.project_id = project_id;
        this.emp_id = emp_id;
        this.assign_date = assign_date;
        this.p_step = p_step;
        this.p_status = p_status;
        this.p_status_brief = p_status_brief;
    }
}
