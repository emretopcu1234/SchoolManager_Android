package com.emretopcu.schoolmanager.viewmodel.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emretopcu.schoolmanager.model.Model_Login_Process;
import com.emretopcu.schoolmanager.model.Shared_Prefs;
import com.emretopcu.schoolmanager.viewmodel.Common_Live_Data;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_Auth_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Create_New_User_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Login_Successful;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Person_Type;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Relogin_Main_Admin_Successful;
import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Login_Process;

public class VM_Login_Process extends ViewModel implements Interface_Login_Process {

    private MutableLiveData<E_Create_New_User_Successful> createNewUserSuccessful;
    private MutableLiveData<E_Login_Successful> loginSuccessful;
    private MutableLiveData<E_Relogin_Main_Admin_Successful> reloginMainAdminSuccessful;
    private MutableLiveData<E_Change_Password_Successful> changePasswordSuccessful;
    private MutableLiveData<E_Change_Password_Auth_Successful> changePasswordAuthSuccessful;
    private E_Person_Type personType;


    public VM_Login_Process(){
        try{
            Shared_Prefs.getInstance().setVmLoginProcess(this);
            Model_Login_Process.getInstance().setVmLoginProcess(this);
            createNewUserSuccessful = Common_Live_Data.getInstance().getCreateNewUserSuccessful();
            loginSuccessful = Common_Live_Data.getInstance().getLoginSuccessful();
            reloginMainAdminSuccessful = Common_Live_Data.getInstance().getReloginMainAdminSuccessful();
            changePasswordSuccessful = Common_Live_Data.getInstance().getChangePasswordSuccessful();
            changePasswordAuthSuccessful = Common_Live_Data.getInstance().getChangePasswordAuthSuccessful();
            personType = Common_Live_Data.getInstance().getPersonType();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' constructor method.");
        }
    }

    public void onCreateNewUserRequested(String id){
        try{
            createNewUserSuccessful.setValue(E_Create_New_User_Successful.NO_STATEMENT);
            Model_Login_Process.getInstance().createNewUser(id);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onCreateNewUserRequested method.");
        }
    }

    public void onLoginRequested(String id, String password, boolean isSavePassword, boolean isKeepLoggedIn){
        try{
            loginSuccessful.setValue(E_Login_Successful.NO_STATEMENT);
            Model_Login_Process.getInstance().login(id,password,isSavePassword,isKeepLoggedIn);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginRequested method.");
        }
    }

    public void onChangePasswordRequested(String oldPassword, String newPassword){
        try{
            changePasswordSuccessful.setValue(E_Change_Password_Successful.NO_STATEMENT);
            changePasswordAuthSuccessful.setValue(E_Change_Password_Auth_Successful.NO_STATEMENT);
            Model_Login_Process.getInstance().changePassword(oldPassword,newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onChangePasswordRequested method.");
        }
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
        return personType;
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
    public void onLoginResultedWithMainAdmin() {
        try{
            personType = E_Person_Type.MAIN_ADMIN;
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithMainAdmin method.");
        }
    }

    @Override
    public void onLoginResultedWithDeptAdmin() {
        try{
            personType = E_Person_Type.DEPT_ADMIN;
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithDeptAdmin method.");
        }
    }

    @Override
    public void onLoginResultedWithLecturer() {
        try{
            personType = E_Person_Type.LECTURER;
            loginSuccessful.setValue(E_Login_Successful.SUCCESSFUL);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on VM_Login_Process class' onLoginResultedWithLecturer method.");
        }
    }

    @Override
    public void onLoginResultedWithStudent() {
        try{
            personType = E_Person_Type.STUDENT;
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
