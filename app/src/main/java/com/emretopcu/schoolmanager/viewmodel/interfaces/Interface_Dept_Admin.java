package com.emretopcu.schoolmanager.viewmodel.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface Interface_Dept_Admin {
    void dataLoadError();

    void onLoadSemestersResulted(ArrayList<String> semestersList);
    void onIsSemesterActiveResulted(boolean semesterActive);

    void onGetCourseListResulted(ArrayList<String[]> courseList);
    void onGetLecturerListResulted(ArrayList<String[]> lecturerList);
    void onGetStudentListResulted(ArrayList<String[]> studentList);
    void onGetDepartmentListResulted(ArrayList<String[]> departmentList);

    void onAddCourseResultedSuccessful();
    void onEditCourseResultedSuccessful();
    // TODO diğer stateler de eklenecek.

    void onDeleteCoursesResultedSuccessful();
}