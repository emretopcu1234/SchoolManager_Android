package com.emretopcu.schoolmanager.viewmodel.sharedData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.SemesterType;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Department_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Person_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Semester_State;

import java.util.ArrayList;
import java.util.HashMap;

public class SD_Main_Admin {

    private static SD_Main_Admin INSTANCE;

    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDetailedSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveOrFutureSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDeptAdminsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setStudentsSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Department_State> addDepartmentSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> addDeptAdminSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> addLecturerSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> addStudentSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Semester_State> addSemesterSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Department_State> editDepartmentSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> editDeptAdminSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> editLecturerSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Person_State> editStudentSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Semester_State> editSemesterSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteDeptAdminsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteStudentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteSemesterSuccessful;
    private ArrayList<String> semesterList;
    private ArrayList<SemesterType> detailedSemesterList;
    private boolean semesterActiveOrFuture;
    private ArrayList<DepartmentType> departmentList;
    private ArrayList<PersonType> deptAdminList;
    private ArrayList<PersonType> lecturerList;
    private ArrayList<PersonType> studentList;
    private HashMap<String,String> departmentIdInfo;

    private SD_Main_Admin(){
        try{
            setSemestersSuccessful = new MutableLiveData<>();
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDetailedSemestersSuccessful = new MutableLiveData<>();
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            isSemesterActiveOrFutureSuccessful = new MutableLiveData<>();
            isSemesterActiveOrFutureSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDepartmentsSuccessful = new MutableLiveData<>();
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDeptAdminsSuccessful = new MutableLiveData<>();
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setLecturersSuccessful = new MutableLiveData<>();
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setStudentsSuccessful = new MutableLiveData<>();
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addDepartmentSuccessful = new MutableLiveData<>();
            addDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.NO_STATEMENT);
            addDeptAdminSuccessful = new MutableLiveData<>();
            addDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            addLecturerSuccessful = new MutableLiveData<>();
            addLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            addStudentSuccessful = new MutableLiveData<>();
            addStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            addSemesterSuccessful = new MutableLiveData<>();
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.NO_STATEMENT);
            editDepartmentSuccessful = new MutableLiveData<>();
            editDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.NO_STATEMENT);
            editDeptAdminSuccessful = new MutableLiveData<>();
            editDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            editLecturerSuccessful = new MutableLiveData<>();
            editLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            editStudentSuccessful = new MutableLiveData<>();
            editStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            editSemesterSuccessful = new MutableLiveData<>();
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.NO_STATEMENT);
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
            semesterActiveOrFuture = false;
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

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getIsSemesterActiveOrFutureSuccessful() {
        return isSemesterActiveOrFutureSuccessful;
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

    public MutableLiveData<E_Add_Or_Edit_Department_State> getAddDepartmentSuccessful() {
        return addDepartmentSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getAddDeptAdminSuccessful() {
        return addDeptAdminSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getAddLecturerSuccessful() {
        return addLecturerSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getAddStudentSuccessful() {
        return addStudentSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Semester_State> getAddSemesterSuccessful() {
        return addSemesterSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Department_State> getEditDepartmentSuccessful() {
        return editDepartmentSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getEditDeptAdminSuccessful() {
        return editDeptAdminSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getEditLecturerSuccessful() {
        return editLecturerSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Person_State> getEditStudentSuccessful() {
        return editStudentSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Semester_State> getEditSemesterSuccessful() {
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

    public ArrayList<SemesterType> getDetailedSemesterList() {
        return detailedSemesterList;
    }

    public void setDetailedSemesterList(ArrayList<SemesterType> detailedSemesterList) {
        this.detailedSemesterList = detailedSemesterList;
    }

    public boolean isSemesterActiveOrFuture() {
        return semesterActiveOrFuture;
    }

    public void setSemesterActiveOrFuture(boolean semesterActiveOrFuture) {
        this.semesterActiveOrFuture = semesterActiveOrFuture;
    }

    public ArrayList<DepartmentType> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<DepartmentType> departmentList) {
        this.departmentList = departmentList;
    }

    public ArrayList<PersonType> getDeptAdminList() {
        return deptAdminList;
    }

    public void setDeptAdminList(ArrayList<PersonType> deptAdminList) {
        this.deptAdminList = deptAdminList;
    }

    public ArrayList<PersonType> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(ArrayList<PersonType> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public ArrayList<PersonType> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<PersonType> studentList) {
        this.studentList = studentList;
    }

    public HashMap<String, String> getDepartmentIdInfo() {
        return departmentIdInfo;
    }

    public void setDepartmentIdInfo(HashMap<String, String> departmentIdInfo) {
        this.departmentIdInfo = departmentIdInfo;
    }
}