package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.sharedData.SD_Main_Admin;

import java.util.ArrayList;
import java.util.HashMap;

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

    public VM_Main_Admin(){
        try{
            sdMainAdmin = SD_Main_Admin.getInstance();
            modelMainAdmin = Model_Main_Admin.getInstance();
            modelMainAdmin.setVmMainAdmin(this);
            modelMainAdmin.getDepartmentIdInfo();
            setSemestersSuccessful = sdMainAdmin.getSetSemestersSuccessful();
            setDetailedSemestersSuccessful = sdMainAdmin.getSetDetailedSemestersSuccessful();
            isSemesterActiveSuccessful = sdMainAdmin.getIsSemesterActiveSuccessful();
            setDepartmentsSuccessful = sdMainAdmin.getSetDepartmentsSuccessful();
            setDeptAdminsSuccessful = sdMainAdmin.getSetDeptAdminsSuccessful();
            setLecturersSuccessful = sdMainAdmin.getSetLecturersSuccessful();
            setStudentsSuccessful = sdMainAdmin.getSetStudentsSuccessful();
            addDepartmentSuccessful = sdMainAdmin.getAddDepartmentSuccessful();
            addDeptAdminSuccessful = sdMainAdmin.getAddDeptAdminSuccessful();
            addLecturerSuccessful = sdMainAdmin.getAddLecturerSuccessful();
            addStudentSuccessful = sdMainAdmin.getAddStudentSuccessful();
            addSemesterSuccessful = sdMainAdmin.getAddSemesterSuccessful();
            editDepartmentSuccessful = sdMainAdmin.getEditDepartmentSuccessful();
            editDeptAdminSuccessful = sdMainAdmin.getEditDeptAdminSuccessful();
            editLecturerSuccessful = sdMainAdmin.getEditLecturerSuccessful();
            editStudentSuccessful = sdMainAdmin.getEditStudentSuccessful();
            editSemesterSuccessful = sdMainAdmin.getEditSemesterSuccessful();
            deleteDepartmentsSuccessful = sdMainAdmin.getDeleteDepartmentsSuccessful();
            deleteDeptAdminsSuccessful = sdMainAdmin.getDeleteDeptAdminsSuccessful();
            deleteLecturersSuccessful = sdMainAdmin.getDeleteLecturersSuccessful();
            deleteStudentsSuccessful = sdMainAdmin.getDeleteStudentsSuccessful();
            deleteSemesterSuccessful = sdMainAdmin.getDeleteSemesterSuccessful();
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

    public void onFilteredDeptAdminListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter, ArrayList<String> deptFilter){
        try{
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredDeptAdminList(selectedSemester, idFilter, nameFilter, surnameFilter, deptFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredDeptAdminListRequested method.");
        }
    }

    public void onFilteredLecturerListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter, ArrayList<String> deptFilter){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredLecturerList(selectedSemester, idFilter, nameFilter, surnameFilter, deptFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredLecturerListRequested method.");
        }
    }

    public void onFilteredStudentListRequested(String selectedSemester, String idFilter, String nameFilter, String surnameFilter, ArrayList<String> deptFilter){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredStudentList(selectedSemester, idFilter, nameFilter, surnameFilter, deptFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredStudentListRequested method.");
        }
    }

    public void onAddDepartmentRequested(String deptName, String deptId){
        try{
            addDepartmentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.addDepartment(deptName, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminRequested method.");
        }
    }

    public void onAddDeptAdminRequested(String id, String name, String surname, String deptId){
        try{
            addDeptAdminSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.addDeptAdmin(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminRequested method.");
        }
    }

    public void onAddLecturerRequested(String id, String name, String surname, String deptId){
        try{
            addLecturerSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.addLecturer(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddLecturerRequested method.");
        }
    }

    public void onAddStudentRequested(String id, String name, String surname, String deptId){
        try{
            addStudentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.addStudent(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddStudentRequested method.");
        }
    }

    public void onAddSemesterRequested(String startDate, String endDate){
        try{
            addSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.addSemester(startDate, endDate);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterRequested method.");
        }
    }

    public void onEditDepartmentRequested(String deptName, String deptId){
        try{
            editDepartmentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.editDepartment(deptName, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDeptAdminRequested method.");
        }
    }

    public void onEditDeptAdminRequested(String id, String name, String surname, String deptId){
        try{
            editDeptAdminSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.editDeptAdmin(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDeptAdminRequested method.");
        }
    }

    public void onEditLecturerRequested(String id, String name, String surname, String deptId){
        try{
            editLecturerSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.editLecturer(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditLecturerRequested method.");
        }
    }

    public void onEditStudentRequested(String id, String name, String surname, String deptId){
        try{
            editStudentSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.editStudent(id, name, surname, deptId);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditStudentRequested method.");
        }
    }

    public void onEditSemesterRequested(String semesterId, String startDate, String endDate){
        try{
            editSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.editSemester(semesterId, startDate, endDate);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterRequested method.");
        }
    }

    public void onDeleteDepartmentsRequested(String selectedSemester, ArrayList<String> idList){
        try{
            deleteDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.deleteDepartments(selectedSemester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDepartmentsRequested method.");
        }
    }

    public void onDeleteDeptAdminsRequested(String selectedSemester, ArrayList<String> idList){
        try{
            deleteDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.deleteDeptAdmins(selectedSemester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDeptAdminsRequested method.");
        }
    }

    public void onDeleteLecturersRequested(String selectedSemester, ArrayList<String> idList){
        try{
            deleteLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.deleteLecturers(selectedSemester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteLecturersRequested method.");
        }
    }

    public void onDeleteStudentsRequested(String selectedSemester, ArrayList<String> idList){
        try{
            deleteStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.deleteStudents(selectedSemester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteStudentsRequested method.");
        }
    }

    public void onDeleteSemesterRequested(String selectedSemester){
        try{
            deleteSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
//            modelMainAdmin.deleteSemester(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteSemesterRequested method.");
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

    public HashMap<String, String> getDepartmentIdInfo() {
        return sdMainAdmin.getDepartmentIdInfo();
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
    public void onDepartmentIdInfo(HashMap<String, String> departmentIdInfo) {
        sdMainAdmin.setDepartmentIdInfo(departmentIdInfo);
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

    @Override
    public void onAddSemesterResultedSuccessful() {
        addSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
        modelMainAdmin.getDetailedSemesterList();
    }

    @Override
    public void onAddSemesterResultedReverseOrder() {
        // TODO
    }

    @Override
    public void onAddSemesterResultedLowDateDifference() {
        // TODO
    }

    @Override
    public void onAddSemesterResultedHighDateDifference() {
        // TODO
    }
}
