package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.sharedData.SD_Main_Admin;

import java.util.ArrayList;

public class VM_Main_Admin extends ViewModel implements Interface_Main_Admin {


    private SD_Main_Admin sdMainAdmin;
    private Model_Main_Admin modelMainAdmin;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDeptAdminsSuccessful;

    public VM_Main_Admin(){
        try{
            sdMainAdmin = SD_Main_Admin.getInstance();
            modelMainAdmin = Model_Main_Admin.getInstance();
            modelMainAdmin.setVmMainAdmin(this);
            setSemestersSuccessful = sdMainAdmin.getSetSemestersSuccessful();
            isSemesterActiveSuccessful = sdMainAdmin.getIsSemesterActiveSuccessful();
            setDepartmentsSuccessful = sdMainAdmin.getSetDepartmentsSuccessful();
            setDeptAdminsSuccessful = sdMainAdmin.getSetDeptAdminsSuccessful();

        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' constructor method.");
        }
    }

    public void onLoadSemestersRequested(){
        try{
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.loadSemesters();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onLoadSemestersRequested method.");
        }
    }

    public void onSemesterActiveRequested(String selectedSemester){
        try{
            isSemesterActiveSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.isSemesterActive(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onSemesterActiveRequested method.");
        }
    }

    public void onDepartmentListRequested(String selectedSemester){
        try{
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDepartmentList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDepartmentsRequested method.");
        }
    }

    public void onDeptAdminListRequested(String selectedSemester){
        try{
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDeptAdminList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDepartmentsRequested method.");
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetSemestersSuccessful() {
        return setSemestersSuccessful;
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

    public ArrayList<String> getSemesterList(){
        return sdMainAdmin.getSemesterList();
    }

    public boolean isSemesterActive(){
        return sdMainAdmin.isSemesterActive();
    }

    public ArrayList<String[]> getDepartmentList(){
        return sdMainAdmin.getDepartmentList();
    }

    public ArrayList<String[]> getDeptAdminList(){
        return sdMainAdmin.getDeptAdminList();
    }

    @Override
    public void dataLoadError() {
        try{
            Log.d("Exception","Data Load Error!!!");
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' dataLoadError method.");
        }
    }

    @Override
    public void onLoadSemestersResulted(ArrayList<String> semestersList) {
        try{
            sdMainAdmin.setSemesterList(semestersList);
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' setSemesters method.");
        }
    }

    @Override
    public void onIsSemesterActiveResulted(boolean semesterActive) {
        try{
            sdMainAdmin.setSemesterActive(semesterActive);
            isSemesterActiveSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onIsSemesterActiveResulted method.");
        }
    }

    @Override
    public void onGetDepartmentListResulted(ArrayList<String[]> departmentList) {
        try{
            sdMainAdmin.setDepartmentList(departmentList);
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onGetDepartmentListResulted method.");
        }
    }

    @Override
    public void onGetDeptAdminListResulted(ArrayList<String[]> deptAdminList) {
        try{
            sdMainAdmin.setDeptAdminList(deptAdminList);
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onGetDeptAdminListResulted method.");
        }
    }
}
