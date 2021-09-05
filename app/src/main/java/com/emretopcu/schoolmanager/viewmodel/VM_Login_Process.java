package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Login_Process;

public class VM_Login_Process extends ViewModel {

    private MutableLiveData<Boolean> isChangePasswordSuccessful = new MutableLiveData<>();

    public VM_Login_Process(){
        try{
            Model_Login_Process.getInstance().setVmLoginProcess(this);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' constructor method.");
        }
    }

    public void onLoginRequested(String id, String password){

    }

    public void onChangePasswordRequested(String oldPassword, String newPassword){
        Model_Login_Process.getInstance().changePassword(oldPassword,newPassword);
    }




    public MutableLiveData<Boolean> getIsChangePasswordSuccessful() {
        return isChangePasswordSuccessful;
    }
}
