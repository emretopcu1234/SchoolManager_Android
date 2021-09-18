package com.emretopcu.schoolmanager.viewmodel.interfaces;

import java.util.ArrayList;

public interface Interface_Main_Admin {
    void dataLoadError();
    void onLoadSemestersResulted(ArrayList<String> semestersList);
    void onIsSemesterActiveResulted(boolean semesterActive);
    void onGetDepartmentListResulted(ArrayList<String[]> departmentList);
    void onGetDeptAdminListResulted(ArrayList<String[]> deptAdminList);
}
