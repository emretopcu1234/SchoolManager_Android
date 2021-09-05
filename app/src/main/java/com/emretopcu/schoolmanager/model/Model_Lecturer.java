package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.VM_Lecturer;

public class Model_Lecturer {

    private static Model_Lecturer INSTANCE;
    private VM_Lecturer vmLecturer;

    private String lecturerId;

    private Model_Lecturer(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Lecturer class' constructor method.");
        }
    }

    public static Model_Lecturer getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Lecturer();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Lecturer class' getInstance method.");
            return null;
        }
    }

    public void setVmLecturer(VM_Lecturer vmLecturer) {
        this.vmLecturer = vmLecturer;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}
