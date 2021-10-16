package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Dept_Admin;

import java.util.ArrayList;

public class Model_Dept_Admin {

    private static Model_Dept_Admin INSTANCE;
    private Interface_Dept_Admin vmDeptAdmin;

    private String deptAdminId;

    private Model_Dept_Admin(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' constructor method.");
        }
    }

    public static Model_Dept_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Dept_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getInstance method.");
            return null;
        }
    }

    public void loadSemesters(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' loadSemesters method.");
        }
    }

    public void isSemesterActive(String unprocessedSemester){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' isSemesterActive method.");
        }
    }

    public void getCourseList(String unprocessedSemester){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getCourseList method.");
        }
    }

    public void getLecturerList(String unprocessedSemester){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getLecturerList method.");
        }
    }

    public void getStudentList(String unprocessedSemester){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getStudentList method.");
        }
    }

    public void getDepartmentList(String unprocessedSemester){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getDepartmentList method.");
        }
    }

    public void getFilteredCourseList(String unprocessedSemester, String idFilter, String unprocessedNameFilter){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getFilteredCourseList method.");
        }
    }

    public void getFilteredLecturerList(String unprocessedSemester, String idFilter,
                                        String unprocessedNameFilter, String unprocessedSurnameFilter){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getFilteredLecturerList method.");
        }
    }

    public void getFilteredStudentList(String unprocessedSemester, String idFilter, String unprocessedNameFilter,
                                       String unprocessedSurnameFilter, ArrayList<String> deptFilter){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getFilteredStudentList method.");
        }
    }

    public void addCourse(String courseId, String unprocessedCourseName, String sections){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' addCourse method.");
        }
    }

    public void editCourse(String courseId, String unprocessedCourseName, String sections){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' editCourse method.");
        }
    }

    public void deleteCourses(ArrayList<String> idList){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' deleteCourses method.");
        }
    }

    public void setVmDeptAdmin(Interface_Dept_Admin vmDeptAdmin) {
        this.vmDeptAdmin = vmDeptAdmin;
    }

    public String getDeptAdminId() {
        return deptAdminId;
    }

    protected void setDeptAdminId(String deptAdminId) {
        this.deptAdminId = deptAdminId;
    }
}