package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Dept_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model_Dept_Admin {

    private static Model_Dept_Admin INSTANCE;
    private Interface_Dept_Admin vmDeptAdmin;

    private FirebaseFirestore dbRef;
    private CollectionReference semestersRef;
    private CollectionReference semesterDeptAdminsRef;
    private CollectionReference departmentsRef;
    private CollectionReference deptAdminsRef;
    private CollectionReference lecturersRef;
    private CollectionReference studentsRef;

    private final HashMap<String,String> departmentsInfo = new HashMap<>();

    private String deptAdminId;
    private String deptAdminName;
    private String deptAdminSurname;
    private String deptId;

    private Model_Dept_Admin(){
        try{
            dbRef = FirebaseFirestore.getInstance();
            semestersRef = dbRef.collection("semesters");
            semesterDeptAdminsRef = dbRef.collection("semesterDeptAdmins");
            departmentsRef = dbRef.collection("departments");
            deptAdminsRef = dbRef.collection("deptAdmins");
            lecturersRef = dbRef.collection("lecturers");
            studentsRef = dbRef.collection("students");
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

    private void storeInitialData(){
        try{
            deptAdminsRef.document(deptAdminId).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    else{
                        deptAdminName = task.getResult().getString("name");
                        deptAdminSurname = task.getResult().getString("surname");
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Dept_Admin class' deptAdminsRef.document(deptAdminId).get().addOnCompleteListener method.");
                }
            });
            departmentsRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentsInfo.put(document.getId().toUpperCase(),document.getString("name"));
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Dept_Admin class' departmentsRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' storeInitialData method.");
        }
    }

    public void getDepartmentIdInfo(){
        try{
            vmDeptAdmin.onDepartmentIdInfo(departmentsInfo);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getDepartmentIdInfo method.");
        }
    }

    public void loadSemesters(){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmDeptAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        semesterList.add(document.getId());
                    }
                    for(int i=0;i<semesterList.size();i++){
                        semesterList.set(i, Common_Services.convertProcessedSemester(semesterList.get(i)));
                    }
                    vmDeptAdmin.onLoadSemestersResulted(semesterList);
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Dept_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' loadSemesters method.");
        }
    }

    public void isSemesterActiveOrFuture(String unprocessedSemester){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            DocumentReference specificSemester = semestersRef.document(semester);
            specificSemester.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmDeptAdmin.dataLoadError();
                        return;
                    }
                    if(Common_Services.isSemesterActive(task.getResult().getTimestamp("startDate"),task.getResult().getTimestamp("endDate"))){
                        vmDeptAdmin.onIsSemesterActiveOrFutureResulted(true);
                    }
                    else if (Common_Services.isSemesterFuture(task.getResult().getTimestamp("startDate"))){
                        vmDeptAdmin.onIsSemesterActiveOrFutureResulted(true);
                    }
                    else{
                        vmDeptAdmin.onIsSemesterActiveOrFutureResulted(false);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Dept_Admin class' specificSemester.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' isSemesterActive method.");
        }
    }

    public void getCourseList(String unprocessedSemester){
        try{
            Log.d("Exception","semester: " + unprocessedSemester);
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

    protected void setDeptAdminId(String deptAdminId) {
        this.deptAdminId = deptAdminId;
        storeInitialData();
    }

    public PersonType getDeptAdminInfo(){
        try{
            PersonType person = new PersonType();
            person.setId(deptAdminId);
            person.setName(deptAdminName);
            person.setSurname(deptAdminSurname);
            person.setDeptId(deptId);
            return person;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getDeptAdminInfo method.");
            return null;
        }
    }
}