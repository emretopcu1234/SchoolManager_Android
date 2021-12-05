package com.emretopcu.schoolmanager.viewmodel.interfaces;

import com.emretopcu.schoolmanager.commonObjectTypes.CourseSectionType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseType;
import com.emretopcu.schoolmanager.commonObjectTypes.DepartmentType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;

import java.util.ArrayList;
import java.util.HashMap;

public interface Interface_Dept_Admin {
    void dataLoadError();

    void onDepartmentIdInfo(HashMap<String,String> departmentIdInfo);
    void onLoadSemestersResulted(ArrayList<String> semestersList);
    void onIsSemesterActiveOrFutureResulted(boolean semesterActiveOrFuture);
    void onDeptAdminInfoResulted(PersonType person);

    void onGetCourseSectionInfoResulted(CourseSectionType courseSection);
    void onGetSpecificStudentListResulted(ArrayList<PersonType> specificStudentList);
    void onGetDeptStudentListResulted(ArrayList<PersonType> deptStudentList);

    void onGetCourseListResulted(ArrayList<CourseType> courseList);
    void onGetLecturerListResulted(ArrayList<PersonType> lecturerList);
    void onGetStudentListResulted(ArrayList<PersonType> studentList);
    void onGetDepartmentListResulted(ArrayList<DepartmentType> departmentList);

    void onAddCourseResultedSuccessful();
    void onAddCourseResultedDuplicatedCourseId();
    void onAddCourseResultedDuplicatedCourseName();
    void onEditCourseResultedSuccessful();
    void onEditCourseResultedDuplicatedCourseName();
    void onDeleteCoursesResultedSuccessful();
}