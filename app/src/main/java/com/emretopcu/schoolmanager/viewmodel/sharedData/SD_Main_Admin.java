package com.emretopcu.schoolmanager.viewmodel.sharedData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;

import java.util.ArrayList;

public class SD_Main_Admin {

    private static SD_Main_Admin INSTANCE;

    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setSemestersSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> isSemesterActiveSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> setDepartmentsSuccessful;
    private ArrayList<String> semesterList;
    private boolean semesterActive;
    private ArrayList<String[]> departmentList;



    private SD_Main_Admin(){
        try{
            setSemestersSuccessful = new MutableLiveData<>();
            setSemestersSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            isSemesterActiveSuccessful = new MutableLiveData<>();
            isSemesterActiveSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            setDepartmentsSuccessful = new MutableLiveData<>();
            setDepartmentsSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            semesterList = new ArrayList<>();
            semesterActive = false;
            departmentList = new ArrayList<>();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Main_Admin class' constructor method.");
        }
    }

    public static SD_Main_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new SD_Main_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Main_Admin class' getInstance method.");
            return null;
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetSemestersSuccessful() {
        return setSemestersSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getIsSemesterActiveSuccessful() {
        return isSemesterActiveSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getSetDepartmentsSuccessful() {
        return setDepartmentsSuccessful;
    }

    public ArrayList<String> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<String> semesterList) {
        this.semesterList = semesterList;
    }

    public boolean isSemesterActive() {
        return semesterActive;
    }

    public void setSemesterActive(boolean semesterActive) {
        this.semesterActive = semesterActive;
    }

    public ArrayList<String[]> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<String[]> departmentList) {
        this.departmentList = departmentList;
    }
}
