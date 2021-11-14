package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonFilterType;
import com.emretopcu.schoolmanager.model.Model_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Department_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Person_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Semester_State;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.sharedData.SD_Main_Admin;

import java.util.ArrayList;
import java.util.HashMap;

public class VM_Main_Admin extends ViewModel implements Interface_Main_Admin {

    private SD_Main_Admin sdMainAdmin;
    private Model_Main_Admin modelMainAdmin;
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

    private String lastProcessedSemester;

    public VM_Main_Admin(){
        try{
            sdMainAdmin = SD_Main_Admin.getInstance();
            modelMainAdmin = Model_Main_Admin.getInstance();
            modelMainAdmin.setVmMainAdmin(this);
            modelMainAdmin.getDepartmentIdInfo();
            setSemestersSuccessful = sdMainAdmin.getSetSemestersSuccessful();
            setDetailedSemestersSuccessful = sdMainAdmin.getSetDetailedSemestersSuccessful();
            isSemesterActiveOrFutureSuccessful = sdMainAdmin.getIsSemesterActiveOrFutureSuccessful();
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

    public void onSemesterActiveOrFutureRequested(String selectedSemester){
        try{
            isSemesterActiveOrFutureSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.isSemesterActiveOrFuture(selectedSemester);
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

    public void onFilteredDepartmentListRequested(DepartmentFilterType departmentFilter){
        try{
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredDepartmentList(departmentFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredDepartmentListRequested method.");
        }
    }

    public void onFilteredDeptAdminListRequested(PersonFilterType personFilter){
        try{
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredDeptAdminList(personFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredDeptAdminListRequested method.");
        }
    }

    public void onFilteredLecturerListRequested(PersonFilterType personFilter){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredLecturerList(personFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredLecturerListRequested method.");
        }
    }

    public void onFilteredStudentListRequested(PersonFilterType personFilter){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getFilteredStudentList(personFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onFilteredStudentListRequested method.");
        }
    }

    public void onAddDepartmentRequested(DepartmentAddOrEditType department){
        try{
            lastProcessedSemester = department.getSemester();
            addDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.NO_STATEMENT);
            modelMainAdmin.addDepartment(department);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminRequested method.");
        }
    }

    public void onAddDeptAdminRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            addDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.addDeptAdmin(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminRequested method.");
        }
    }

    public void onAddLecturerRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            addLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.addLecturer(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddLecturerRequested method.");
        }
    }

    public void onAddStudentRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            addStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.addStudent(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddStudentRequested method.");
        }
    }

    public void onAddSemesterRequested(String startDate, String endDate){
        try{
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.NO_STATEMENT);
            modelMainAdmin.addSemester(startDate, endDate);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterRequested method.");
        }
    }

    public void onEditDepartmentRequested(DepartmentAddOrEditType department){
        try{
            lastProcessedSemester = department.getSemester();
            editDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.NO_STATEMENT);
            modelMainAdmin.editDepartment(department);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDeptAdminRequested method.");
        }
    }

