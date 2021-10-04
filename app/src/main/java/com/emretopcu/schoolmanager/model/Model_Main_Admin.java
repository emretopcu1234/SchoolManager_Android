package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.sql.Time;
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
                        vmMainAdmin.onIsSemesterActiveResulted(true);
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

    public void getFilteredDepartmentList(String unprocessedSemester, String unprocessedDeptNameFilter){
        try {
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String deptNameFilter = Common_Services.convertUnprocessedFilter(unprocessedDeptNameFilter);
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
            String nameFilter = Common_Services.convertUnprocessedFilter(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedFilter(unprocessedSurnameFilter);
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
            String nameFilter = Common_Services.convertUnprocessedFilter(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedFilter(unprocessedSurnameFilter);
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
                idFilter = "3"; // student ids always start with 2.
            }
            String semester = Common_Services.convertUnprocessedSemester(unprocessedSemester);
            String nameFilter = Common_Services.convertUnprocessedFilter(unprocessedNameFilter);
            String surnameFilter = Common_Services.convertUnprocessedFilter(unprocessedSurnameFilter);
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
    
    public void addDepartment(String deptName, String deptId){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDepartment method.");
        }
    }

    public void addDeptAdmin(String id, String name, String surname, String deptId){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addDeptAdmin method.");
        }
    }

    public void addLecturer(String id, String name, String surname, String deptId){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addLecturer method.");
        }
    }

    public void addStudent(String id, String name, String surname, String deptId){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' addStudent method.");
        }
    }

    public void addSemester(String startDate, String endDate){
        try{
            addDepartmentsForNewSemester("fall20212022");
            // TODO yukarıdaki metodu sil, diğer 3 metodu çağır. o metodların içini de şimdiki gibi doldur.
            // TODO sonrasında içinde bulunduğumuz semestera ilgili deptadmin lecturer student bilgileri de girildikten sonra
            // TODO bu metodu ve aşağıdaki diğer 4 metodu orijinal haline döndür.
//            Timestamp timestampStart = Common_Services.convertDateStringToTimestamp(startDate);
//            Timestamp timestampEnd = Common_Services.convertDateStringToTimestamp(endDate);
//            if(timestampStart.compareTo(timestampEnd) >= 0){
//                vmMainAdmin.onAddSemesterResultedReverseOrder();
//                return;
//            }
//            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() < (86400 * 60)){
//                vmMainAdmin.onAddSemesterResultedLowDateDifference();
//                return;
//            }
//            else if (timestampEnd.getSeconds() - timestampStart.getSeconds() > (86400 * 150)){
//                vmMainAdmin.onAddSemesterResultedHighDateDifference();
//                return;
//            }
//            String semesterName = Common_Services.specifySemesterName(startDate,endDate);
//            WriteBatch batchAddSemester = dbRef.batch();
//            Map<String, Object> semesterData = new HashMap<>();
//            semesterData.put("startDate",timestampStart);
//            semesterData.put("endDate",timestampEnd);
//            DocumentReference specificSemester = semestersRef.document(semesterName);
//            batchAddSemester.set(specificSemester,semesterData);
//            Map<String, Object> semesterWeeksData = new HashMap<>();
//            Timestamp timestamp = timestampStart;
//            Calendar c = Calendar.getInstance();
//            int weekIndex = 1;
//            while(timestamp.compareTo(timestampEnd) < 0){
//                semesterWeeksData.put("startDate",timestamp);
//                c.setTimeInMillis(timestamp.getSeconds()*1000);
//                c.add(Calendar.DATE,7);
//                timestamp = new Timestamp(c.getTime());
//                semesterWeeksData.put("endDate",timestamp);
//                DocumentReference specificSemesterWeek = specificSemester.collection("weeks").document("week" + weekIndex);
//                batchAddSemester.set(specificSemesterWeek,semesterWeeksData);
//                weekIndex++;
//            }
//            batchAddSemester.commit().addOnCompleteListener(task -> {
//                try{
//                    if(!task.isSuccessful()){
//                        vmMainAdmin.dataLoadError();
//                    }
//                    else{
//                        vmMainAdmin.onAddSemesterResultedSuccessful();
//                        addDepartmentsForNewSemester(semesterName);
//                        addDeptAdminsForNewSemester(semesterName);
//                        addLecturersForNewSemester(semesterName);
//                        addStudentsForNewSemester(semesterName);
//                    }
//                }
//                catch (Exception e){
//                    Log.d("Exception", "Exception on Model_Main_Admin class' batchAddSemester.commit().addOnCompleteListener method.");
//                }
//            });
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
                    activeSemester = "fall20202021";
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
                                    departmentList.add(new String[]{document.getString("name"),document.getId()});
                                }
                                WriteBatch batchAddDepartments = dbRef.batch();
                                Map<String,Object> deptData = new HashMap<>();
                                CollectionReference newSemesterDepartmentsRef = semesterConditionsRef.document(newSemester).collection("departments");
                                for(int i=0;i<departmentList.size();i++){
                                    DocumentReference entityDeptRef = newSemesterDepartmentsRef.document(departmentList.get(i)[1]);
                                    deptData.put("name",departmentList.get(i)[0]);
                                    batchAddDepartments.set(entityDeptRef,deptData);
                                }
                                batchAddDepartments.commit().addOnCompleteListener(task2 -> Log.d("Exception","olduuuuu"));
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Main_Admin class' departments.get().addOnCompleteListener method.");
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
                    String semester;
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            semester = document.getId();
                            break;
                        }
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
                    String semester;
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            semester = document.getId();
                            break;
                        }
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
                    String semester;
                    if(!task.isSuccessful()){
                        vmMainAdmin.dataLoadError();
                        return;
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (Common_Services.isSemesterActive(document.getTimestamp("startDate"), document.getTimestamp("endDate"))) {
                            semester = document.getId();
                            break;
                        }
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

    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}