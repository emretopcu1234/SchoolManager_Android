package com.emretopcu.schoolmanager.model.pojo;

import com.google.firebase.Timestamp;

import java.util.List;

public class Semester {
    private Timestamp startDate;
    private Timestamp endDate;
    private List<Object> weeks;

    public Semester(Timestamp startDate, Timestamp endDate, List<Object> weeks) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.weeks = weeks;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public List<Object> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Object> weeks) {
        this.weeks = weeks;
    }
}