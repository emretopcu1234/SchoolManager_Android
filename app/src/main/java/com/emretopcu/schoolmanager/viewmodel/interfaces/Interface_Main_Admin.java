package com.emretopcu.schoolmanager.viewmodel.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface Interface_Main_Admin {
    void dataLoadError();
    void onDepartmentIdInfo(HashMap<String,String> departmentIdInfo);
    void onLoadSemestersResulted(ArrayList<String> semestersList);
    void onLoadDetailedSemestersResulted(ArrayList<String[]> detailedSemestersList);
    void onIsSemesterActiveResulted(boolean semesterActive);
    void onGetDepartmentListResulted(ArrayList<String[]> departmentList);
    void onGetDeptAdminListResulted(ArrayList<String[]> deptAdminList);
    void onGetLecturerListResulted(ArrayList<String[]> lecturerList);
    void onGetStudentListResulted(ArrayList<String[]> studentList);
    void onAddSemesterResultedSuccessful();
    void onAddSemesterResultedReverseOrder();
    void onAddSemesterResultedLowDateDifference();
    void onAddSemesterResultedHighDateDifference();
}