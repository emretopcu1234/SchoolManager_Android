package com.emretopcu.schoolmanager.viewmodel.sharedData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;

import java.util.ArrayList;
import java.util.HashMap;

public class SD_Main_Admin {

    private static SD_Main_Admin INSTANCE;

    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDetailedSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDeptAdminsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setStudentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> addDepartmentSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> addDeptAdminSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> addLecturerSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> addStudentSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> addSemesterSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> editDepartmentSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> editDeptAdminSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> editLecturerSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> editStudentSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> editSemesterSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteDeptAdminsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteStudentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteSemesterSuccessful;
    private ArrayList<String> semesterList;
    private ArrayList<String[]> detailedSemesterList;
    private boolean semesterActive;
    private ArrayList<String[]> departmentList;
    private ArrayList<String[]> deptAdminList;
    private ArrayList<String[]> lecturerList;
    private ArrayList<String[]> studentList;
    private HashMap<String,String> departmentIdInfo;

    private SD_Main_Admin(){
        try{
            setSemestersSuccessful = new MutableLiveData<>();
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDetailedSemestersSuccessful = new MutableLiveData<>();
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            isSemesterActiveSuccessful = new MutableLiveData<>();
            isSemesterActiveSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDepartmentsSuccessful = new MutableLiveData<>();
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDeptAdminsSuccessful = new MutableLiveData<>();
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setLecturersSuccessful = new MutableLiveData<>();
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setStudentsSuccessful = new MutableLiveData<>();
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addDepartmentSuccessful = new MutableLiveData<>();
            addDepartmentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addDeptAdminSuccessful = new MutableLiveData<>();
            addDeptAdminSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addLecturerSuccessful = new MutableLiveData<>();
            addLecturerSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addStudentSuccessful = new MutableLiveData<>();
            addStudentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addSemesterSuccessful = new MutableLiveData<>();
            addSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            editDepartmentSuccessful = new MutableLiveData<>();
            editDepartmentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            editDeptAdminSuccessful = new MutableLiveData<>();
            editDeptAdminSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            editLecturerSuccessful = new MutableLiveData<>();
            editLecturerSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            editStudentSuccessful = new MutableLiveData<>();
            editStudentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            editSemesterSuccessful = new MutableLiveData<>();
            editSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            deleteDepartmentsSuccessful = new MutableLiveData<>();
            deleteDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            deleteDeptAdminsSuccessful = new MutableLiveData<>();
            deleteDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            deleteLecturersSuccessful = new MutableLiveData<>();
            deleteLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            deleteStudentsSuccessful = new MutableLiveData<>();
            deleteStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            deleteSemesterSuccessful = new MutableLiveData<>();
            deleteSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            semesterList = new ArrayList<>();
            detailedSemesterList = new ArrayList<>();
            semesterActive = false;
            departmentList = new ArrayList<>();
            deptAdminList = new ArrayList<>();
            lecturerList = new ArrayList<>();
            studentList = new ArrayList<>();
            departmentIdInfo = new HashMap<>();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Main_Admin class' constructor method.");
        }
    }

    public static SD_Main_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new SD_Main_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Main_Admin class' getInstance method.");
            return null;
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetSemestersSuccessful() {
        return setSemestersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetDetailedSemestersSuccessful() {
        return setDetailedSemestersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getIsSemesterActiveSuccessful() {
        return isSemesterActiveSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetDepartmentsSuccessful() {
        return setDepartmentsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetDeptAdminsSuccessful() {
        return setDeptAdminsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetLecturersSuccessful() {
        return setLecturersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetStudentsSuccessful() {
        return setStudentsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getAddDepartmentSuccessful() {
        return addDepartmentSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getAddDeptAdminSuccessful() {
        return addDeptAdminSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getAddLecturerSuccessful() {
        return addLecturerSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getAddStudentSuccessful() {
        return addStudentSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getAddSemesterSuccessful() {
        return addSemesterSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getEditDepartmentSuccessful() {
        return editDepartmentSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getEditDeptAdminSuccessful() {
        return editDeptAdminSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getEditLecturerSuccessful() {
        return editLecturerSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getEditStudentSuccessful() {
        return editStudentSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getEditSemesterSuccessful() {
        return editSemesterSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteDepartmentsSuccessful() {
        return deleteDepartmentsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteDeptAdminsSuccessful() {
        return deleteDeptAdminsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteLecturersSuccessful() {
        return deleteLecturersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteStudentsSuccessful() {
        return deleteStudentsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteSemesterSuccessful() {
        return deleteSemesterSuccessful;
    }

    public ArrayList<String> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<String> semesterList) {
        this.semesterList = semesterList;
    }

    public ArrayList<String[]> getDetailedSemesterList() {
        return detailedSemesterList;
    }

    public void setDetailedSemesterList(ArrayList<String[]> detailedSemesterList) {
        this.detailedSemesterList = detailedSemesterList;
    }

    public boolean isSemesterActive() {
        return semesterActive;
    }

    public void setSemesterActive(boolean semesterActive) {
        this.semesterActive = semesterActive;
    }

    public ArrayList<String[]> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<String[]> departmentList) {
        this.departmentList = departmentList;
    }

    public ArrayList<String[]> getDeptAdminList() {
        return deptAdminList;
    }

    public void setDeptAdminList(ArrayList<String[]> deptAdminList) {
        this.deptAdminList = deptAdminList;
    }

    public ArrayList<String[]> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(ArrayList<String[]> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public ArrayList<String[]> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String[]> studentList) {
        this.studentList = studentList;
    }

    public HashMap<String, String> getDepartmentIdInfo() {
        return departmentIdInfo;
    }

    public void setDepartmentIdInfo(HashMap<String, String> departmentIdInfo) {
        this.departmentIdInfo = departmentIdInfo;
    }
}