    public void onEditDeptAdminRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            editDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.editDeptAdmin(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDeptAdminRequested method.");
        }
    }

    public void onEditLecturerRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            editLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.editLecturer(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditLecturerRequested method.");
        }
    }

    public void onEditStudentRequested(PersonAddOrEditType person){
        try{
            lastProcessedSemester = person.getSemester();
            editStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.NO_STATEMENT);
            modelMainAdmin.editStudent(person);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditStudentRequested method.");
        }
    }

    public void onEditSemesterRequested(String startDate, String endDate){
        try{
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.NO_STATEMENT);
            modelMainAdmin.editSemester(startDate, endDate);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterRequested method.");
        }
    }

    public void onDeleteDepartmentsRequested(String semester, ArrayList<String> idList){
        try{
            lastProcessedSemester = semester;
            deleteDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.deleteDepartments(semester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDepartmentsRequested method.");
        }
    }

    public void onDeleteDeptAdminsRequested(String semester, ArrayList<String> idList){
        try{
            lastProcessedSemester = semester;
            deleteDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.deleteDeptAdmins(semester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDeptAdminsRequested method.");
        }
    }

    public void onDeleteLecturersRequested(String semester, ArrayList<String> idList){
        try{
            lastProcessedSemester = semester;
            deleteLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.deleteLecturers(semester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteLecturersRequested method.");
        }
    }

    public void onDeleteStudentsRequested(String semester, ArrayList<String> idList){
        try{
            lastProcessedSemester = semester;
            deleteStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.deleteStudents(semester, idList);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteStudentsRequested method.");
        }
    }

    public void onDeleteSemesterRequested(String selectedSemester){
        try{
            deleteSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.deleteSemester(selectedSemester);
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

    public ArrayList<String> getSemesterList(){
        return sdMainAdmin.getSemesterList();
    }

    public ArrayList<String[]> getDetailedSemesterList(){
        return sdMainAdmin.getDetailedSemesterList();
    }

    public boolean isSemesterActiveOrFuture(){
        return sdMainAdmin.isSemesterActiveOrFuture();
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
        try{
            sdMainAdmin.setDepartmentIdInfo(departmentIdInfo);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDepartmentIdInfo method.");
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
    public void onIsSemesterActiveOrFutureResulted(boolean semesterActiveOrFuture) {
        try{
            sdMainAdmin.setSemesterActiveOrFuture(semesterActiveOrFuture);
            isSemesterActiveOrFutureSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
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
    public void onAddDepartmentResultedSuccessful() {
        try{
            addDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.SUCCESSFUL);
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDepartmentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDepartmentResultedSuccessful method.");
        }
    }

    @Override
    public void onAddDepartmentResultedDuplicatedId() {
        try{
            addDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_ID);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDepartmentResultedDuplicatedId method.");
        }
    }

    @Override
    public void onAddDepartmentResultedDuplicatedName() {
        try{
            addDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_NAME);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDepartmentResultedDuplicatedName method.");
        }
    }

    @Override
    public void onAddDeptAdminResultedSuccessful() {
        try{
            addDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDeptAdminList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminResultedSuccessful method.");
        }
    }

    @Override
    public void onAddDeptAdminResultedDuplicatedId() {
        try{
            addDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.UNSUCCESSFUL_DUPLICATED_ID);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddDeptAdminResultedDuplicatedId method.");
        }
    }

    @Override
    public void onAddLecturerResultedSuccessful() {
        try{
            addLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getLecturerList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddLecturerResultedSuccessful method.");
        }
    }

    @Override
    public void onAddLecturerResultedDuplicatedId() {
        try{
            addLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.UNSUCCESSFUL_DUPLICATED_ID);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddLecturerResultedDuplicatedId method.");
        }
    }

    @Override
    public void onAddStudentResultedSuccessful() {
        try{
            addStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getStudentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddStudentResultedSuccessful method.");
        }
    }

    @Override
    public void onAddStudentResultedDuplicatedId() {
        try{
            addStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.UNSUCCESSFUL_DUPLICATED_ID);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddStudentResultedDuplicatedId method.");
        }
    }

    @Override
    public void onAddSemesterResultedSuccessful() {
        try{
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.SUCCESSFUL);
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDetailedSemesterList();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterResultedSuccessful method.");
        }
    }

    @Override
    public void onAddSemesterResultedReverseOrder() {
        try{
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_REVERSE_ORDER);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterResultedReverseOrder method.");
        }
    }

    @Override
    public void onAddSemesterResultedLowDateDifference() {
        try{
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_LOW_DIFF);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterResultedLowDateDifference method.");
        }
    }

    @Override
    public void onAddSemesterResultedHighDateDifference() {
        try{
            addSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_HIGH_DIFF);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onAddSemesterResultedHighDateDifference method.");
        }
    }

    @Override
    public void onEditDepartmentResultedSuccessful() {
        try{
            editDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.SUCCESSFUL);
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDepartmentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDepartmentResultedSuccessful method.");
        }
    }

    @Override
    public void onEditDepartmentResultedDuplicatedName() {
        try{
            editDepartmentSuccessful.setValue(E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_NAME);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDepartmentResultedDuplicatedName method.");
        }
    }

    @Override
    public void onEditDeptAdminResultedSuccessful() {
        try{
            editDeptAdminSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDeptAdminList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditDeptAdminResultedSuccessful method.");
        }
    }

    @Override
    public void onEditLecturerResultedSuccessful() {
        try{
            editLecturerSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getLecturerList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditLecturerResultedSuccessful method.");
        }
    }

    @Override
    public void onEditStudentResultedSuccessful() {
        try{
            editStudentSuccessful.setValue(E_Add_Or_Edit_Person_State.SUCCESSFUL);
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getStudentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditStudentResultedSuccessful method.");
        }
    }

    @Override
    public void onEditSemesterResultedSuccessful() {
        try{
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.SUCCESSFUL);
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDetailedSemesterList();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterResultedSuccessful method.");
        }
    }

    @Override
    public void onEditSemesterResultedReverseOrder() {
        try{
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_REVERSE_ORDER);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterResultedReverseOrder method.");
        }
    }

    @Override
    public void onEditSemesterResultedLowDateDifference() {
        try{
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_LOW_DIFF);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterResultedLowDateDifference method.");
        }
    }

    @Override
    public void onEditSemesterResultedHighDateDifference() {
        try{
            editSemesterSuccessful.setValue(E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_HIGH_DIFF);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onEditSemesterResultedHighDateDifference method.");
        }
    }

    @Override
    public void onDeleteDepartmentsResultedSuccessful() {
        try{
            deleteDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDepartmentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDepartmentsResultedSuccessful method.");
        }
    }

    @Override
    public void onDeleteDeptAdminsResultedSuccessful() {
        try{
            deleteDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setDeptAdminsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDeptAdminList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteDeptAdminsResultedSuccessful method.");
        }
    }

    @Override
    public void onDeleteLecturersResultedSuccessful() {
        try{
            deleteLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getLecturerList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteLecturersResultedSuccessful method.");
        }
    }

    @Override
    public void onDeleteStudentsResultedSuccessful() {
        try{
            deleteStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getStudentList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteStudentsResultedSuccessful method.");
        }
    }

    @Override
    public void onDeleteSemesterResultedSuccessful() {
        try{
            deleteSemesterSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setDetailedSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelMainAdmin.getDetailedSemesterList();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' onDeleteSemesterResultedSuccessful method.");
        }
    }
}