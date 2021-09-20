package com.emretopcu.schoolmanager.model;

import android.hardware.ConsumerIrManager;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Model_Main_Admin {

    private static Model_Main_Admin INSTANCE;
    private Interface_Main_Admin vmMainAdmin;

    private FirebaseFirestore dbRef;
    private CollectionReference semestersRef;
    private CollectionReference semesterConditionsRef;
    private CollectionReference departmentsRef;
    private CollectionReference deptAdminsRef;
    private CollectionReference lecturersRef;
    private CollectionReference studentsRef;

    private HashMap<String,String> departmentsInfo;
    private HashMap<String,String[]> deptAdminsInfo;
    private HashMap<String,String[]> lecturersInfo;
    private HashMap<String,String[]> studentsInfo;

    private Model_Main_Admin(){
        try{
            dbRef = FirebaseFirestore.getInstance();
            semestersRef = dbRef.collection("semesters");
            semesterConditionsRef = dbRef.collection("semesterConditions");
            departmentsRef = dbRef.collection("departments");
            deptAdminsRef = dbRef.collection("deptAdmins");
            lecturersRef = dbRef.collection("lecturers");
            studentsRef = dbRef.collection("students");
            departmentsInfo = new HashMap<>();
            deptAdminsInfo = new HashMap<>();
            lecturersInfo = new HashMap<>();
            studentsInfo = new HashMap<>();
            storeInitialData();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' constructor method.");
        }
    }

    public static Model_Main_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Main_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getInstance method.");
            return null;
        }
    }

    private void storeInitialData(){
        try{
            departmentsRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentsInfo.put(document.getId(),document.getString("name"));
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departmentsRef.get().addOnCompleteListener method.");
                }
            });
            deptAdminsRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        deptAdminsInfo.put(document.getId(),new String[]{document.getString("name"),document.getString("surname")});
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' deptAdminsRef.get().addOnCompleteListener method.");
                }
            });
            lecturersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lecturersInfo.put(document.getId(),new String[]{document.getString("name"),document.getString("surname")});
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' lecturersRef.get().addOnCompleteListener method.");
                }
            });
            studentsRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        Log.d("Exception","Data Load Error on storeInitialData!!!");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        studentsInfo.put(document.getId(),new String[]{document.getString("name"),document.getString("surname")});
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' studentsRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' storePeopleData method.");
        }
    }

    public void loadSemesters(){
        try{
            semestersRef.orderBy("startDate").get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        semesterList.add(document.getId());
                    }
                    for(int i=0;i<semesterList.size();i++){
                        semesterList.set(i, Common_Services.convertProcessedSemester(semesterList.get(i)));
                    }
                    vmMainAdmin.onLoadSemestersResulted(semesterList);
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' loadSemesters method.");
        }
    }

    public void getDetailedSemesterList(){
        try{
            semestersRef.orderBy("startDate").get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String> semesterId = new ArrayList<>();
                    ArrayList<Timestamp> startDate = new ArrayList<>();
                    ArrayList<Timestamp> endDate = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        semesterId.add(document.getId());
                        startDate.add(document.getTimestamp("startDate"));
                        endDate.add(document.getTimestamp("endDate"));
                    }
                    ArrayList<String[]> detailedSemesterList = new ArrayList<>();
                    for(int i=0;i<semesterId.size();i++){
                        if(Common_Services.isSemesterActive(startDate.get(i), endDate.get(i))){
                            detailedSemesterList.add(new String[]{
                                    Common_Services.convertProcessedSemester(semesterId.get(i)),
                                    Common_Services.convertTimestampToDateString(startDate.get(i)),
                                    Common_Services.convertTimestampToDateString(endDate.get(i)),
                                    "ACTIVE"});
                        }
                        else{
                            if(Common_Services.isSemesterFuture(startDate.get(i))){
                                detailedSemesterList.add(new String[]{
                                        Common_Services.convertProcessedSemester(semesterId.get(i)),
                                        Common_Services.convertTimestampToDateString(startDate.get(i)),
                                        Common_Services.convertTimestampToDateString(endDate.get(i)),
                                        "FUTURE"});
                            }
                            detailedSemesterList.add(new String[]{
                                    Common_Services.convertProcessedSemester(semesterId.get(i)),
                                    Common_Services.convertTimestampToDateString(startDate.get(i)),
                                    Common_Services.convertTimestampToDateString(endDate.get(i)),
                                    "PAST"});
                        }
                    }
                    vmMainAdmin.onLoadDetailedSemestersResulted(detailedSemesterList);
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getDetailedSemesterList method.");
        }
    }

    public void isSemesterActive(String unprocessedSemester){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            DocumentReference specificSemester = semestersRef.document(semester);
            specificSemester.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    if(Common_Services.isSemesterActive(task.getResult().getTimestamp("startDate"),task.getResult().getTimestamp("endDate"))){
                        // TODO silinecek kısım başlangıç
                        if(semester.startsWith("spring")){
                            vmMainAdmin.onIsSemesterActiveResulted(false);
                        }
                        else{
                            vmMainAdmin.onIsSemesterActiveResulted(true);
                        }
                        // TODO silinecek kısım son


                        // TODO eklenecek kısım başlangıc
//                        vmMainAdmin.onIsSemesterActiveResulted(true);
                        // TODO eklenecek kısım son
                    }
                    else{
                        vmMainAdmin.onIsSemesterActiveResulted(false);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' specificSemester.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' isSemesterActive method.");
        }
    }

    public void getDepartmentList(String unprocessedSemester){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            CollectionReference departments = semesterConditionsRef.document(semester).collection("departments");
            departments.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> departmentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentList.add(new String[]{document.getString("name"),document.getId().toUpperCase()});
                    }
                    vmMainAdmin.onGetDepartmentListResulted(departmentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departments.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getDepartmentList method.");
        }
    }

    public void getDeptAdminList(String unprocessedSemester){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            CollectionReference deptAdmins = semesterConditionsRef.document(semester).collection("deptAdmins");
            deptAdmins.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> deptAdminList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        deptAdminList.add(new String[]{document.getId(),deptAdminsInfo.get(document.getId())[0],
                                deptAdminsInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetDeptAdminListResulted(deptAdminList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' deptAdmins.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getDeptAdminList method.");
        }
    }

    public void getLecturerList(String unprocessedSemester){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            CollectionReference lecturers = semesterConditionsRef.document(semester).collection("lecturers");
            lecturers.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> lecturerList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lecturerList.add(new String[]{document.getId(),lecturersInfo.get(document.getId())[0],
                                lecturersInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetLecturerListResulted(lecturerList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' lecturers.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getLecturerList method.");
        }
    }

    public void getStudentList(String unprocessedSemester){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            CollectionReference students = semesterConditionsRef.document(semester).collection("students");
            students.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> studentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        studentList.add(new String[]{document.getId(),studentsInfo.get(document.getId())[0],
                                studentsInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetStudentListResulted(studentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' students.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getStudentList method.");
        }
    }

    public void getFilteredDepartmentList(String unprocessedSemester, String unprocessedFilteredDeptName){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            CollectionReference departments = semesterConditionsRef.document(semester).collection("departments");

            departments.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> departmentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentList.add(new String[]{document.getString("name"),document.getId().toUpperCase()});
                    }
                    vmMainAdmin.onGetDepartmentListResulted(departmentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departments.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredDepartmentList method.");
        }
    }
    
    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}