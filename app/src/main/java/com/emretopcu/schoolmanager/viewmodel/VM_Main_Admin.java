package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Main_Admin;

public class VM_Main_Admin extends ViewModel {


    public VM_Main_Admin(){
        try{
            Model_Main_Admin.getInstance().setVmMainAdmin(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Main_Admin class' constructor method.");
        }
    }


}



