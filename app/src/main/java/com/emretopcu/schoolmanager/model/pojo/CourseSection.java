package com.emretopcu.schoolmanager.model.pojo;

import com.google.firebase.Timestamp;

import java.util.List;

public class CourseSection {
    private String lecturerFullName;
    private List<String> studentIds;
    private List<Object> hours;

    public CourseSection(String lecturerFullName, List<String> studentIds, List<Object> hours) {
        this.lecturerFullName = lecturerFullName;
        this.studentIds = studentIds;
        this.hours = hours;
    }

    public String getLecturerFullName() {
        return lecturerFullName;
    }

    public void setLecturerFullName(String lecturerFullName) {
        this.lecturerFullName = lecturerFullName;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Object> getHours() {
        return hours;
    }

    public void setHours(List<Object> hours) {
        this.hours = hours;
    }
}
