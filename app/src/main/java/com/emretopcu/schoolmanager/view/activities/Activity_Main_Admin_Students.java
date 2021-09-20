package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Students;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Main_Admin_Students extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Main_Admin_Students adapter;
    private RecyclerView recyclerViewMainAdminStudents;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderStudent;
    private View viewDialogStudent;
    private AlertDialog alertDialogStudent;

    private AlertDialog.Builder builderChangePassword;
    private View viewDialogChangePassword;
    private AlertDialog alertDialogChangePassword;

    private EditText editTextDialogChangePasswordOldPassword;
    private EditText editTextDialogChangePasswordNewPassword;
    private EditText editTextDialogChangePasswordNewPasswordConfirm;
    private TextView textViewDialogChangePassword;
    private Button buttonDialogChangePasswordOK;
    private Button buttonDialogChangePasswordCancel;
    private Helper_Dialog_Change_Password helperDialogChangePassword;

    private Button buttonAddDelete;
    private Button buttonSelectCancel;
    private Button buttonSearchId;
    private Button buttonCancelSearchId;
    private Button buttonSearchName;
    private Button buttonCancelSearchName;
    private Button buttonSearchSurname;
    private Button buttonCancelSearchSurname;
    private Button buttonFilterDeptName;
    private Button buttonCancelFilterDeptName;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Main_Admin vmMainAdmin;

    private ProgressBar progressBarStudent;
    private boolean progressBarIndicator_isSemesterActive;
    private boolean progressBarIndicator_setStudents;

    private ProgressBar progressBarChangePassword;

    // TODO bilgi amaçlı: studentlar içinde 30011 ee'den ce'ye, 30028 ee'den me'ye, 30072 phy'den ee'ye bölümü değişti.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_students);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_main_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewMainAdminStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewMainAdminStudents.setLayoutManager(layoutManager);

            builderStudent = new AlertDialog.Builder(this);
            viewDialogStudent = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_students, null);
            builderStudent.setView(viewDialogStudent);
            alertDialogStudent = builderStudent.create();
            alertDialogStudent.setCancelable(false);
            alertDialogStudent.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            builderChangePassword = new AlertDialog.Builder(this);
            viewDialogChangePassword = this.getLayoutInflater().inflate(R.layout.dialog_change_password, null);
            builderChangePassword.setView(viewDialogChangePassword);
            alertDialogChangePassword = builderChangePassword.create();
            alertDialogChangePassword.setCancelable(false);
            alertDialogChangePassword.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            editTextDialogChangePasswordOldPassword = viewDialogChangePassword.findViewById(R.id.editText_old_password);
            editTextDialogChangePasswordNewPassword = viewDialogChangePassword.findViewById(R.id.editText_new_password);
            editTextDialogChangePasswordNewPasswordConfirm = viewDialogChangePassword.findViewById(R.id.editText_new_password_confirm);
            editTextDialogChangePasswordOldPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(0,s.toString()));
                        if(buttonDialogChangePasswordOK.isEnabled()){
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        }
                        else{
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        }
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Students class' editTextDialogChangePasswordOldPassword onTextChanged method.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editTextDialogChangePasswordNewPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(1,s.toString()));
                        if(buttonDialogChangePasswordOK.isEnabled()){
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        }
                        else{
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        }
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Students class' editTextDialogChangePasswordNewPassword onTextChanged method.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editTextDialogChangePasswordNewPasswordConfirm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(2,s.toString()));
                        if(buttonDialogChangePasswordOK.isEnabled()){
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        }
                        else{
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        }
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Students class' editTextDialogChangePasswordNewPasswordConfirm onTextChanged method.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            textViewDialogChangePassword = viewDialogChangePassword.findViewById(R.id.textView_warning);
            buttonDialogChangePasswordOK = viewDialogChangePassword.findViewById(R.id.button_ok);
            buttonDialogChangePasswordOK.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onOKClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonDialogChangePasswordCancel setOnClickListener method.");
                }
            });
            progressBarChangePassword = viewDialogChangePassword.findViewById(R.id.progressBar);
            helperDialogChangePassword = new Helper_Dialog_Change_Password(this);

            buttonAddDelete = findViewById(R.id.button_add_delete);
            buttonAddDelete.setVisibility(View.INVISIBLE);
            buttonAddDelete.setOnClickListener(v -> {
                try{
                    alertDialogStudent.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonAddDelete setOnClickListener method.");
                }
            });

            buttonSelectCancel = findViewById(R.id.button_select_cancel);
            buttonSelectCancel.setVisibility(View.INVISIBLE);
            buttonSelectCancel.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonSelectCancel setOnClickListener method.");
                }
            });

            buttonSearchId = findViewById(R.id.button_search_id);
            buttonSearchId.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonSearchId setOnClickListener method.");
                }
            });

            buttonCancelSearchId = findViewById(R.id.button_cancel_search_id);
            buttonCancelSearchId.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonCancelSearchId setOnClickListener method.");
                }
            });

            buttonSearchName = findViewById(R.id.button_search_name);
            buttonSearchName.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonSearchName setOnClickListener method.");
                }
            });

            buttonCancelSearchName = findViewById(R.id.button_cancel_search_name);
            buttonCancelSearchName.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonCancelSearchName setOnClickListener method.");
                }
            });

            buttonSearchSurname = findViewById(R.id.button_search_surname);
            buttonSearchSurname.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonSearchSurname setOnClickListener method.");
                }
            });

            buttonCancelSearchSurname = findViewById(R.id.button_cancel_search_surname);
            buttonCancelSearchSurname.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonCancelSearchSurname setOnClickListener method.");
                }
            });

            buttonFilterDeptName = findViewById(R.id.button_filter_empty_closed);
            buttonFilterDeptName.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonFilterDeptName setOnClickListener method.");
                }
            });

            buttonCancelFilterDeptName = findViewById(R.id.button_filter_full_closed);
            buttonCancelFilterDeptName.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' buttonCancelFilterDeptName setOnClickListener method.");
                }
            });

            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_students).setChecked(true);

            progressBarStudent = findViewById(R.id.progressBar);

            vmLoginProcess = new ViewModelProvider(this).get(VM_Login_Process.class);
            vmLoginProcess.getChangePasswordSuccessful().observe(this, e_change_password_state -> {
                try{
                    if(e_change_password_state == E_Change_Password_State.SUCCESSFUL){
                        alertDialogChangePassword.dismiss();
                        showToastMessage(R.string.toast_change_password_successful);
                    }
                    else if(e_change_password_state == E_Change_Password_State.CANNOT_CHANGED){
                        setAndShowWarningOnDialogChangePassword(R.string.warning_change_password_cannot_changed, View.VISIBLE);
                    }
                    else if(e_change_password_state == E_Change_Password_State.WRONG_PASSWORD){
                        setAndShowWarningOnDialogChangePassword(R.string.warning_change_password_wrong_password, View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
                }
            });

            vmMainAdmin = new ViewModelProvider(this).get(VM_Main_Admin.class);
            vmMainAdmin.getSetSemestersSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        fragmentUserAndSemester.setSpinnerList(vmMainAdmin.getSemesterList());
                        fragmentUserAndSemester.setSpinnerItem(Common_Variables_View.SEMESTER_SPINNER_POSITION);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' vmMainAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmMainAdmin.getIsSemesterActiveSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_isSemesterActive = true;
                        if(progressBarIndicator_setStudents){
                            progressBarStudent.setVisibility(View.INVISIBLE);
                        }
                        if(vmMainAdmin.isSemesterActive()){
                            buttonAddDelete.setVisibility(View.VISIBLE);
                            buttonSelectCancel.setVisibility(View.VISIBLE);
                        }
                        else{
                            buttonAddDelete.setVisibility(View.INVISIBLE);
                            buttonSelectCancel.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' vmMainAdmin.getIsSemesterActiveSuccessful().observe method.");
                }
            });
            vmMainAdmin.getSetStudentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setStudents = true;
                        if(progressBarIndicator_isSemesterActive){
                            progressBarStudent.setVisibility(View.INVISIBLE);
                        }
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Students(this, vmMainAdmin.getStudentList());
                            recyclerViewMainAdminStudents.setAdapter(adapter);
                        }
                        else{
                            adapter.setStudentList(vmMainAdmin.getStudentList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Students class' vmMainAdmin.getSetStudentsSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            progressBarStudent.setVisibility(View.VISIBLE);
            progressBarIndicator_isSemesterActive = false;
            progressBarIndicator_setStudents = false;
            if(toastMessage != null){
                toastMessage.cancel();
            }
            buttonSearchId.setVisibility(View.VISIBLE);
            buttonSearchName.setVisibility(View.VISIBLE);
            buttonSearchSurname.setVisibility(View.VISIBLE);
            buttonFilterDeptName.setVisibility(View.VISIBLE);
            buttonCancelSearchId.setVisibility(View.INVISIBLE);
            buttonCancelSearchName.setVisibility(View.INVISIBLE);
            buttonCancelSearchSurname.setVisibility(View.INVISIBLE);
            buttonCancelFilterDeptName.setVisibility(View.INVISIBLE);
            fragmentUserAndSemester.setName("ADMIN");
            vmMainAdmin.onLoadSemestersRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onResume method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' showToastMessage method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' goToDeptAdmins method.");
        }
    }

    public void goToDeptAdmins(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' goToStudents method.");
        }
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        // nothing to do
    }

    public void goToSemesters(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' goToSemesters method.");
        }
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            progressBarStudent.setVisibility(View.VISIBLE);
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmMainAdmin.onSemesterActiveRequested(selectedSemester);
            vmMainAdmin.onStudentListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onSemesterChanged method.");
        }
    }

    @Override
    public void onChangePasswordClicked() {
        try{
            editTextDialogChangePasswordOldPassword.setText(null);
            editTextDialogChangePasswordNewPassword.setText(null);
            editTextDialogChangePasswordNewPasswordConfirm.setText(null);
            editTextDialogChangePasswordOldPassword.clearFocus();
            editTextDialogChangePasswordNewPassword.clearFocus();
            editTextDialogChangePasswordNewPasswordConfirm.clearFocus();
            textViewDialogChangePassword.setVisibility(View.INVISIBLE);
            buttonDialogChangePasswordOK.setEnabled(false);
            alertDialogChangePassword.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onChangePasswordClicked method.");
        }
    }

    @Override
    public void onLogoutClicked() {
        try{
            fragmentUserAndSemester.setSpinnerItem(0);
            vmLoginProcess.onLogoutRequested();
            Intent i = new Intent(getApplicationContext(), Activity_Login_Page.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onLogoutClicked method.");
        }
    }

    @Override
    public void setAndShowWarningOnDialogChangePassword(int warning, int visibility) {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            textViewDialogChangePassword.setText(warning);
            textViewDialogChangePassword.setVisibility(visibility);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onDismissDialogRequested method.");
        }
    }
}