package com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin;

public class DepartmentAddOrEditType {
    private String deptName;
    private String deptId;
    private String semester;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
