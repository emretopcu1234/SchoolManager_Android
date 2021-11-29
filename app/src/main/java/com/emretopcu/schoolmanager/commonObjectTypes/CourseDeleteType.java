package com.emretopcu.schoolmanager.commonObjectTypes;

import java.util.ArrayList;

public class CourseDeleteType {
    String semester;
    ArrayList<String> courseIdList;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public ArrayList<String> getCourseIdList() {
        return courseIdList;
    }

    public void setCourseIdList(ArrayList<String> courseIdList) {
        this.courseIdList = courseIdList;
    }
}
