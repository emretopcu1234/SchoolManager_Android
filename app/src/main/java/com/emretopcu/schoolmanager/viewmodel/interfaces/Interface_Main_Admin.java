package com.emretopcu.schoolmanager.viewmodel.interfaces;

import com.emretopcu.schoolmanager.commonObjectTypes.DepartmentType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.commonObjectTypes.SemesterType;

import java.util.ArrayList;
import java.util.HashMap;

public interface Interface_Main_Admin {
    void dataLoadError();

    void onDepartmentIdInfo(HashMap<String,String> departmentIdInfo);
    void onLoadSemestersResulted(ArrayList<String> semestersList);
    void onLoadDetailedSemestersResulted(ArrayList<SemesterType> detailedSemestersList);
    void onIsSemesterActiveOrFutureResulted(boolean semesterActiveOrFuture);

    void onGetDepartmentListResulted(ArrayList<DepartmentType> departmentList);
    void onGetDeptAdminListResulted(ArrayList<PersonType> deptAdminList);
    void onGetLecturerListResulted(ArrayList<PersonType> lecturerList);
    void onGetStudentListResulted(ArrayList<PersonType> studentList);

    void onAddDepartmentResultedSuccessful();
    void onAddDepartmentResultedDuplicatedId();
    void onAddDepartmentResultedDuplicatedName();

    void onAddDeptAdminResultedSuccessful();
    void onAddDeptAdminResultedDuplicatedId();

    void onAddLecturerResultedSuccessful();
    void onAddLecturerResultedDuplicatedId();

    void onAddStudentResultedSuccessful();
    void onAddStudentResultedDuplicatedId();

    void onAddSemesterResultedSuccessful();
    void onAddSemesterResultedReverseOrder();
    void onAddSemesterResultedLowDateDifference();
    void onAddSemesterResultedHighDateDifference();

    void onEditDepartmentResultedSuccessful();
    void onEditDepartmentResultedDuplicatedName();

    void onEditDeptAdminResultedSuccessful();

    void onEditLecturerResultedSuccessful();

    void onEditStudentResultedSuccessful();

    void onEditSemesterResultedSuccessful();
    void onEditSemesterResultedReverseOrder();
    void onEditSemesterResultedLowDateDifference();
    void onEditSemesterResultedHighDateDifference();

    void onDeleteDepartmentsResultedSuccessful();
    void onDeleteDeptAdminsResultedSuccessful();
    void onDeleteLecturersResultedSuccessful();
    void onDeleteStudentsResultedSuccessful();
    void onDeleteSemesterResultedSuccessful();
}