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
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDetailedSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDeptAdminsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setStudentsSuccessful;

    public VM_Main_Admin(){
        try{
            sdMainAdmin = SD_Main_Admin.getInstance();
            modelMainAdmin = Model_Main_Admin.getInstance();
            modelMainAdmin.setVmMainAdmin(this);
            setSemestersSuccessful = sdMainAdmin.getSetSemestersSuccessful();
            setDetailedSemestersSuccessful = sdMainAdmin.getSetDetailedSemestersSuccessful();
            isSemesterActiveSuccessful = sdMainAdmin.getIsSemesterActiveSuccessful();
            setDepartmentsSuccessful = sdMainAdmin.getSetDepartmentsSuccessful();
            setDeptAdminsSuccessful = sdMainAdmin.getSetDeptAdminsSuccessful();
            setLecturersSuccessful = sdMainAdmin.getSetLecturersSuccessful();
            setStudentsSuccessful = sdMainAdmin.getSetStudentsSuccessful();
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

    public void onDetailedSemesterListRequested(){
        try{
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDetailedSemesterList();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDetailedSemesterListRequested method.");
        }
    }

    public void onDepartmentListRequested(String selectedSemester){
        try{
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDepartmentList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDepartmentListRequested method.");
        }
    }

    public void onDeptAdminListRequested(String selectedSemester){
        try{
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDeptAdminList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeptAdminListRequested method.");
        }
    }

    public void onLecturerListRequested(String selectedSemester){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getLecturerList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onLecturerListRequested method.");
        }
    }

    public void onStudentListRequested(String selectedSemester){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getStudentList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onLecturerListRequested method.");
        }
    }

    public void onFilteredDepartmentListRequested(String selectedSemester, String deptNameFilter){
        try{
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredDepartmentList(selectedSemester, deptNameFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredDepartmentListRequested method.");
        }
    }

    public void onFilteredDeptAdminListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter){
        try{
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.getFilteredDeptAdminList(selectedSemester, idFilter, nameFilter, surnameFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredDeptAdminListRequested method.");
        }
    }

    public void onFilteredLecturerListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.getFilteredLecturerList(selectedSemester, idFilter, nameFilter, surnameFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredLecturerListRequested method.");
        }
    }

    public void onFilteredStudentListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.getFilteredStudentList(selectedSemester, idFilter, nameFilter, surnameFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredStudentListRequested method.");
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

    public ArrayList<String> getSemesterList(){
        return sdMainAdmin.getSemesterList();
    }

    public ArrayList<String[]> getDetailedSemesterList(){
        return sdMainAdmin.getDetailedSemesterList();
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

    public ArrayList<String[]> getLecturerList(){
        return sdMainAdmin.getLecturerList();
    }

    public ArrayList<String[]> getStudentList(){
        return sdMainAdmin.getStudentList();
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
            Log.d("Exception", "Exception on VM_Main_Admin class' onLoadSemestersResulted method.");
        }
    }

    @Override
    public void onLoadDetailedSemestersResulted(ArrayList<String[]> detailedSemestersList) {
        try{
            sdMainAdmin.setDetailedSemesterList(detailedSemestersList);
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onLoadDetailedSemestersResulted method.");
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

    @Override
    public void onGetLecturerListResulted(ArrayList<String[]> lecturerList) {
        try{
            sdMainAdmin.setLecturerList(lecturerList);
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onGetLecturerListResulted method.");
        }
    }

    @Override
    public void onGetStudentListResulted(ArrayList<String[]> studentList) {
        try{
            sdMainAdmin.setStudentList(studentList);
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onGetStudentListResulted method.");
        }
    }
}
