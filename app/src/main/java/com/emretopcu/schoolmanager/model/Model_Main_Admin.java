package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    public void getFilteredDepartmentList(String unprocessedSemester, String unprocessedDeptNameFilter){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String deptNameFilter = Common_Services.convertUnprocessedName(unprocessedDeptNameFilter);
            CollectionReference departments = semesterConditionsRef.document(semester).collection("departments");
            departments.whereGreaterThanOrEqualTo("name", deptNameFilter)
                    .whereLessThanOrEqualTo("name", deptNameFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredDepartmentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        filteredDepartmentList.add(new String[]{document.getString("name"),document.getId().toUpperCase()});
                    }
                    vmMainAdmin.onGetDepartmentListResulted(filteredDepartmentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' departments.FILTER.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredDepartmentList method.");
        }
    }

    public void getFilteredDeptAdminList(String unprocessedSemester, String idFilter, String unprocessedNameFilter,
                                         String unprocessedSurnameFilter, ArrayList<String> deptFilter){
        try {
            if(idFilter.length() == 0){
                idFilter = "1"; // deptAdmin ids always start with 1.
            }
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String nameFilter = Common_Services.convertUnprocessedName(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedName(unprocessedSurnameFilter);
            CollectionReference deptAdmins = semesterConditionsRef.document(semester).collection("deptAdmins");
            deptAdmins.whereGreaterThanOrEqualTo(FieldPath.documentId(),idFilter)
                    .whereLessThanOrEqualTo(FieldPath.documentId(), idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredDeptAdminList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId")).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!deptAdminsInfo.get(document.getId())[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!deptAdminsInfo.get(document.getId())[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredDeptAdminList.add(new String[]{document.getId(),deptAdminsInfo.get(document.getId())[0],
                                    deptAdminsInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetDeptAdminListResulted(filteredDeptAdminList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' deptAdmins.FILTER.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredDeptAdminList method.");
        }
    }

    public void getFilteredLecturerList(String unprocessedSemester, String idFilter, String unprocessedNameFilter,
                                         String unprocessedSurnameFilter, ArrayList<String> deptFilter){
        try {
            if(idFilter.length() == 0){
                idFilter = "2"; // lecturer ids always start with 2.
            }
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String nameFilter = Common_Services.convertUnprocessedName(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedName(unprocessedSurnameFilter);
            CollectionReference lecturers = semesterConditionsRef.document(semester).collection("lecturers");
            lecturers.whereGreaterThanOrEqualTo(FieldPath.documentId(),idFilter)
                    .whereLessThanOrEqualTo(FieldPath.documentId(), idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredLecturerList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId")).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!lecturersInfo.get(document.getId())[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!lecturersInfo.get(document.getId())[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredLecturerList.add(new String[]{document.getId(),lecturersInfo.get(document.getId())[0],
                                    lecturersInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetLecturerListResulted(filteredLecturerList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' lecturers.FILTER.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredLecturerList method.");
        }
    }

    public void getFilteredStudentList(String unprocessedSemester, String idFilter, String unprocessedNameFilter,
                                         String unprocessedSurnameFilter, ArrayList<String> deptFilter){
        try {
            if(idFilter.length() == 0){
                idFilter = "3"; // student ids always start with 3.
            }
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String nameFilter = Common_Services.convertUnprocessedName(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedName(unprocessedSurnameFilter);
            CollectionReference students = semesterConditionsRef.document(semester).collection("students");
            students.whereGreaterThanOrEqualTo(FieldPath.documentId(),idFilter)
                    .whereLessThanOrEqualTo(FieldPath.documentId(), idFilter + '\uf8ff').get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    ArrayList<String[]> filteredStudentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        boolean isExist = true;
                        if(deptFilter.size() > 0){
                            isExist = false;
                            for(int i=0;i<deptFilter.size();i++){
                                if(departmentsInfo.get(document.getString("deptId")).equals(deptFilter.get(i))){
                                    isExist = true;
                                    break;
                                }
                            }
                        }
                        if(nameFilter.length() > 0){
                            if(!studentsInfo.get(document.getId())[0].startsWith(nameFilter)){
                                isExist = false;
                            }
                        }
                        if(surnameFilter.length() > 0){
                            if(!studentsInfo.get(document.getId())[1].startsWith(surnameFilter)){
                                isExist = false;
                            }
                        }
                        if(isExist){
                            filteredStudentList.add(new String[]{document.getId(),studentsInfo.get(document.getId())[0],
                                    studentsInfo.get(document.getId())[1],document.getString("deptId").toUpperCase()});
                        }
                    }
                    vmMainAdmin.onGetStudentListResulted(filteredStudentList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' students.FILTER.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getFilteredStudentList method.");
        }
    }
    
    public void addDepartment(String unprocessedDeptName, String unprocessedDeptId, String unprocessedSemester){
        try{
            String deptName = Common_Services.convertUnprocessedName(unprocessedDeptName);
            String deptId = unprocessedDeptId.toLowerCase();
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            for (String key : departmentsInfo.keySet() ) {
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
                    WriteBatch batchAddDepartmentToSemesterConditions = dbRef.batch();
                    Map<String, Object> departmentData = new HashMap<>();
                    departmentData.put("name",deptName);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("departments").document(deptId);
                        batchAddDepartmentToSemesterConditions.set(doc,departmentData);
                    }
                    batchAddDepartmentToSemesterConditions.commit();
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

    public void addDeptAdmin(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchAddDeptAdminToSemesterConditions = dbRef.batch();
                    Map<String, Object> deptAdminData = new HashMap<>();
                    deptAdminData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("deptAdmins").document(id);
                        batchAddDeptAdminToSemesterConditions.set(doc,deptAdminData);
                    }
                    batchAddDeptAdminToSemesterConditions.commit();
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

    public void addLecturer(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchAddLecturerToSemesterConditions = dbRef.batch();
                    Map<String, Object> lecturerData = new HashMap<>();
                    lecturerData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("lecturers").document(id);
                        batchAddLecturerToSemesterConditions.set(doc,lecturerData);
                    }
                    batchAddLecturerToSemesterConditions.commit();
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

    public void addStudent(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchAddStudentToSemesterConditions = dbRef.batch();
                    Map<String, Object> studentData = new HashMap<>();
                    studentData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("students").document(id);
                        batchAddStudentToSemesterConditions.set(doc,studentData);
                    }
                    batchAddStudentToSemesterConditions.commit();
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
            WriteBatch batchAddSemester = dbRef.batch();
            Map<String, Object> semesterData = new HashMap<>();
            semesterData.put("startDate",timestampStart);
            semesterData.put("endDate",timestampEnd);
            DocumentReference specificSemester = semestersRef.document(semesterName);
            batchAddSemester.set(specificSemester,semesterData);
            Map<String, Object> semesterWeeksData = new HashMap<>();
            Timestamp timestamp = timestampStart;
            Calendar c = Calendar.getInstance();
            int weekIndex = 1;
            while(timestamp.compareTo(timestampEnd) < 0){
                semesterWeeksData.put("startDate",timestamp);
                c.setTimeInMillis(timestamp.getSeconds()*1000);
                c.add(Calendar.DATE,7);
                timestamp = new Timestamp(c.getTime());
                semesterWeeksData.put("endDate",timestamp);
                DocumentReference specificSemesterWeek = specificSemester.collection("weeks").document("week" + weekIndex);
                batchAddSemester.set(specificSemesterWeek,semesterWeeksData);
                weekIndex++;
            }
            batchAddSemester.commit().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        vmMainAdmin.onAddSemesterResultedSuccessful();
                        addDepartmentsForNewSemester(semesterName);
                        addDeptAdminsForNewSemester(semesterName);
                        addLecturersForNewSemester(semesterName);
                        addStudentsForNewSemester(semesterName);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' batchAddSemester.commit().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addSemester method.");
        }
    }

    private void addDepartmentsForNewSemester(String newSemester){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    String activeSemester = "";
                    if(!task.isSuccessful()){
                        Log.d("Exception","Task is not successful on Model_Main_Admin class' addDepartmentsForNewSemester method.");
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            activeSemester = document.getId();
                            break;
                        }
                    }
                    if(activeSemester.length() != 0){
                        CollectionReference activeSemesterDepartmentsRef = semesterConditionsRef.document(activeSemester).collection("departments");
                        activeSemesterDepartmentsRef.get().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
                                    Log.d("Exception","Task1 is not successful on Model_Main_Admin class' addDepartmentsForNewSemester method.");
                                    return;
                                }
                                ArrayList<String[]> departmentList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task1.getResult()) {
                                    departmentList.add(new String[]{document.getId(),document.getString("name")});
                                }
                                WriteBatch batchAddDepartments = dbRef.batch();
                                Map<String,Object> deptData = new HashMap<>();
                                CollectionReference newSemesterDepartmentsRef = semesterConditionsRef.document(newSemester).collection("departments");
                                for(int i=0;i<departmentList.size();i++){
                                    DocumentReference entityDeptRef = newSemesterDepartmentsRef.document(departmentList.get(i)[0]);
                                    deptData.put("name",departmentList.get(i)[1]);
                                    batchAddDepartments.set(entityDeptRef,deptData);
                                }
                                batchAddDepartments.commit();
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' activeSemesterDepartmentsRef.get().addOnCompleteListener method.");
                            }
                        });
                    }
                    else{
                        Log.d("Exception","ACTIVE SEMESTER LENGTH = 0 on Model_Main_Admin class!!!");
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDepartmentsForNewSemester method.");
        }
    }

    private void addDeptAdminsForNewSemester(String newSemester){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    String activeSemester = "";
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            activeSemester = document.getId();
                            break;
                        }
                    }
                    if(activeSemester.length() != 0){
                        CollectionReference activeSemesterDeptAdminsRef = semesterConditionsRef.document(activeSemester).collection("deptAdmins");
                        activeSemesterDeptAdminsRef.get().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
                                    Log.d("Exception","Task1 is not successful on Model_Main_Admin class' addDeptAdminsForNewSemester method.");
                                    return;
                                }
                                ArrayList<String[]> deptAdminList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task1.getResult()) {
                                    deptAdminList.add(new String[]{document.getId(),document.getString("deptId")});
                                }
                                WriteBatch batchAddDeptAdmins = dbRef.batch();
                                Map<String,Object> deptAdminData = new HashMap<>();
                                CollectionReference newSemesterDeptAdminsRef = semesterConditionsRef.document(newSemester).collection("deptAdmins");
                                for(int i=0;i<deptAdminList.size();i++){
                                    DocumentReference entityDeptAdminRef = newSemesterDeptAdminsRef.document(deptAdminList.get(i)[0]);
                                    deptAdminData.put("deptId",deptAdminList.get(i)[1]);
                                    batchAddDeptAdmins.set(entityDeptAdminRef,deptAdminData);
                                }
                                batchAddDeptAdmins.commit();
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' activeSemesterDeptAdminsRef.get().addOnCompleteListener method.");
                            }
                        });
                    }
                    else{
                        Log.d("Exception","ACTIVE SEMESTER LENGTH = 0 on Model_Main_Admin class!!!");
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDeptAdminsForNewSemester method.");
        }
    }

    private void addLecturersForNewSemester(String newSemester){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    String activeSemester = "";
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            activeSemester = document.getId();
                            break;
                        }
                    }
                    if(activeSemester.length() != 0){
                        CollectionReference activeSemesterLecturersRef = semesterConditionsRef.document(activeSemester).collection("lecturers");
                        activeSemesterLecturersRef.get().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
                                    Log.d("Exception","Task1 is not successful on Model_Main_Admin class' addLecturersForNewSemester method.");
                                    return;
                                }
                                ArrayList<String[]> lecturerList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task1.getResult()) {
                                    lecturerList.add(new String[]{document.getId(),document.getString("deptId")});
                                }
                                WriteBatch batchAddLecturers = dbRef.batch();
                                Map<String,Object> lecturerData = new HashMap<>();
                                CollectionReference newSemesterLecturersRef = semesterConditionsRef.document(newSemester).collection("lecturers");
                                for(int i=0;i<lecturerList.size();i++){
                                    DocumentReference entityLecturerRef = newSemesterLecturersRef.document(lecturerList.get(i)[0]);
                                    lecturerData.put("deptId",lecturerList.get(i)[1]);
                                    batchAddLecturers.set(entityLecturerRef,lecturerData);
                                }
                                batchAddLecturers.commit();
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' activeSemesterLecturersRef.get().addOnCompleteListener method.");
                            }
                        });
                    }
                    else{
                        Log.d("Exception","ACTIVE SEMESTER LENGTH = 0 on Model_Main_Admin class!!!");
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addLecturersForNewSemester method.");
        }
    }

    private void addStudentsForNewSemester(String newSemester){
        try{
            semestersRef.orderBy("startDate", Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
                try{
                    String activeSemester = "";
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            activeSemester = document.getId();
                            break;
                        }
                    }
                    if(activeSemester.length() != 0){
                        CollectionReference activeSemesterStudentsRef = semesterConditionsRef.document(activeSemester).collection("students");
                        activeSemesterStudentsRef.get().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
                                    Log.d("Exception","Task1 is not successful on Model_Main_Admin class' addStudentsForNewSemester method.");
                                    return;
                                }
                                ArrayList<String[]> studentList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task1.getResult()) {
                                    studentList.add(new String[]{document.getId(),document.getString("deptId")});
                                }
                                WriteBatch batchAddStudents = dbRef.batch();
                                Map<String,Object> studentData = new HashMap<>();
                                CollectionReference newSemesterStudentsRef = semesterConditionsRef.document(newSemester).collection("students");
                                for(int i=0;i<studentList.size();i++){
                                    DocumentReference entityStudentRef = newSemesterStudentsRef.document(studentList.get(i)[0]);
                                    studentData.put("deptId",studentList.get(i)[1]);
                                    batchAddStudents.set(entityStudentRef,studentData);
                                }
                                batchAddStudents.commit();
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' activeSemesterStudentsRef.get().addOnCompleteListener method.");
                            }
                        });
                    }
                    else{
                        Log.d("Exception","ACTIVE SEMESTER LENGTH = 0 on Model_Main_Admin class!!!");
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Model_Main_Admin class' semestersRef.get().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addStudentsForNewSemester method.");
        }
    }

    public void editDepartment(String unprocessedDeptName, String unprocessedDeptId, String unprocessedSemester){
        try{
            String deptName = Common_Services.convertUnprocessedName(unprocessedDeptName);
            String deptId = unprocessedDeptId.toLowerCase();
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchEditDepartmentToSemesterConditions = dbRef.batch();
                    Map<String, Object> departmentData = new HashMap<>();
                    departmentData.put("name",deptName);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("departments").document(deptId);
                        batchEditDepartmentToSemesterConditions.set(doc,departmentData);
                    }
                    batchEditDepartmentToSemesterConditions.commit();
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

    public void editDeptAdmin(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchEditDeptAdminToSemesterConditions = dbRef.batch();
                    Map<String, Object> deptAdminData = new HashMap<>();
                    deptAdminData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("deptAdmins").document(id);
                        batchEditDeptAdminToSemesterConditions.set(doc,deptAdminData);
                    }
                    batchEditDeptAdminToSemesterConditions.commit();
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

    public void editLecturer(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchEditLecturerToSemesterConditions = dbRef.batch();
                    Map<String, Object> lecturerData = new HashMap<>();
                    lecturerData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("lecturers").document(id);
                        batchEditLecturerToSemesterConditions.set(doc,lecturerData);
                    }
                    batchEditLecturerToSemesterConditions.commit();
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

    public void editStudent(String id, String unprocessedName, String unprocessedSurname, String deptName, String unprocessedSemester){
        try{
            String name = Common_Services.convertUnprocessedName(unprocessedName);
            String surname = Common_Services.convertUnprocessedName(unprocessedSurname);
            String dummyDeptId = "";
            for (String key : departmentsInfo.keySet() ) {
                if(deptName.equals(departmentsInfo.get(key))){
                    dummyDeptId = key.toLowerCase();
                    break;
                }
            }
            String deptId = dummyDeptId;
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
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
                    WriteBatch batchEditStudentToSemesterConditions = dbRef.batch();
                    Map<String, Object> studentData = new HashMap<>();
                    studentData.put("deptId",deptId);
                    for(int i=0;i<semesterList.size();i++){
                        DocumentReference doc = semesterConditionsRef.document(semesterList.get(i)).collection("students").document(id);
                        batchEditStudentToSemesterConditions.set(doc,studentData);
                    }
                    batchEditStudentToSemesterConditions.commit();
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
            WriteBatch batchEditSemester = dbRef.batch();
            Map<String, Object> semesterData = new HashMap<>();
            semesterData.put("startDate",timestampStart);
            semesterData.put("endDate",timestampEnd);
            DocumentReference specificSemester = semestersRef.document(semesterName);
            specificSemester.collection("weeks").get().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    int totalWeeks = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        totalWeeks++;
                    }
                    WriteBatch batchDeleteSemesterWeeks = dbRef.batch();
                    for(int i=1;i<=totalWeeks;i++){
                        batchDeleteSemesterWeeks.delete(specificSemester.collection("weeks").document("week" + i));
                    }
                    batchDeleteSemesterWeeks.commit().addOnCompleteListener(task1 -> {
                        try{
                            if(!task1.isSuccessful()){
                                vmMainAdmin.dataLoadError();
                            }
                            else{
                                batchEditSemester.set(specificSemester,semesterData);
                                Map<String, Object> semesterWeeksData = new HashMap<>();
                                Timestamp timestamp = timestampStart;
                                Calendar c = Calendar.getInstance();
                                int weekIndex = 1;
                                while(timestamp.compareTo(timestampEnd) < 0){
                                    semesterWeeksData.put("startDate",timestamp);
                                    c.setTimeInMillis(timestamp.getSeconds()*1000);
                                    c.add(Calendar.DATE,7);
                                    timestamp = new Timestamp(c.getTime());
                                    semesterWeeksData.put("endDate",timestamp);
                                    DocumentReference specificSemesterWeek = specificSemester.collection("weeks").document("week" + weekIndex);
                                    batchEditSemester.set(specificSemesterWeek,semesterWeeksData);
                                    weekIndex++;
                                }
                                batchEditSemester.commit().addOnCompleteListener(task2 -> {
                                    try{
                                        if(!task2.isSuccessful()){
                                            vmMainAdmin.dataLoadError();
                                        }
                                        else{
                                            vmMainAdmin.onEditSemesterResultedSuccessful();
                                        }
                                    }
                                    catch (Exception e){
                                        Log.d("Exception", "Exception on Model_Main_Admin class' batchEditSemester.commit().addOnCompleteListener method.");
                                    }
                                });
                            }
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Model_Main_Admin class' batchEditSemester.commit().addOnCompleteListener method.");
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteSemesterWeeks.commit().addOnCompleteListener method.");
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
                        WriteBatch batchDeleteDepartments = dbRef.batch();
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                batchDeleteDepartments.delete(semesterConditionsRef.document(semesterList.get(i)).collection("departments").document(idList.get(j).toLowerCase()));
                            }
                        }
                        batchDeleteDepartments.commit().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
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
                        WriteBatch batchDeleteDeptAdmins = dbRef.batch();
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                batchDeleteDeptAdmins.delete(semesterConditionsRef.document(semesterList.get(i)).collection("deptAdmins").document(idList.get(j)));
                            }
                        }
                        batchDeleteDeptAdmins.commit().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
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
                        WriteBatch batchDeleteLecturers = dbRef.batch();
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                batchDeleteLecturers.delete(semesterConditionsRef.document(semesterList.get(i)).collection("lecturers").document(idList.get(j)));
                            }
                        }
                        batchDeleteLecturers.commit().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
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
                        WriteBatch batchDeleteStudents = dbRef.batch();
                        for(int i=0;i<semesterList.size();i++){
                            for(int j=0;j<idList.size();j++){
                                batchDeleteStudents.delete(semesterConditionsRef.document(semesterList.get(i)).collection("students").document(idList.get(j)));
                            }
                        }
                        batchDeleteStudents.commit().addOnCompleteListener(task1 -> {
                            try{
                                if(!task1.isSuccessful()){
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
            WriteBatch batchDeleteSemester = dbRef.batch();
            batchDeleteSemester.delete(semestersRef.document(semester));
            batchDeleteSemester.delete(semesterConditionsRef.document(semester));
            batchDeleteSemester.commit().addOnCompleteListener(task -> {
                try{
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                    }
                    else{
                        vmMainAdmin.onDeleteSemesterResultedSuccessful();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Main_Admin class' batchDeleteSemester.commit().addOnCompleteListener method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' deleteSemester method.");
        }
    }

    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}