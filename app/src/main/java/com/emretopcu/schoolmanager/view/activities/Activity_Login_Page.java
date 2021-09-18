package com.emretopcu.schoolmanager.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Person_Type;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;


public class Activity_Login_Page extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextPassword;
    private CheckBox checkBoxSavePassword;
    private CheckBox checkBoxKeepLoggedIn;
    private TextView textViewWarning;
    private Button buttonLogin;
    private VM_Login_Process vmLoginProcess;
    private boolean loginRequested;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_login_page);

            editTextId = findViewById(R.id.editText_id);
            editTextPassword = findViewById(R.id.editText_password);
            checkBoxSavePassword = findViewById(R.id.checkBoxSavePassword);
            checkBoxKeepLoggedIn = findViewById(R.id.checkBoxKeepLoggedIn);
            textViewWarning = findViewById(R.id.textView_warning);
            buttonLogin = findViewById(R.id.button_login);
            buttonLogin.setOnClickListener(v -> {
                try{
                    loginRequested = true;
                    editTextId.clearFocus();
                    editTextPassword.clearFocus();
                    if(editTextId.getText().toString().length()==0 || editTextPassword.getText().toString().length()==0){
                        textViewWarning.setText(R.string.warning_login_empty_field);
                        textViewWarning.setVisibility(View.VISIBLE);
                    }
                    else{
                        progressBar.setVisibility(View.VISIBLE);
                        textViewWarning.setVisibility(View.INVISIBLE);
                        vmLoginProcess.onLoginRequested
                                (editTextId.getText().toString(),editTextPassword.getText().toString(),
                                        checkBoxSavePassword.isChecked(), checkBoxKeepLoggedIn.isChecked());
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Login_Page class' buttonLogin setOnClickListener method.");
                }
            });

            progressBar = findViewById(R.id.progressBar);

            vmLoginProcess = new ViewModelProvider(this).get(VM_Login_Process.class);
            vmLoginProcess.getLoginSuccessful().observe(this, e_login_successful -> {
                try{
                    if(e_login_successful == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBar.setVisibility(View.INVISIBLE);
                        if(vmLoginProcess.getPersonType() == E_Person_Type.MAIN_ADMIN){
                            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.DEPT_ADMIN){
                            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Courses.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.LECTURER){
                            Intent i = new Intent(getApplicationContext(), Activity_Lecturer_Main_Page.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.STUDENT){
                            Intent i = new Intent(getApplicationContext(), Activity_Student_Main_Page.class);
                            startActivity(i);
                        }
                        else{
                            Log.d("Exception","Unexpected person type!");
                        }
                        loginRequested = false;
                    }
                    else if(e_login_successful == E_Successful_Unsuccessful_NoStatement.UNSUCCESSFUL){
                        progressBar.setVisibility(View.INVISIBLE);
                        if(loginRequested){
                            textViewWarning.setText(R.string.warning_login_mismatch);
                            textViewWarning.setVisibility(View.VISIBLE);
                        }
                        editTextId.setText(vmLoginProcess.getId());
                        editTextPassword.setText(vmLoginProcess.getPassword());
                        loginRequested = false;
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Login_Page class' vmLoginProcess.getLoginSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Login_Page class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        try{
            super.onResume();
            loginRequested = false;
            textViewWarning.setVisibility(View.INVISIBLE);
            editTextId.setText(vmLoginProcess.getId());
            if(vmLoginProcess.isSavePassword()){
                editTextPassword.setText(vmLoginProcess.getPassword());
                checkBoxSavePassword.setChecked(true);
            }
            else{
                checkBoxSavePassword.setChecked(false);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Login_Page class' onResume method.");
        }
    }
}
