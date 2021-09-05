package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Dept_Admin;

public class VM_Dept_Admin extends ViewModel {


    public VM_Dept_Admin(){
        try{
            Model_Dept_Admin.getInstance().setVmDeptAdmin(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Dept_Admin class' constructor method.");
        }
    }


}


