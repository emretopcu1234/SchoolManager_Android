package com.emretopcu.schoolmanager.viewmodel.sharedData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.deptAdmin.E_Add_Or_Edit_Course_State;

import java.util.ArrayList;
import java.util.HashMap;

public class SD_Dept_Admin {

    private static SD_Dept_Admin INSTANCE;

    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setCoursesSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setStudentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Course_State> addCourseSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Course_State> editCourseSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteCoursesSuccessful;
    private ArrayList<String> semesterList;
    private boolean semesterActive;
    private ArrayList<String[]> courseList;
    private ArrayList<String[]> lecturerList;
    private ArrayList<String[]> studentList;
    private ArrayList<String[]> departmentList;
    private String deptAdminId;

    private SD_Dept_Admin(){
        try{
            setSemestersSuccessful = new MutableLiveData<>();
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            isSemesterActiveSuccessful = new MutableLiveData<>();
            isSemesterActiveSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setCoursesSuccessful = new MutableLiveData<>();
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setLecturersSuccessful = new MutableLiveData<>();
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setStudentsSuccessful = new MutableLiveData<>();
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDepartmentsSuccessful = new MutableLiveData<>();
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            addCourseSuccessful = new MutableLiveData<>();
            addCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.NO_STATEMENT);
            editCourseSuccessful = new MutableLiveData<>();
            editCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.NO_STATEMENT);
            deleteCoursesSuccessful = new MutableLiveData<>();
            deleteCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            semesterList = new ArrayList<>();
            semesterActive = false;
            courseList = new ArrayList<>();
            lecturerList = new ArrayList<>();
            studentList = new ArrayList<>();
            departmentList = new ArrayList<>();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Dept_Admin class' constructor method.");
        }
    }

    public static SD_Dept_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new SD_Dept_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Dept_Admin class' getInstance method.");
            return null;
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetSemestersSuccessful() {
        return setSemestersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getIsSemesterActiveSuccessful() {
        return isSemesterActiveSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetCoursesSuccessful() {
        return setCoursesSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetLecturersSuccessful() {
        return setLecturersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetStudentsSuccessful() {
        return setStudentsSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetDepartmentsSuccessful() {
        return setDepartmentsSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Course_State> getAddCourseSuccessful() {
        return addCourseSuccessful;
    }

    public MutableLiveData<E_Add_Or_Edit_Course_State> getEditCourseSuccessful() {
        return editCourseSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getDeleteCoursesSuccessful() {
        return deleteCoursesSuccessful;
    }

    public ArrayList<String> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<String> semesterList) {
        this.semesterList = semesterList;
    }

    public boolean isSemesterActive() {
        return semesterActive;
    }

    public void setSemesterActive(boolean semesterActive) {
        this.semesterActive = semesterActive;
    }

    public ArrayList<String[]> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<String[]> courseList) {
        this.courseList = courseList;
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

    public ArrayList<String[]> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<String[]> departmentList) {
        this.departmentList = departmentList;
    }

    public String getDeptAdminId() {
        return deptAdminId;
    }

    public void setDeptAdminId(String deptAdminId) {
        this.deptAdminId = deptAdminId;
    }
}