package com.emretopcu.schoolmanager.commonObjectTypes;

public class CourseAddOrEditType {
    String courseId;
    String courseName;
    String sections;
    String semester;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
