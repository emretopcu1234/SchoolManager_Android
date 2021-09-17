package com.emretopcu.schoolmanager.model;

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

    private Model_Main_Admin(){
        try{
            dbRef = FirebaseFirestore.getInstance();
            semestersRef = dbRef.collection("semesters");
            semesterConditionsRef = dbRef.collection("semesterConditions");
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
                    Date todayDate = new Date();
                    if(task.getResult().getTimestamp("startDate").compareTo(new Timestamp(todayDate)) <= 0
                            && task.getResult().getTimestamp("endDate").compareTo(new Timestamp(todayDate)) <= 0){

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
    
    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}