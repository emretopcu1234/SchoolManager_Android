package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Main_Admin;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Model_Main_Admin {

    private static Model_Main_Admin INSTANCE;
    private Interface_Main_Admin vmMainAdmin;

    private FirebaseFirestore dbRef;

    private Model_Main_Admin(){
        try{
            dbRef = FirebaseFirestore.getInstance();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' constructor method.");
        }
    }

    public static Model_Main_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Main_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' getInstance method.");
            return null;
        }
    }

    public void setVmMainAdmin(Interface_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}
