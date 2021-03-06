package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Student;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Student;

public class Model_Student {

    private static Model_Student INSTANCE;
    private Interface_Student vmStudent;

    private String studentId;

    private Model_Student(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Student class' constructor method.");
        }
    }

    public static Model_Student getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Student();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Student class' getInstance method.");
            return null;
        }
    }

    public void setVmStudent(Interface_Student vmStudent) {
        this.vmStudent = vmStudent;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}


