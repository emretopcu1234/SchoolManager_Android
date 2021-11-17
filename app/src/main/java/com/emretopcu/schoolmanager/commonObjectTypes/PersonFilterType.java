package com.emretopcu.schoolmanager.commonObjectTypes;

import java.util.ArrayList;

public class PersonFilterType {
    String semester;
    String idFilter;
    String nameFilter;
    String surnameFilter;
    ArrayList<String> deptFilter;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(String idFilter) {
        this.idFilter = idFilter;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getSurnameFilter() {
        return surnameFilter;
    }

    public void setSurnameFilter(String surnameFilter) {
        this.surnameFilter = surnameFilter;
    }

    public ArrayList<String> getDeptFilter() {
        return deptFilter;
    }

    public void setDeptFilter(ArrayList<String> deptFilter) {
        this.deptFilter = deptFilter;
    }
}
