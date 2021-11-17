package com.emretopcu.schoolmanager.commonObjectTypes;

public class DepartmentFilterType {
    String semester;
    String deptNameFilter;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDeptNameFilter() {
        return deptNameFilter;
    }

    public void setDeptNameFilter(String deptNameFilter) {
        this.deptNameFilter = deptNameFilter;
    }
}
