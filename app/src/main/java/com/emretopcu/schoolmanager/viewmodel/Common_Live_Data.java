package com.emretopcu.schoolmanager.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class Common_Live_Data {

    private static Common_Live_Data INSTANCE;

    private MutableLiveData<Boolean> isChangePasswordSuccessful = new MutableLiveData<>();

    private Common_Live_Data(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Live_Data class' constructor method.");
        }
    }

    public static Common_Live_Data getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Common_Live_Data();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Live_Data class' getInstance method.");
            return null;
        }
    }

    public MutableLiveData<Boolean> getIsChangePasswordSuccessful() {
        return isChangePasswordSuccessful;
    }
}
