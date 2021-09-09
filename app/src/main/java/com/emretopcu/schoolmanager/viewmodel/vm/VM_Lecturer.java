package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Lecturer;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Lecturer;

public class VM_Lecturer extends ViewModel implements Interface_Lecturer {


    public VM_Lecturer(){
        try{
            Model_Lecturer.getInstance().setVmLecturer(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Lecturer class' constructor method.");
        }
    }


}



