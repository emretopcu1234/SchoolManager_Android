package com.emretopcu.schoolmanager.view.activities;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.viewmodel.VM_Login_Process;


public class Helper_Dialog_Change_Password extends AppCompatActivity {

    private static final int MINIMUM_PASSWORD_SIZE = 8;
    private String[] passwordFields;
    private Interface_General_Activity interfaceHelperDialogChangePassword;
    private VM_Login_Process vmLoginProcess;

    public Helper_Dialog_Change_Password(Interface_General_Activity interfaceHelperDialogChangePassword){
        try{
            this.interfaceHelperDialogChangePassword = interfaceHelperDialogChangePassword;
            passwordFields = new String[3];
            vmLoginProcess = new ViewModelProvider(this).get(VM_Login_Process.class);
            resetFields();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Helper_Dialog_Change_Password class' constructor method.");
        }
    }

    public boolean onFieldChanged(int fieldIndicator, String fieldValue){
        try{
            passwordFields[fieldIndicator] = fieldValue;
            boolean isAllFieldsOK = true;
            for(int i=0;i<passwordFields.length;i++){
                if(passwordFields[i].length() == 0){
                    isAllFieldsOK = false;
                    break;
                }
            }
            return isAllFieldsOK;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Helper_Dialog_Change_Password class' onFieldChanged method.");
            return false;
        }
    }

    public void onOKClicked(){
        try{
            if(passwordFields[1].equals(passwordFields[2])){
                if(passwordFields[1].length() < MINIMUM_PASSWORD_SIZE){
                    interfaceHelperDialogChangePassword.setAndShowWarningOnDialogChangePassword(R.string.warning_change_password_short_password, View.INVISIBLE);
                }
                else{
                    vmLoginProcess.onChangePasswordRequested(passwordFields[0],passwordFields[1]);
                }
            }
            else{
                interfaceHelperDialogChangePassword.setAndShowWarningOnDialogChangePassword((R.string.warning_change_password_wrong_confirmation), View.VISIBLE);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Helper_Dialog_Change_Password class' onOKClicked method.");
        }
    }

    public void onCancelClicked(){
        try{
            resetFields();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Helper_Dialog_Change_Password class' onCancelClicked method.");
        }
    }

    private void resetFields(){
        try{
            for(int i=0;i<passwordFields.length;i++){
                passwordFields[i] = "";
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Helper_Dialog_Change_Password class' resetFields method.");
        }
    }
}
