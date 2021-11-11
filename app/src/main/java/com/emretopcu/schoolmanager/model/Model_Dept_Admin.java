package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

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
    private CollectionReference semesterConditionsRef;
    private CollectionReference departmentsRef;
    private CollectionReference deptAdminsRef;
    private CollectionReference lecturersRef;
    private CollectionReference studentsRef;

    private String deptAdminId;
    private String deptAdminName;
    private String deptAdminSurname;
    private ArrayList<String> semesterList;
    private ArrayList<String> allNonFutureSemesterList;
    private HashMap<String, String> deptAdminDepartmentCondition;


    private Model_Dept_Admin(){
        try{
            dbRef = FirebaseFirestore.getInstance();
            semestersRef = dbRef.collection("semesters");
            semesterConditionsRef = dbRef.collection("semesterConditions");
            departmentsRef = dbRef.collection("departments");
            deptAdminsRef = dbRef.collection("deptAdmins");
            lecturersRef = dbRef.collection("lecturers");
            studentsRef = dbRef.collection("students");
            semesterList = new ArrayList<>();
            allNonFutureSemesterList = new ArrayList<>();
            deptAdminDepartmentCondition = new HashMap<>();
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
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    allNonFutureSemesterList.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(!Common_Services.isSemesterFuture(document.getTimestamp("startDate"))){
                            allNonFutureSemesterList.add(document.getId());
                        }
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Dept_Admin class' semestersRef.orderBy(\"startDate\", Query.Direction.DESCENDING).get().addOnCompleteListener method.");
                }
            });






        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' storeInitialData method.");
        }
    }

    public void loadSemesters(){
        try{
            semesterList.clear();
            for(int i=0;i<allNonFutureSemesterList.size();i++){
                int count = i;
                Log.d("Exception","count " + count);
                String semester = allNonFutureSemesterList.get(i);
                semesterConditionsRef.document(semester).collection("deptAdmins").get().addOnCompleteListener(task1 -> {
                    try{
                        if(!task1.isSuccessful()){
                            Log.d("Exception","Data Load Error on storeInitialData!!!");
                            return;
                        }
                        else{
                            for (QueryDocumentSnapshot document1 : task1.getResult()) {
                                if(document1.getId().equals(deptAdminId)){
                                    semesterList.add(semester);
                                    deptAdminDepartmentCondition.put(semester,document1.getString("deptId"));
                                    break;
                                }
                            }
                            if(count == allNonFutureSemesterList.size()-1){
                                ArrayList<String> unprocessedSemesterList = new ArrayList<>();
                                for(String s: semesterList){
                                    unprocessedSemesterList.add(Common_Services.convertProcessedSemester(s));
                                }
                                vmDeptAdmin.onLoadSemestersResulted(unprocessedSemesterList);
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Model_Dept_Admin class' semesterConditionsRef.document(semester).collection(\"deptAdmins\").get().addOnCompleteListener method.");
                    }
                });
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' loadSemesters method.");
        }
    }

    public void isSemesterActive(String unprocessedSemester){
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
                        vmDeptAdmin.onIsSemesterActiveResulted(true);
                    }
                    else{
                        vmDeptAdmin.onIsSemesterActiveResulted(false);
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
        try{
            this.deptAdminId = deptAdminId;
            storeInitialData();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' setDeptAdminId method.");
        }
    }
}