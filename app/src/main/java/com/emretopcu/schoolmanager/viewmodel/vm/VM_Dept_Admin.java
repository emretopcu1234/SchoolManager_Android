package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.commonObjectTypes.CourseAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseDeleteType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseType;
import com.emretopcu.schoolmanager.commonObjectTypes.DepartmentType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.model.Model_Dept_Admin;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.deptAdmin.E_Add_Or_Edit_Course_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Person_State;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Dept_Admin;
import com.emretopcu.schoolmanager.viewmodel.sharedData.SD_Dept_Admin;

import java.util.ArrayList;
import java.util.HashMap;

public class VM_Dept_Admin extends ViewModel implements Interface_Dept_Admin {

    private SD_Dept_Admin sdDeptAdmin;
    private Model_Dept_Admin modelDeptAdmin;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> personInfoSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveOrFutureSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setCoursesSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setLecturersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setStudentsSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Course_State> addCourseSuccessful;
    private MutableLiveData<E_Add_Or_Edit_Course_State> editCourseSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> deleteCoursesSuccessful;

    private String lastProcessedSemester;

    public VM_Dept_Admin(){
        try{
            sdDeptAdmin = SD_Dept_Admin.getInstance();
            modelDeptAdmin = Model_Dept_Admin.getInstance();
            modelDeptAdmin.setVmDeptAdmin(this);
            modelDeptAdmin.getDepartmentIdInfo();
            personInfoSuccessful = sdDeptAdmin.getPersonInfoSuccessful();
            setSemestersSuccessful = sdDeptAdmin.getSetSemestersSuccessful();
            isSemesterActiveOrFutureSuccessful = sdDeptAdmin.getIsSemesterActiveOrFutureSuccessful();
            setCoursesSuccessful = sdDeptAdmin.getSetCoursesSuccessful();
            setLecturersSuccessful = sdDeptAdmin.getSetLecturersSuccessful();
            setStudentsSuccessful = sdDeptAdmin.getSetStudentsSuccessful();
            setDepartmentsSuccessful = sdDeptAdmin.getSetDepartmentsSuccessful();
            addCourseSuccessful = sdDeptAdmin.getAddCourseSuccessful();
            editCourseSuccessful = sdDeptAdmin.getEditCourseSuccessful();
            deleteCoursesSuccessful = sdDeptAdmin.getDeleteCoursesSuccessful();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' constructor method.");
        }
    }

    public void onLoadSemestersRequested(){
        try{
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.loadSemesters();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onLoadSemestersRequested method.");
        }
    }

    public void onSemesterActiveOrFutureRequested(String selectedSemester){
        try{
            isSemesterActiveOrFutureSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.isSemesterActiveOrFuture(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onSemesterActiveRequested method.");
        }
    }

    public void onPersonInfoRequested(String selectedSemester){
        try{
            personInfoSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getDeptAdminInfo(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onPersonInfoRequested method.");
        }
    }

    public void onCourseListRequested(String selectedSemester){
        try{
            lastProcessedSemester = selectedSemester;
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getCourseList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onCourseListRequested method.");
        }
    }

    public void onLecturerListRequested(String selectedSemester){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getLecturerList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onLecturerListRequested method.");
        }
    }

    public void onStudentListRequested(String selectedSemester){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getStudentList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onLecturerListRequested method.");
        }
    }

