package com.emretopcu.schoolmanager.commonObjectTypes;

import java.util.ArrayList;

public class DepartmentDeleteType {
    String semester;
    ArrayList<String> idList;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public ArrayList<String> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<String> idList) {
        this.idList = idList;
    }
}
