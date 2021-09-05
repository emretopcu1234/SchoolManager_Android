package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.viewmodel.VM_Main_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Model_Main_Admin {

    private static Model_Main_Admin INSTANCE;
    private VM_Main_Admin vmMainAdmin;

    private static final String FAKE_EMAIL_DOMAIN = "@myfakeschoolmanagerapp.com";
    private static final String FAKE_PASSWORD_PREFIX = "PW_";

    private FirebaseAuth auth;
    private FirebaseFirestore dbRef;

    private Model_Main_Admin(){
        try{
            auth = FirebaseAuth.getInstance();
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

    public void createNewUser(int id){
        try{
            String emailAddress = id + FAKE_EMAIL_DOMAIN;
            String password = FAKE_PASSWORD_PREFIX + id;
            auth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try{
                        if(task.isSuccessful()){
                            // TODO interface vm üzerinden haber gönder. toast çıksın. (başarılı)
                        }
                        else{
                            // TODO interface vm üzerinden haber gönder. toast çıksın. (başarısız)
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Model_Main_Admin class' auth createUserWithEmailAndPassword onComplete method.");
                    }
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Main_Admin class' createNewUser method.");
        }
    }

    public void setVmMainAdmin(VM_Main_Admin vmMainAdmin) {
        this.vmMainAdmin = vmMainAdmin;
    }
}
