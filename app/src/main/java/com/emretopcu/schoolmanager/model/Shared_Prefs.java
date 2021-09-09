package com.emretopcu.schoolmanager.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.emretopcu.schoolmanager.view.activities.Activity_Initial;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Login_Process;

public class Shared_Prefs {

    private static Shared_Prefs INSTANCE = new Shared_Prefs();
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;
    private Interface_Login_Process vmLoginProcess;


    public static void initialize(Activity_Initial activityInitial){
        try{
            prefs = activityInitial.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            prefsEditor = prefs.edit();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Shared_Prefs class' initialize method.");
        }
    }

    public static Shared_Prefs getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Shared_Prefs();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Shared_Prefs class' getInstance method.");
            return null;
        }
    }

    public void setVmLoginProcess(Interface_Login_Process vmLoginProcess) {
        this.vmLoginProcess = vmLoginProcess;
    }

    public String getId() {
        return prefs.getString("id","");
    }

    public void setId(String id) {
        prefsEditor.putString("id", id);
        prefsEditor.commit();
    }

    public String getPassword() {
        return prefs.getString("password","");
    }

    public void setPassword(String password) {
        prefsEditor.putString("password", password);
        prefsEditor.commit();
    }

    public boolean isSavePassword() {
        return prefs.getBoolean("save_password",false);
    }

    public void setSavePassword(boolean savePassword) {
        prefsEditor.putBoolean("save_password", savePassword);
        prefsEditor.commit();
    }

    public boolean isKeepLoggedIn() {
        return prefs.getBoolean("is_keep_logged_in",false);
    }

    public void setKeepLoggedIn(boolean keepLoggedIn) {
        prefsEditor.putBoolean("is_keep_logged_in", keepLoggedIn);
        prefsEditor.commit();
    }
}