package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Lecturer;

public class VM_Lecturer extends ViewModel {


    public VM_Lecturer(){
        try{
            Model_Lecturer.getInstance().setVmLecturer(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Lecturer class' constructor method.");
        }
    }


}



