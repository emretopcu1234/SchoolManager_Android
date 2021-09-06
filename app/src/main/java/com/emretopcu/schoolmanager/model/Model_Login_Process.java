package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.emretopcu.schoolmanager.viewmodel.VM_Login_Process;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Model_Login_Process {

    private static Model_Login_Process INSTANCE;
    private VM_Login_Process vmLoginProcess;

    private static final String FAKE_EMAIL_DOMAIN = "@myfakeschoolmanagerapp.com";

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore dbRef;
    private CollectionReference peopleRef;


    private Model_Login_Process(){
        try{
            auth = FirebaseAuth.getInstance();
            dbRef = FirebaseFirestore.getInstance();
            peopleRef = dbRef.collection("people");


        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Login_Process class' constructor method.");
        }
    }

    public static Model_Login_Process getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Login_Process();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Login_Process class' getInstance method.");
            return null;
        }
    }

    public void login(String id, String password){
        try{
            String email = id + FAKE_EMAIL_DOMAIN;
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();
                            if(id.startsWith("1")){
                                Model_Dept_Admin.getInstance().setDeptAdminId(id);
                                // TODO interfaceVMLoginProcess üzerinden activity login page'e hangi tip person olduğu gönderilecek.
                            }
                            else if(id.startsWith("2")){
                                Model_Lecturer.getInstance().setLecturerId(id);
                                // TODO interfaceVMLoginProcess üzerinden activity login page'e hangi tip person olduğu gönderilecek.
                            }
                            else if(id.startsWith("3")){
                                Model_Student.getInstance().setStudentId(id);
                                // TODO interfaceVMLoginProcess üzerinden activity login page'e hangi tip person olduğu gönderilecek.
                            }
                            else if(id.equals("ADMIN")){
                                // TODO interfaceVMLoginProcess üzerinden activity login page'e hangi tip person olduğu gönderilecek.
                            }
                        } else {
                            // TODO toast message çıkarılacak.
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Model_Login_Process class' auth signInWithEmailAndPassword onComplete method.");
                    }
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Login_Process class' login method.");
        }
    }

    public void changePassword(String oldPassword, String newPassword){
        try{
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),oldPassword);
            user.reauthenticate(credential).addOnCompleteListener(task -> {
                try{
                    if(task.isSuccessful()){
                        user.updatePassword(newPassword).addOnCompleteListener(task1 -> {
                            try{
                                if(task1.isSuccessful()){
                                    // TODO toast
                                }
                                else {
                                    // TODO toast
                                }
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Login_Process class' user updatePassword onComplete method.");
                            }
                        });
                    }
                    else {
                        // TODO toast
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Model_Login_Process class' user reauthenticate onComplete method.");
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Login_Process class' changePassword method.");
        }
    }

    public void setVmLoginProcess(VM_Login_Process vmLoginProcess) {
        this.vmLoginProcess = vmLoginProcess;
    }
}
