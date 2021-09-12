package com.emretopcu.schoolmanager.viewmodel.vm;

import android.media.midi.MidiOutputPort;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.sharedData.SD_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_Auth_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Create_New_User_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Login_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Person_Type;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Relogin_Main_Admin_Successful;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Login_Process;

public class VM_Login_Process extends ViewModel implements Interface_Login_Process {

    private SD_Login_Process sdLoginProcess;
    private Model_Login_Process modelLoginProcess;
    private MutableLiveData<E_Create_New_User_Successful> createNewUserSuccessful;
    private MutableLiveData<E_Login_Successful> loginSuccessful;
    private MutableLiveData<E_Relogin_Main_Admin_Successful> reloginMainAdminSuccessful;
    private MutableLiveData<E_Change_Password_Successful> changePasswordSuccessful;
    private MutableLiveData<E_Change_Password_Auth_Successful> changePasswordAuthSuccessful;



    public VM_Login_Process(){
        try{
            sdLoginProcess = SD_Login_Process.getInstance();
            modelLoginProcess = Model_Login_Process.getInstance();
            modelLoginProcess.setVmLoginProcess(this);
            createNewUserSuccessful = sdLoginProcess.getCreateNewUserSuccessful();
            loginSuccessful = sdLoginProcess.getLoginSuccessful();
            reloginMainAdminSuccessful = sdLoginProcess.getReloginMainAdminSuccessful();
            changePasswordSuccessful = sdLoginProcess.getChangePasswordSuccessful();
            changePasswordAuthSuccessful = sdLoginProcess.getChangePasswordAuthSuccessful();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' constructor method.");
        }
    }

    public void onLoginInfoRequested(){
        try{
            loginSuccessful.setValue(E_Login_Successful.NO_STATEMENT);
            modelLoginProcess.getLoginInfo();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginInfoRequested method.");
        }
    }

    public void onCreateNewUserRequested(String id){
        try{
            createNewUserSuccessful.setValue(E_Create_New_User_Successful.NO_STATEMENT);
            modelLoginProcess.createNewUser(id);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onCreateNewUserRequested method.");
        }
    }

    public void onLoginRequested(String id, String password, boolean isSavePassword, boolean isKeepLoggedIn){
        try{
            sdLoginProcess.setId(id);
            sdLoginProcess.setPassword(password);
            sdLoginProcess.setSavePassword(isSavePassword);
            loginSuccessful.setValue(E_Login_Successful.NO_STATEMENT);
            modelLoginProcess.login(id,password,isSavePassword,isKeepLoggedIn);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginRequested method.");
        }
    }

    public void onChangePasswordRequested(String oldPassword, String newPassword){
        try{
            changePasswordSuccessful.setValue(E_Change_Password_Successful.NO_STATEMENT);
            changePasswordAuthSuccessful.setValue(E_Change_Password_Auth_Successful.NO_STATEMENT);
            modelLoginProcess.changePassword(oldPassword,newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onChangePasswordRequested method.");
        }
    }

    public void onLogoutRequested(){
        loginSuccessful.setValue(E_Login_Successful.NO_STATEMENT);
        sdLoginProcess.setId(modelLoginProcess.getId());
        sdLoginProcess.setPassword(modelLoginProcess.getPassword());
        sdLoginProcess.setSavePassword(modelLoginProcess.isSavePassword());
        modelLoginProcess.logout();
    }

    public MutableLiveData<E_Create_New_User_Successful> getCreateNewUserSuccessful() {
        return createNewUserSuccessful;
    }

    public MutableLiveData<E_Login_Successful> getLoginSuccessful() {
        return loginSuccessful;
    }

    public MutableLiveData<E_Relogin_Main_Admin_Successful> getReloginMainAdminSuccessful() {
        return reloginMainAdminSuccessful;
    }

    public MutableLiveData<E_Change_Password_Successful> getChangePasswordSuccessful() {
        return changePasswordSuccessful;
    }

    public MutableLiveData<E_Change_Password_Auth_Successful> getChangePasswordAuthSuccessful() {
        return changePasswordAuthSuccessful;
    }

    public E_Person_Type getPersonType() {
        return sdLoginProcess.getPersonType();
    }

    public String getId() {
        return sdLoginProcess.getId();
    }

    public String getPassword() {
        return sdLoginProcess.getPassword();
    }

    public boolean isSavePassword() {
        return sdLoginProcess.isSavePassword();
    }

    @Override
    public void onCreateNewUserResulted(boolean isCreateNewUserSuccessful) {
        try{
            if(isCreateNewUserSuccessful){
                createNewUserSuccessful.setValue(E_Create_New_User_Successful.SUCCESSFUL);
            }
            else{
                createNewUserSuccessful.setValue(E_Create_New_User_Successful.UNSUCCESSFUL);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onCreateNewUserResulted method.");
        }
    }

    @Override
    public void onLoginInfoResulted(String id, String password, boolean isSavePassword) {
        try{
            sdLoginProcess.setId(id);
            if(isSavePassword){
                sdLoginProcess.setPassword(password);
            }
            sdLoginProcess.setSavePassword(isSavePassword);
            loginSuccessful.setValue(E_Login_Successful.UNSUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onGetLoginInfoResulted method.");
        }
    }

    @Override
    public void onLoginResultedWithMainAdmin() {
        try{
            sdLoginProcess.setPersonType(E_Person_Type.MAIN_ADMIN);
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithMainAdmin method.");
        }
    }

    @Override
    public void onLoginResultedWithDeptAdmin() {
        try{
            sdLoginProcess.setPersonType(E_Person_Type.DEPT_ADMIN);
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithDeptAdmin method.");
        }
    }

    @Override
    public void onLoginResultedWithLecturer() {
        try{
            sdLoginProcess.setPersonType(E_Person_Type.LECTURER);
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithLecturer method.");
        }
    }

    @Override
    public void onLoginResultedWithStudent() {
        try{
            sdLoginProcess.setPersonType(E_Person_Type.STUDENT);
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithStudent method.");
        }
    }

    @Override
    public void onLoginResultedUnsuccessful() {
        try{
            loginSuccessful.setValue(E_Login_Successful.UNSUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedUnsuccessful method.");
        }
    }

    @Override
    public void onReloginForMainAdminResulted(boolean isReloginForMainAdminSuccessful) {
        try{
            if(isReloginForMainAdminSuccessful){
                reloginMainAdminSuccessful.setValue(E_Relogin_Main_Admin_Successful.SUCCESSFUL);
            }
            else{
                reloginMainAdminSuccessful.setValue(E_Relogin_Main_Admin_Successful.UNSUCCESSFUL);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onReloginForMainAdminResultedUnsuccessful method.");
        }
    }

    @Override
    public void onChangePasswordResulted(boolean isChangePasswordAuthSuccessful, boolean isChangePasswordSuccessful){
        try{
            if(isChangePasswordAuthSuccessful){
                changePasswordAuthSuccessful.setValue(E_Change_Password_Auth_Successful.SUCCESSFUL);
                if(isChangePasswordSuccessful){
                    changePasswordSuccessful.setValue(E_Change_Password_Successful.SUCCESSFUL);
                }
                else{
                    changePasswordSuccessful.setValue(E_Change_Password_Successful.UNSUCCESSFUL);
                }
            }
            else{
                changePasswordAuthSuccessful.setValue(E_Change_Password_Auth_Successful.UNSUCCESSFUL);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onChangePasswordResulted method.");
        }
    }
}
