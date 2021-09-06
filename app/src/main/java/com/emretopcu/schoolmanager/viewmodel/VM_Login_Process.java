package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Login_Process;

public class VM_Login_Process extends ViewModel {

    private MutableLiveData<Boolean> isChangePasswordSuccessful;

    public VM_Login_Process(){
        try{
            Model_Login_Process.getInstance().setVmLoginProcess(this);
            isChangePasswordSuccessful = Common_Live_Data.getInstance().getIsChangePasswordSuccessful();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' constructor method.");
        }
    }

    public void onLoginRequested(String id, String password){
        try{
            Model_Login_Process.getInstance().login(id,password);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginRequested method.");
        }
    }

    public void onChangePasswordRequested(String oldPassword, String newPassword){
        try{
            Model_Login_Process.getInstance().changePassword(oldPassword,newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onChangePasswordRequested method.");
        }
    }




    public MutableLiveData<Boolean> getIsChangePasswordSuccessful() {
        return isChangePasswordSuccessful;
    }
}