    public void onDepartmentListRequested(String selectedSemester){
        try{
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getDepartmentList(selectedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onDepartmentListRequested method.");
        }
    }

    public void onFilteredCourseListRequested(CourseFilterType courseFilter){
        try{
            lastProcessedSemester = courseFilter.getSemester();
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getFilteredCourseList(courseFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onFilteredCourseListRequested method.");
        }
    }

    public void onFilteredLecturerListRequested(PersonFilterType personFilter){
        try{
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getFilteredLecturerList(personFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onFilteredLecturerListRequested method.");
        }
    }

    public void onFilteredStudentListRequested(PersonFilterType personFilter){
        try{
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getFilteredStudentList(personFilter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onFilteredStudentListRequested method.");
        }
    }

    public void onAddCourseRequested(CourseAddOrEditType course){
        try{
            lastProcessedSemester = course.getSemester();
            addCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.NO_STATEMENT);
            modelDeptAdmin.addCourse(course);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onAddCourseRequested method.");
        }
    }

    public void onEditCourseRequested(CourseAddOrEditType course){
        try{
            lastProcessedSemester = course.getSemester();
            editCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.NO_STATEMENT);
            modelDeptAdmin.editCourse(course);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onEditCourseRequested method.");
        }
    }

    public void onDeleteCoursesRequested(CourseDeleteType courses){
        try{
            deleteCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.deleteCourses(courses);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onDeleteCoursesRequested method.");
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getPersonInfoSuccessful() {
        return personInfoSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetSemestersSuccessful() {
        return setSemestersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getIsSemesterActiveOrFutureSuccessful() {
        return isSemesterActiveOrFutureSuccessful;
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

    public ArrayList<String> getSemesterList(){
        return sdDeptAdmin.getSemesterList();
    }

    public boolean isSemesterActive(){
        return sdDeptAdmin.isSemesterActive();
    }

    public ArrayList<CourseType> getCourseList(){
        return sdDeptAdmin.getCourseList();
    }

    public ArrayList<PersonType> getLecturerList(){
        return sdDeptAdmin.getLecturerList();
    }

    public ArrayList<PersonType> getStudentList(){
        return sdDeptAdmin.getStudentList();
    }

    public ArrayList<DepartmentType> getDepartmentList(){
        return sdDeptAdmin.getDepartmentList();
    }

    public PersonType getDeptAdminInfo(){
        return sdDeptAdmin.getDeptAdminInfo();
    }

    @Override
    public void dataLoadError() {
        try{
            Log.d("Exception","Data Load Error!!!");
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' dataLoadError method.");
        }
    }

    @Override
    public void onDepartmentIdInfo(HashMap<String, String> departmentIdInfo) {
        try{
            sdDeptAdmin.setDepartmentIdInfo(departmentIdInfo);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onDepartmentIdInfo method.");
        }
    }

    @Override
    public void onLoadSemestersResulted(ArrayList<String> semestersList) {
        try{
            sdDeptAdmin.setSemesterList(semestersList);
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onLoadSemestersResulted method.");
        }
    }

    @Override
    public void onIsSemesterActiveOrFutureResulted(boolean semesterActive) {
        try{
            sdDeptAdmin.setSemesterActive(semesterActive);
            isSemesterActiveOrFutureSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onIsSemesterActiveResulted method.");
        }
    }

    @Override
    public void onDeptAdminInfoResulted(PersonType person) {
        try{
            sdDeptAdmin.setDeptAdminInfo(person);
            personInfoSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onDeptAdminInfoResulted method.");
        }
    }

    @Override
    public void onGetCourseListResulted(ArrayList<CourseType> courseList) {
        try{
            sdDeptAdmin.setCourseList(courseList);
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onGetCourseListResulted method.");
        }
    }

    @Override
    public void onGetLecturerListResulted(ArrayList<PersonType> lecturerList) {
        try{
            sdDeptAdmin.setLecturerList(lecturerList);
            setLecturersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onGetLecturerListResulted method.");
        }
    }

    @Override
    public void onGetStudentListResulted(ArrayList<PersonType> studentList) {
        try{
            sdDeptAdmin.setStudentList(studentList);
            setStudentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onGetStudentListResulted method.");
        }
    }

    @Override
    public void onGetDepartmentListResulted(ArrayList<DepartmentType> departmentList) {
        try{
            sdDeptAdmin.setDepartmentList(departmentList);
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onGetDepartmentListResulted method.");
        }
    }

    @Override
    public void onAddCourseResultedSuccessful() {
        try{
            addCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.SUCCESSFUL);
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getCourseList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onAddCourseResultedSuccessful method.");
        }
    }

    @Override
    public void onAddCourseResultedDuplicatedCourseId() {
        try{
            addCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.UNSUCCESSFUL_DUPLICATED_ID);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onAddCourseResultedDuplicatedCourseId method.");
        }
    }

    @Override
    public void onAddCourseResultedDuplicatedCourseName() {
        try{
            addCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.UNSUCCESSFUL_DUPLICATED_NAME);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onAddCourseResultedDuplicatedCourseName method.");
        }
    }

    @Override
    public void onEditCourseResultedSuccessful() {
        try{
            editCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.SUCCESSFUL);
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getCourseList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onEditCourseResultedSuccessful method.");
        }
    }

    @Override
    public void onEditCourseResultedDuplicatedCourseName() {
        try{
            editCourseSuccessful.setValue(E_Add_Or_Edit_Course_State.UNSUCCESSFUL_DUPLICATED_NAME);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onEditCourseResultedDuplicatedCourseName method.");
        }
    }

    @Override
    public void onDeleteCoursesResultedSuccessful() {
        try{
            deleteCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            setCoursesSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            modelDeptAdmin.getCourseList(lastProcessedSemester);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' onDeleteCoursesResultedSuccessful method.");
        }
    }
}