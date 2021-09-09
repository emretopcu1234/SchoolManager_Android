package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Student;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Student;

public class VM_Student extends ViewModel implements Interface_Student {


    public VM_Student(){
        try{
            Model_Student.getInstance().setVmStudent(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Student class' constructor method.");
        }
    }


}



