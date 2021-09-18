package com.emretopcu.schoolmanager.viewmodel.sharedData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Person_Type;

public class SD_Login_Process {

    private static SD_Login_Process INSTANCE;

    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> createNewUserSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> loginSuccessful;
    private MutableLiveData<E_Successful_Unsuccessful_NoStatement> reloginMainAdminSuccessful;
    private MutableLiveData<E_Change_Password_State> changePasswordSuccessful;
    private E_Person_Type personType;
    private String id;
    private String password;
    private boolean savePassword;


    private SD_Login_Process(){
        try{
            createNewUserSuccessful = new MutableLiveData<>();
            createNewUserSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            loginSuccessful = new MutableLiveData<>();
            loginSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.NO_STATEMENT);
            reloginMainAdminSuccessful = new MutableLiveData<>();
            reloginMainAdminSuccessful.setValue(E_Successful_Unsuccessful_NoStatement.SUCCESSFUL);
            changePasswordSuccessful = new MutableLiveData<>();
            changePasswordSuccessful.setValue(E_Change_Password_State.NO_STATEMENT);
            personType = E_Person_Type.NO_STATEMENT;
            id = "";
            password = "";
            savePassword = false;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Login_Process class' constructor method.");
        }
    }

    public static SD_Login_Process getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new SD_Login_Process();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on SD_Login_Process class' getInstance method.");
            return null;
        }
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getCreateNewUserSuccessful() {
        return createNewUserSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getLoginSuccessful() {
        return loginSuccessful;
    }

    public MutableLiveData<E_Successful_Unsuccessful_NoStatement> getReloginMainAdminSuccessful() {
        return reloginMainAdminSuccessful;
    }

    public MutableLiveData<E_Change_Password_State> getChangePasswordSuccessful() {
        return changePasswordSuccessful;
    }

    public E_Person_Type getPersonType() {
        return personType;
    }

    public void setPersonType(E_Person_Type personType) {
        this.personType = personType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSavePassword() {
        return savePassword;
    }

    public void setSavePassword(boolean savePassword) {
        this.savePassword = savePassword;
    }

}
