package com.emretopcu.schoolmanager.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
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
    private Interface_Login_Process vmLoginProcess;

    private static final String FAKE_EMAIL_DOMAIN = "@myfakeschoolmanagerapp.com";
    private static  final String FAKE_PASSWORD_PREFIX = "PW_";

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

    public void createNewUser(String id){
        try{
            String emailAddress = id + FAKE_EMAIL_DOMAIN;
            String password = FAKE_PASSWORD_PREFIX + id;
            auth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try{
                        if(task.isSuccessful()){
                            Log.d("Exception", "basarili: " + id);
                            Log.d("Exception","before signout: " + auth.getCurrentUser().getEmail());
                            auth.signOut();
//                            reloginForMainAdmin();
                            vmLoginProcess.onCreateNewUserResulted(true);
                        }
                        else{
                            Log.d("Exception", "basarisiz: " + id);
                            vmLoginProcess.onCreateNewUserResulted(false);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Model_Login_Process class' auth createUserWithEmailAndPassword onComplete method.");
                    }
                }
            });
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Login_Process class' createNewUser method.");
        }
    }

    public void login(String id, String password, boolean isSavePassword, boolean isKeepLoggedIn){
        try{
            String email = id + FAKE_EMAIL_DOMAIN;
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try {
                        if (task.isSuccessful()) {
                            Shared_Prefs.getInstance().setSavePassword(isSavePassword);
                            Shared_Prefs.getInstance().setKeepLoggedIn(isKeepLoggedIn);
                            user = auth.getCurrentUser();
                            if(id.startsWith("1")){
                                Model_Dept_Admin.getInstance().setDeptAdminId(id);
                                vmLoginProcess.onLoginResultedWithDeptAdmin();
                            }
                            else if(id.startsWith("2")){
                                Model_Lecturer.getInstance().setLecturerId(id);
                                vmLoginProcess.onLoginResultedWithLecturer();
                            }
                            else if(id.startsWith("3")){
                                Model_Student.getInstance().setStudentId(id);
                                vmLoginProcess.onLoginResultedWithStudent();
                            }
                            else if(id.equals("ADMIN")){
                                vmLoginProcess.onLoginResultedWithMainAdmin();
                            }
                        } else {
                            vmLoginProcess.onLoginResultedUnsuccessful();
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

    public void reloginForMainAdmin(){
        try{
            String email = "ADMIN" + FAKE_EMAIL_DOMAIN;
            String password = Shared_Prefs.getInstance().getPassword();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();
                            vmLoginProcess.onReloginForMainAdminResulted(true);

                        } else {
                            vmLoginProcess.onReloginForMainAdminResulted(false);
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
                                    vmLoginProcess.onChangePasswordResulted(true,true);
                                }
                                else {
                                    vmLoginProcess.onChangePasswordResulted(true,false);
                                }
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on Model_Login_Process class' user updatePassword onComplete method.");
                            }
                        });
                    }
                    else {
                        vmLoginProcess.onChangePasswordResulted(false,false);
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

    public void setVmLoginProcess(Interface_Login_Process vmLoginProcess) {
        this.vmLoginProcess = vmLoginProcess;
    }
}
