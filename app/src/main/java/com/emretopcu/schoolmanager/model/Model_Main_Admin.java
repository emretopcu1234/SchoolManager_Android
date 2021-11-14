package com.emretopcu.schoolmanager.model;

import android.text.format.Time;
import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonFilterType;
import com.emretopcu.schoolmanager.model.pojo.Semester;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model_Main_Admin {

    private static Model_Main_Admin INSTANCE;
    private Interface_Main_Admin vmMainAdmin;

    private FirebaseFirestore dbRef;
    private CollectionReference semestersRef;
    private CollectionReference semesterDepartmentsRef;
    private CollectionReference semesterDeptAdminsRef;
    private CollectionReference semesterLecturersRef;
    private CollectionReference semesterStudentsRef;

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
            semesterDepartmentsRef = dbRef.collection("semesterDepartments");
            semesterDeptAdminsRef = dbRef.collection("semesterDeptAdmins");
            semesterLecturersRef = dbRef.collection("semesterLecturers");
            semesterStudentsRef = dbRef.collection("semesterStudents");

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
                        departmentsInfo.put(document.getId().toUpperCase(),document.getString("name"));
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

    public void getDepartmentIdInfo(){
        try{
            vmMainAdmin.onDepartmentIdInfo(departmentsInfo);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getInitialDepartmentInfo method.");
        }
    }

    public void loadSemesters(){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
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
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
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
                            else{
                                detailedSemesterList.add(new String[]{
                                        Common_Services.convertProcessedSemester(semesterId.get(i)),
                                        Common_Services.convertTimestampToDateString(startDate.get(i)),
                                        Common_Services.convertTimestampToDateString(endDate.get(i)),
                                        "PAST"});
                            }
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

    public void isSemesterActiveOrFuture(String unprocessedSemester){
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
                        vmMainAdmin.onIsSemesterActiveOrFutureResulted(true);
                    }
                    else if (Common_Services.isSemesterFuture(task.getResult().getTimestamp("startDate"))){
                        vmMainAdmin.onIsSemesterActiveOrFutureResulted(true);
                    }
                    else{
                        vmMainAdmin.onIsSemesterActiveOrFutureResulted(false);
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
            semesterDepartmentsRef.whereEqualTo("semesterId",semester).orderBy("deptName", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> departmentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentList.add(new String[]{document.getString("deptName"),document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetDepartmentListResulted(departmentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.get method.");
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
            semesterDeptAdminsRef.whereEqualTo("semesterId",semester).orderBy("deptAdminId", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> deptAdminList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String deptAdminId = document.get("deptAdminId").toString();
                        deptAdminList.add(new String[]{deptAdminId,deptAdminsInfo.get(deptAdminId)[0],
                                deptAdminsInfo.get(deptAdminId)[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetDeptAdminListResulted(deptAdminList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDeptAdminsRef.get method.");
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
            semesterLecturersRef.whereEqualTo("semesterId",semester).orderBy("lecturerId", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> lecturerList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String lecturerId = document.get("lecturerId").toString();
                        lecturerList.add(new String[]{lecturerId,lecturersInfo.get(lecturerId)[0],
                                lecturersInfo.get(lecturerId)[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetLecturerListResulted(lecturerList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.get method.");
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
            semesterStudentsRef.whereEqualTo("semesterId",semester).orderBy("studentId", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> studentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String studentId = document.get("studentId").toString();
                        studentList.add(new String[]{studentId,studentsInfo.get(studentId)[0],
                                studentsInfo.get(studentId)[1],document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetStudentListResulted(studentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.get method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getStudentList method.");
        }
    }

    public void getFilteredDepartmentList(DepartmentFilterType departmentFilter){
        try {
            String semester = Common_Services.convertUnprocessedSemester(departmentFilter.getSemester());
            String deptNameFilter = Common_Services.convertUnprocessedName(departmentFilter.getDeptNameFilter());
            semesterDepartmentsRef.whereEqualTo("semesterId",semester).whereGreaterThanOrEqualTo("deptName", deptNameFilter)
                    .whereLessThanOrEqualTo("deptName", deptNameFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredDepartmentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        filteredDepartmentList.add(new String[]{document.getString("deptName"),document.getString("deptId").toUpperCase()});
                    }
                    vmMainAdmin.onGetDepartmentListResulted(filteredDepartmentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.get method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredDepartmentList method.");
        }
    }

    public void getFilteredDeptAdminList(PersonFilterType personFilter){
        try {
            if(personFilter.getIdFilter().length() == 0){
                personFilter.setIdFilter("1"); // deptAdmin ids always start with 1.
            }
            String idFilter = personFilter.getIdFilter();
            String semester = Common_Services.convertUnprocessedSemester(personFilter.getSemester());
            String nameFilter = Common_Services.convertUnprocessedName(personFilter.getNameFilter());
            String surnameFilter = Common_Services.convertUnprocessedName(personFilter.getSurnameFilter());
            ArrayList<String> deptFilter = personFilter.getDeptFilter();
            semesterDeptAdminsRef.whereEqualTo("semesterId",semester).whereGreaterThanOrEqualTo("deptAdminId",idFilter)
                    .whereLessThanOrEqualTo("deptAdminId", idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredDeptAdminList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String deptAdminId = document.get("deptAdminId").toString();
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId").toUpperCase()).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!deptAdminsInfo.get(deptAdminId)[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!deptAdminsInfo.get(deptAdminId)[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredDeptAdminList.add(new String[]{deptAdminId,deptAdminsInfo.get(deptAdminId)[0],
                                    deptAdminsInfo.get(deptAdminId)[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetDeptAdminListResulted(filteredDeptAdminList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDeptAdminsRef.get method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredDeptAdminList method.");
        }
    }

    public void getFilteredLecturerList(PersonFilterType personFilter){
        try {
            if(personFilter.getIdFilter().length() == 0){
                personFilter.setIdFilter("2"); // lecturer ids always start with 2.
            }
            String idFilter = personFilter.getIdFilter();
            String semester = Common_Services.convertUnprocessedSemester(personFilter.getSemester());
            String nameFilter = Common_Services.convertUnprocessedName(personFilter.getNameFilter());
            String surnameFilter = Common_Services.convertUnprocessedName(personFilter.getSurnameFilter());
            ArrayList<String> deptFilter = personFilter.getDeptFilter();
            semesterLecturersRef.whereEqualTo("semesterId",semester).whereGreaterThanOrEqualTo("lecturerId",idFilter)
                    .whereLessThanOrEqualTo("lecturerId", idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredLecturerList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String lecturerId = document.get("lecturerId").toString();
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId").toUpperCase()).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!lecturersInfo.get(lecturerId)[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!lecturersInfo.get(lecturerId)[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredLecturerList.add(new String[]{lecturerId,lecturersInfo.get(lecturerId)[0],
                                    lecturersInfo.get(lecturerId)[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetLecturerListResulted(filteredLecturerList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.get method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredLecturerList method.");
        }
    }

    public void getFilteredStudentList(PersonFilterType personFilter){
        try {
            if(personFilter.getIdFilter().length() == 0){
                personFilter.setIdFilter("3"); // student ids always start with 3.
            }
            String idFilter = personFilter.getIdFilter();
            String semester = Common_Services.convertUnprocessedSemester(personFilter.getSemester());
            String nameFilter = Common_Services.convertUnprocessedName(personFilter.getNameFilter());
            String surnameFilter = Common_Services.convertUnprocessedName(personFilter.getSurnameFilter());
            ArrayList<String> deptFilter = personFilter.getDeptFilter();
            semesterStudentsRef.whereEqualTo("semesterId",semester).whereGreaterThanOrEqualTo("studentId",idFilter)
                    .whereLessThanOrEqualTo("studentId", idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredStudentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String studentId = document.get("studentId").toString();
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId").toUpperCase()).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!studentsInfo.get(studentId)[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!studentsInfo.get(studentId)[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredStudentList.add(new String[]{studentId,studentsInfo.get(studentId)[0],
                                    studentsInfo.get(studentId)[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetStudentListResulted(filteredStudentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.get method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredStudentList method.");
        }
    }

    public void addDepartment(DepartmentAddOrEditType department){
        try{
            String deptName = Common_Services.convertUnprocessedName(department.getDeptName());
            String deptId = department.getDeptId().toLowerCase();
            String semester = Common_Services.convertUnprocessedSemester(department.getSemester());
            for (String key : departmentsInfo.keySet()) {
                if(deptId.toUpperCase().equals(key)){
                    vmMainAdmin.onAddDepartmentResultedDuplicatedId();
                    return;
                }
                if(deptName.equals(departmentsInfo.get(key))){
                    vmMainAdmin.onAddDepartmentResultedDuplicatedName();
                    return;
                }
            }
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",deptName);
            departmentsRef.document(deptId).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    departmentsInfo.put(deptId.toUpperCase(),deptName);
                    vmMainAdmin.onAddDepartmentResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departmentsRef.document(deptId).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    WriteBatch batchAddDepartment = dbRef.batch();
                    Map<String, Object> departmentData = new HashMap<>();
                    for(int i=0;i<semesterList.size();i++){
                        departmentData.put("semesterId",semesterList.get(i));
                        departmentData.put("deptId",deptId);
                        departmentData.put("deptName",deptName);
                        DocumentReference doc = semesterDepartmentsRef.document();
                        batchAddDepartment.set(doc,departmentData);
                    }
                    batchAddDepartment.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDepartment method.");
        }
    }

    public void addDeptAdmin(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            for (String key : deptAdminsInfo.keySet() ) {
                if(id.equals(key)){
                    vmMainAdmin.onAddDeptAdminResultedDuplicatedId();
                    return;
                }
            }
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            deptAdminsRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    deptAdminsInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onAddDeptAdminResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' deptAdminsRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    WriteBatch batchAddDeptAdmin = dbRef.batch();
                    Map<String, Object> deptAdminData = new HashMap<>();
                    for(int i=0;i<semesterList.size();i++){
                        deptAdminData.put("semesterId",semesterList.get(i));
                        deptAdminData.put("deptAdminId",id);
                        deptAdminData.put("deptId",deptId);
                        DocumentReference doc = semesterDeptAdminsRef.document();
                        batchAddDeptAdmin.set(doc,deptAdminData);
                    }
                    batchAddDeptAdmin.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDeptAdmin method.");
        }
    }

    public void addLecturer(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            for (String key : lecturersInfo.keySet() ) {
                if(id.equals(key)){
                    vmMainAdmin.onAddLecturerResultedDuplicatedId();
                    return;
                }
            }
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            lecturersRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    lecturersInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onAddLecturerResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' lecturersRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    WriteBatch batchAddLecturer = dbRef.batch();
                    Map<String, Object> lecturerData = new HashMap<>();
                    for(int i=0;i<semesterList.size();i++){
                        lecturerData.put("semesterId",semesterList.get(i));
                        lecturerData.put("lecturerId",id);
                        lecturerData.put("deptId",deptId);
                        DocumentReference doc = semesterLecturersRef.document();
                        batchAddLecturer.set(doc,lecturerData);
                    }
                    batchAddLecturer.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addLecturer method.");
        }
    }

    public void addStudent(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            for (String key : studentsInfo.keySet() ) {
                if(id.equals(key)){
                    vmMainAdmin.onAddStudentResultedDuplicatedId();
                    return;
                }
            }
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            studentsRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    studentsInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onAddStudentResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' studentsRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    WriteBatch batchAddStudent = dbRef.batch();
                    Map<String, Object> studentData = new HashMap<>();
                    for(int i=0;i<semesterList.size();i++){
                        studentData.put("semesterId",semesterList.get(i));
                        studentData.put("studentId",id);
                        studentData.put("deptId",deptId);
                        DocumentReference doc = semesterStudentsRef.document();
                        batchAddStudent.set(doc,studentData);
                    }
                    batchAddStudent.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addStudent method.");
        }
    }

    public void addSemester(String startDate, String endDate){
        try{
            Timestamp timestampStart = Common_Services.convertDateStringToTimestamp(startDate);
            Timestamp timestampEnd = Common_Services.convertDateStringToTimestamp(endDate);
            if(timestampStart.compareTo(timestampEnd) >= 0){
                vmMainAdmin.onAddSemesterResultedReverseOrder();
                return;
            }
            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() < (86400 * Parameters_and_Constants.lowDateDiffLimit)){
                vmMainAdmin.onAddSemesterResultedLowDateDifference();
                return;
            }
            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() > (86400 * Parameters_and_Constants.highDateDiffLimit)){
                vmMainAdmin.onAddSemesterResultedHighDateDifference();
                return;
            }
            String semesterName = Common_Services.specifySemesterName(startDate,endDate);
            List<Object> semesterWeeksData = new ArrayList<>();
            Timestamp timestamp = timestampStart;
            Timestamp timestampWeekStart;
            Timestamp timestampWeekEnd;
            int weekIndex = 0;
            Calendar c = Calendar.getInstance();
            while(timestamp.compareTo(timestampEnd) < 0){
                Map<String,Object> object = new HashMap<>();
                timestampWeekStart = timestamp;
                c.setTimeInMillis(timestamp.getSeconds()*1000);
                c.add(Calendar.DATE,7);
                timestamp = new Timestamp(c.getTime());
                timestampWeekEnd = timestamp;
                weekIndex++;
                object.put("startDate",timestampWeekStart);
                object.put("endDate",timestampWeekEnd);
                object.put("weekNumber",weekIndex);
                semesterWeeksData.add(object);
            }
            Semester semester = new Semester(timestampStart,timestampEnd,semesterWeeksData);
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    String dummyActiveSemester = "";
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            dummyActiveSemester = document.getId();
                            break;
                        }
                    }
                    if(dummyActiveSemester.length() != 0){
                        final String activeSemester = dummyActiveSemester;
                        semestersRef.document(semesterName).set(semester).addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
                                    vmMainAdmin.dataLoadError();
                                }
                                else{
                                    vmMainAdmin.onAddSemesterResultedSuccessful();
                                    addDepartmentsForNewSemester(semesterName,activeSemester);
                                    addDeptAdminsForNewSemester(semesterName,activeSemester);
                                    addLecturersForNewSemester(semesterName,activeSemester);
                                    addStudentsForNewSemester(semesterName,activeSemester);
                                }
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.document(semesterName).set(semester).addOnCompleteListener method.");
                            }
                        });
                    }
                    else{
                        Log.d("Exception","ACTIVE SEMESTER LENGTH == 0 on Model_Main_Admin class!!!");
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addSemester method.");
        }
    }

    private void addDepartmentsForNewSemester(String newSemester, String activeSemester){
        try{
            semesterDepartmentsRef.whereEqualTo("semesterId",activeSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    WriteBatch batchAddDepartment = dbRef.batch();
                    Map<String, Object> departmentData = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        departmentData.put("semesterId",newSemester);
                        departmentData.put("deptId",document.getString("deptId"));
                        departmentData.put("deptName",document.getString("deptName"));
                        DocumentReference doc = semesterDepartmentsRef.document();
                        batchAddDepartment.set(doc,departmentData);
                    }
                    batchAddDepartment.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDepartmentsForNewSemester method.");
        }
    }

    private void addDeptAdminsForNewSemester(String newSemester, String activeSemester){
        try{
            semesterDeptAdminsRef.whereEqualTo("semesterId",activeSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    WriteBatch batchAddDeptAdmin = dbRef.batch();
                    Map<String, Object> deptAdminData = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        deptAdminData.put("semesterId",newSemester);
                        deptAdminData.put("deptAdminId",document.getString("deptAdminId"));
                        deptAdminData.put("deptId",document.getString("deptId"));
                        DocumentReference doc = semesterDeptAdminsRef.document();
                        batchAddDeptAdmin.set(doc,deptAdminData);
                    }
                    batchAddDeptAdmin.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDeptAdminsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDeptAdminsForNewSemester method.");
        }
    }

    private void addLecturersForNewSemester(String newSemester, String activeSemester){
        try{
            semesterLecturersRef.whereEqualTo("semesterId",activeSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    WriteBatch batchAddLecturer = dbRef.batch();
                    Map<String, Object> lecturerData = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lecturerData.put("semesterId",newSemester);
                        lecturerData.put("lecturerId",document.getString("lecturerId"));
                        lecturerData.put("deptId",document.getString("deptId"));
                        DocumentReference doc = semesterLecturersRef.document();
                        batchAddLecturer.set(doc,lecturerData);
                    }
                    batchAddLecturer.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addLecturersForNewSemester method.");
        }
    }

    private void addStudentsForNewSemester(String newSemester, String activeSemester){
        try{
            semesterStudentsRef.whereEqualTo("semesterId",activeSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    WriteBatch batchAddStudent = dbRef.batch();
                    Map<String, Object> studentData = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        studentData.put("semesterId",newSemester);
                        studentData.put("studentId",document.getString("studentId"));
                        studentData.put("deptId",document.getString("deptId"));
                        DocumentReference doc = semesterStudentsRef.document();
                        batchAddStudent.set(doc,studentData);
                    }
                    batchAddStudent.commit();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addStudentsForNewSemester method.");
        }
    }

    public void editDepartment(DepartmentAddOrEditType department){
        try{
            String deptName = Common_Services.convertUnprocessedName(department.getDeptName());
            String deptId = department.getDeptId().toLowerCase();
            String semester = Common_Services.convertUnprocessedSemester(department.getSemester());
            for (String key : departmentsInfo.keySet() ) {
                if(!deptId.toUpperCase().equals(key) && deptName.equals(departmentsInfo.get(key))){
                    vmMainAdmin.onEditDepartmentResultedDuplicatedName();
                    return;
                }
            }
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",deptName);
            departmentsRef.document(deptId).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    departmentsInfo.put(deptId.toUpperCase(),deptName);
                    vmMainAdmin.onEditDepartmentResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departmentsRef.document(deptId).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    semesterDepartmentsRef.whereEqualTo("deptId",deptId).get().addOnCompleteListener(task1 -> {
                        try{
                            WriteBatch batchEditDepartmentToSemesterDepartments = dbRef.batch();
                            Map<String, Object> departmentData = new HashMap<>();
                            for (QueryDocumentSnapshot document : task1.getResult()) {
                                if(semesterList.contains(document.getString("semesterId"))){
                                    departmentData.put("semesterId",document.getString("semesterId"));
                                    departmentData.put("deptId",deptId);
                                    departmentData.put("deptName",deptName);
                                    batchEditDepartmentToSemesterDepartments.set(document.getReference(),departmentData);
                                }
                            }
                            batchEditDepartmentToSemesterDepartments.commit();
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.where.get.addOnCompleteListener method.");
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' editDepartment method.");
        }
    }

    public void editDeptAdmin(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            deptAdminsRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    deptAdminsInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onEditDeptAdminResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' deptAdminsRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    semesterDeptAdminsRef.whereEqualTo("deptAdminId",id).get().addOnCompleteListener(task1 -> {
                        try{
                            WriteBatch batchEditDeptAdmin = dbRef.batch();
                            Map<String, Object> deptAdminData = new HashMap<>();
                            for (QueryDocumentSnapshot document : task1.getResult()) {
                                if(semesterList.contains(document.getString("semesterId"))){
                                    deptAdminData.put("semesterId",document.getString("semesterId"));
                                    deptAdminData.put("deptAdminId",id);
                                    deptAdminData.put("deptId",deptId);
                                    batchEditDeptAdmin.set(document.getReference(),deptAdminData);
                                }
                            }
                            batchEditDeptAdmin.commit();
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.where.get.addOnCompleteListener method.");
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' editDeptAdmin method.");
        }
    }

    public void editLecturer(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            lecturersRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    lecturersInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onEditLecturerResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' lecturersRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    semesterLecturersRef.whereEqualTo("lecturerId",id).get().addOnCompleteListener(task1 -> {
                        try{
                            WriteBatch batchEditLecturer = dbRef.batch();
                            Map<String, Object> lecturerData = new HashMap<>();
                            for (QueryDocumentSnapshot document : task1.getResult()) {
                                if(semesterList.contains(document.getString("semesterId"))){
                                    lecturerData.put("semesterId",document.getString("semesterId"));
                                    lecturerData.put("lecturerId",id);
                                    lecturerData.put("deptId",deptId);
                                    batchEditLecturer.set(document.getReference(),lecturerData);
                                }
                            }
                            batchEditLecturer.commit();
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.where.get.addOnCompleteListener method.");
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' editLecturer method.");
        }
    }

    public void editStudent(PersonAddOrEditType person){
        try{
            String id = person.getId();
            String name = Common_Services.convertUnprocessedName(person.getName());
            String surname = Common_Services.convertUnprocessedName(person.getSurname());
            String deptName = person.getDeptName();
            String semester = Common_Services.convertUnprocessedSemester(person.getSemester());
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            Map<String, Object> docData = new HashMap<>();
            docData.put("name",name);
            docData.put("surname",surname);
            studentsRef.document(id).set(docData).addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    studentsInfo.put(id,new String[]{name,surname});
                    vmMainAdmin.onEditStudentResultedSuccessful();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' studentsRef.document(id).set(docData).addOnCompleteListener method.");
                }
            });
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    HashMap<String,Timestamp> startDateMap = new HashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                    }
                    ArrayList<String> semesterList = new ArrayList<>();
                    for (String key : startDateMap.keySet() ) {
                        if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                            semesterList.add(key);
                        }
                    }
                    semesterStudentsRef.whereEqualTo("studentId",id).get().addOnCompleteListener(task1 -> {
                        try{
                            WriteBatch batchEditStudent = dbRef.batch();
                            Map<String, Object> studentData = new HashMap<>();
                            for (QueryDocumentSnapshot document : task1.getResult()) {
                                if(semesterList.contains(document.getString("semesterId"))){
                                    studentData.put("semesterId",document.getString("semesterId"));
                                    studentData.put("studentId",id);
                                    studentData.put("deptId",deptId);
                                    batchEditStudent.set(document.getReference(),studentData);
                                }
                            }
                            batchEditStudent.commit();
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.where.get.addOnCompleteListener method.");
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' editStudent method.");
        }
    }

    public void editSemester(String startDate, String endDate){
        try{
            Timestamp timestampStart = Common_Services.convertDateStringToTimestamp(startDate);
            Timestamp timestampEnd = Common_Services.convertDateStringToTimestamp(endDate);
            if(timestampStart.compareTo(timestampEnd) >= 0){
                vmMainAdmin.onEditSemesterResultedReverseOrder();
                return;
            }
            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() < (86400 * 60)){
                vmMainAdmin.onEditSemesterResultedLowDateDifference();
                return;
            }
            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() > (86400 * 150)){
                vmMainAdmin.onEditSemesterResultedHighDateDifference();
                return;
            }
            String semesterName = Common_Services.specifySemesterName(startDate,endDate);
            List<Object> semesterWeeksData = new ArrayList<>();
            Timestamp timestamp = timestampStart;
            Timestamp timestampWeekStart;
            Timestamp timestampWeekEnd;
            int weekIndex = 0;
            Calendar c = Calendar.getInstance();
            while(timestamp.compareTo(timestampEnd) < 0){
                Map<String,Object> object = new HashMap<>();
                timestampWeekStart = timestamp;
                c.setTimeInMillis(timestamp.getSeconds()*1000);
                c.add(Calendar.DATE,7);
                timestamp = new Timestamp(c.getTime());
                timestampWeekEnd = timestamp;
                weekIndex++;
                object.put("startDate",timestampWeekStart);
                object.put("endDate",timestampWeekEnd);
                object.put("weekNumber",weekIndex);
                semesterWeeksData.add(object);
            }
            Semester semester = new Semester(timestampStart,timestampEnd,semesterWeeksData);
            semestersRef.document(semesterName).set(semester).addOnCompleteListener(task1 -> {
                try{
                    if(!task1.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        vmMainAdmin.onEditSemesterResultedSuccessful();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.document(semesterName).set(semester).addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' editSemester method.");
        }
    }

    public void deleteDepartments(String unprocessedSemester, ArrayList<String> idList){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        HashMap<String,Timestamp> startDateMap = new HashMap<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                        }
                        ArrayList<String> semesterList = new ArrayList<>();
                        for (String key : startDateMap.keySet() ) {
                            if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                                semesterList.add(key);
                            }
                        }
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                semesterDepartmentsRef.whereEqualTo("deptId",idList.get(j).toLowerCase()).get().addOnCompleteListener(task1 -> {
                                    try{
                                        WriteBatch batchDeleteDepartments = dbRef.batch();
                                        for (QueryDocumentSnapshot document : task1.getResult()) {
                                            if(semesterList.contains(document.getString("semesterId"))){
                                                batchDeleteDepartments.delete(document.getReference());
                                            }
                                        }
                                        batchDeleteDepartments.commit().addOnCompleteListener(task2 -> {
                                            try{
                                                if(!task2.isSuccessful()){
                                                    vmMainAdmin.dataLoadError();
                                                }
                                                else{
                                                    vmMainAdmin.onDeleteDepartmentsResultedSuccessful();
                                                }
                                            }
                                            catch (Exception e){
                                                Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteDepartments.commit().addOnCompleteListener method.");
                                            }
                                        });
                                    }
                                    catch (Exception e){
                                        Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.where.get.addOnCompleteListener method.");
                                    }
                                });
                            }
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteDepartments method.");
        }
    }

    public void deleteDeptAdmins(String unprocessedSemester, ArrayList<String> idList){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        HashMap<String,Timestamp> startDateMap = new HashMap<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                        }
                        ArrayList<String> semesterList = new ArrayList<>();
                        for (String key : startDateMap.keySet() ) {
                            if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                                semesterList.add(key);
                            }
                        }
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                semesterDeptAdminsRef.whereEqualTo("deptAdminId",idList.get(j)).get().addOnCompleteListener(task1 -> {
                                    try{
                                        WriteBatch batchDeleteDeptAdmins = dbRef.batch();
                                        for (QueryDocumentSnapshot document : task1.getResult()) {
                                            if(semesterList.contains(document.getString("semesterId"))){
                                                batchDeleteDeptAdmins.delete(document.getReference());
                                            }
                                        }
                                        batchDeleteDeptAdmins.commit().addOnCompleteListener(task2 -> {
                                            try{
                                                if(!task2.isSuccessful()){
                                                    vmMainAdmin.dataLoadError();
                                                }
                                                else{
                                                    vmMainAdmin.onDeleteDeptAdminsResultedSuccessful();
                                                }
                                            }
                                            catch (Exception e){
                                                Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteDeptAdmins.commit().addOnCompleteListener method.");
                                            }
                                        });
                                    }
                                    catch (Exception e){
                                        Log.d("Exception", "Exception on Model_Main_Admin class' semesterDeptAdminsRef.where.get.addOnCompleteListener method.");
                                    }
                                });
                            }
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteDeptAdmins method.");
        }
    }

    public void deleteLecturers(String unprocessedSemester, ArrayList<String> idList){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        HashMap<String,Timestamp> startDateMap = new HashMap<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                        }
                        ArrayList<String> semesterList = new ArrayList<>();
                        for (String key : startDateMap.keySet() ) {
                            if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                                semesterList.add(key);
                            }
                        }
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                semesterLecturersRef.whereEqualTo("lecturerId",idList.get(j)).get().addOnCompleteListener(task1 -> {
                                    try{
                                        WriteBatch batchDeleteLecturers = dbRef.batch();
                                        for (QueryDocumentSnapshot document : task1.getResult()) {
                                            if(semesterList.contains(document.getString("semesterId"))){
                                                batchDeleteLecturers.delete(document.getReference());
                                            }
                                        }
                                        batchDeleteLecturers.commit().addOnCompleteListener(task2 -> {
                                            try{
                                                if(!task2.isSuccessful()){
                                                    vmMainAdmin.dataLoadError();
                                                }
                                                else{
                                                    vmMainAdmin.onDeleteLecturersResultedSuccessful();
                                                }
                                            }
                                            catch (Exception e){
                                                Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteLecturers.commit().addOnCompleteListener method.");
                                            }
                                        });
                                    }
                                    catch (Exception e){
                                        Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.where.get.addOnCompleteListener method.");
                                    }
                                });
                            }
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteLecturers method.");
        }
    }

    public void deleteStudents(String unprocessedSemester, ArrayList<String> idList){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            semestersRef.get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        HashMap<String,Timestamp> startDateMap = new HashMap<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            startDateMap.put(document.getId(),document.getTimestamp("startDate"));
                        }
                        ArrayList<String> semesterList = new ArrayList<>();
                        for (String key : startDateMap.keySet() ) {
                            if(startDateMap.get(key).compareTo(startDateMap.get(semester)) >= 0){
                                semesterList.add(key);
                            }
                        }
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                semesterStudentsRef.whereEqualTo("studentId",idList.get(j)).get().addOnCompleteListener(task1 -> {
                                    try{
                                        WriteBatch batchDeleteStudents = dbRef.batch();
                                        for (QueryDocumentSnapshot document : task1.getResult()) {
                                            if(semesterList.contains(document.getString("semesterId"))){
                                                batchDeleteStudents.delete(document.getReference());
                                            }
                                        }
                                        batchDeleteStudents.commit().addOnCompleteListener(task2 -> {
                                            try{
                                                if(!task2.isSuccessful()){
                                                    vmMainAdmin.dataLoadError();
                                                }
                                                else{
                                                    vmMainAdmin.onDeleteStudentsResultedSuccessful();
                                                }
                                            }
                                            catch (Exception e){
                                                Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteStudents.commit().addOnCompleteListener method.");
                                            }
                                        });
                                    }
                                    catch (Exception e){
                                        Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.where.get.addOnCompleteListener method.");
                                    }
                                });
                            }
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteStudents method.");
        }
    }

    public void deleteSemester(String unprocessedSemester){
        try{
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            semestersRef.document(semester).delete().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        vmMainAdmin.onDeleteSemesterResultedSuccessful();
                        deleteDepartmentsForDeletedSemester(semester);
                        deleteDeptAdminsForDeletedSemester(semester);
                        deleteLecturersForDeletedSemester(semester);
                        deleteStudentsForDeletedSemester(semester);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.document(semester).delete().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteSemester method.");
        }
    }

    private void deleteDepartmentsForDeletedSemester(String deletedSemester){
        try{
            semesterDepartmentsRef.whereEqualTo("semesterId",deletedSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        WriteBatch batchDeleteDepartments = dbRef.batch();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            batchDeleteDepartments.delete(document.getReference());
                        }
                        batchDeleteDepartments.commit();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDepartmentsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteDepartmentsForDeletedSemester method.");
        }
    }

    private void deleteDeptAdminsForDeletedSemester(String deletedSemester){
        try{
            semesterDeptAdminsRef.whereEqualTo("semesterId",deletedSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        WriteBatch batchDeleteDeptAdmins = dbRef.batch();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            batchDeleteDeptAdmins.delete(document.getReference());
                        }
                        batchDeleteDeptAdmins.commit();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterDeptAdminsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteDeptAdminsForDeletedSemester method.");
        }
    }

    private void deleteLecturersForDeletedSemester(String deletedSemester){
        try{
            semesterLecturersRef.whereEqualTo("semesterId",deletedSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        WriteBatch batchDeleteLecturers = dbRef.batch();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            batchDeleteLecturers.delete(document.getReference());
                        }
                        batchDeleteLecturers.commit();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterLecturersRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteLecturersForDeletedSemester method.");
        }
    }

    private void deleteStudentsForDeletedSemester(String deletedSemester){
        try{
            semesterStudentsRef.whereEqualTo("semesterId",deletedSemester).get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        WriteBatch batchDeleteStudents = dbRef.batch();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            batchDeleteStudents.delete(document.getReference());
                        }
                        batchDeleteStudents.commit();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' semesterStudentsRef.whereEqualTo.get.addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteStudentsForDeletedSemester method.");
        }
    }

    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}