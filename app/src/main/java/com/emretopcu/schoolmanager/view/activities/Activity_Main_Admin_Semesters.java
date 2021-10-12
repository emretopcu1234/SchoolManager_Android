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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Semesters;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Semester_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Activity_Main_Admin_Semesters extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Main_Admin_Semesters adapter;
    private RecyclerView recyclerViewMainAdminSemesters;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderSemester;
    private View viewDialogSemester;
    private AlertDialog alertDialogSemester;

    private EditText editTextDialogStartDate;
    private EditText editTextDialogEndDate;
    private EditText editTextDialogSemester;
    private TextView textViewDialogWarning;
    private Button buttonDialogOK;
    private Button buttonDialogCancel;
    private ProgressBar progressBarDialog;

    private AlertDialog.Builder builderDeleteConfirmation;
    private View viewDialogDeleteConfirmation;
    private AlertDialog alertDialogDeleteConfirmation;

    private TextView textViewDeleteConfirmation;
    private Button buttonDeleteConfirmationYes;
    private Button buttonDeleteConfirmationNo;
    private ProgressBar progressBarDeleteConfirmation;

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

    private Button buttonAdd;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Main_Admin vmMainAdmin;

    private ProgressBar progressBarSemester;
    private ProgressBar progressBarChangePassword;

    private boolean addRequested;
    private String deletedSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_semesters);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_main_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewMainAdminSemesters = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewMainAdminSemesters.setLayoutManager(layoutManager);

            builderSemester = new AlertDialog.Builder(this);
            viewDialogSemester = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_semesters, null);
            builderSemester.setView(viewDialogSemester);
            alertDialogSemester = builderSemester.create();
            alertDialogSemester.setCancelable(false);
            alertDialogSemester.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            editTextDialogStartDate = viewDialogSemester.findViewById(R.id.editText_start);
            editTextDialogEndDate = viewDialogSemester.findViewById(R.id.editText_end);
            editTextDialogSemester = viewDialogSemester.findViewById(R.id.editText_semester);
            editTextDialogSemester.setEnabled(false);
            textViewDialogWarning = viewDialogSemester.findViewById(R.id.textView_warning);
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            progressBarDialog = viewDialogSemester.findViewById(R.id.progressBar);
            buttonDialogOK = viewDialogSemester.findViewById(R.id.button_ok);
            buttonDialogCancel = viewDialogSemester.findViewById(R.id.button_cancel);

            TextWatcher watcherDialog = new TextWatcher() {
                String previousTextStart;
                String previousTextEnd;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    try{
                        previousTextStart = editTextDialogStartDate.getText().toString();
                        previousTextEnd = editTextDialogEndDate.getText().toString();
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' watcherDialog beforeTextChanged method.");
                    }
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        textViewDialogWarning.setVisibility(View.INVISIBLE);
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' watcherDialog onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        int textLength = -1;
                        StringBuilder textBuilder;
                        if(s.hashCode() == editTextDialogStartDate.getText().hashCode()){
                            textBuilder = new StringBuilder(editTextDialogStartDate.getText().toString());
                            textLength = editTextDialogStartDate.getText().length();
                            if((textLength == 2 || textLength == 7) && (previousTextStart.length() == 1 || previousTextStart.length() == 6)){
                                textBuilder.append(" / ");
                                editTextDialogStartDate.setText(textBuilder.toString());
                                editTextDialogStartDate.setSelection(editTextDialogStartDate.getText().length());
                            }
                            else if((textLength == 5 || textLength == 10) && (previousTextStart.length() == 6 || previousTextStart.length() == 11)){
                                String text = editTextDialogStartDate.getText().toString();
                                editTextDialogStartDate.setText(text.substring(0, text.length() - 3));
                                editTextDialogStartDate.setSelection(editTextDialogStartDate.getText().length());
                            }
                            else if(textLength == 3 || textLength == 8){
                                char c = editTextDialogStartDate.getText().charAt(textLength - 1);
                                textBuilder = new StringBuilder(previousTextStart).append(" / ").append(c);
                                editTextDialogStartDate.setText(textBuilder.toString());
                                editTextDialogStartDate.setSelection(editTextDialogStartDate.getText().length());
                            }
                            else if(textLength == 4 || textLength == 9){
                                String text = editTextDialogStartDate.getText().toString();
                                editTextDialogStartDate.setText(text.substring(0, text.length() - 2));
                                editTextDialogStartDate.setSelection(editTextDialogStartDate.getText().length());
                            }
                        }
                        else if(s.hashCode() == editTextDialogEndDate.getText().hashCode()){
                            textBuilder = new StringBuilder(editTextDialogEndDate.getText().toString());
                            textLength = editTextDialogEndDate.getText().length();
                            if((textLength == 2 || textLength == 7) && (previousTextEnd.length() == 1 || previousTextEnd.length() == 6)){
                                textBuilder.append(" / ");
                                editTextDialogEndDate.setText(textBuilder.toString());
                                editTextDialogEndDate.setSelection(editTextDialogEndDate.getText().length());
                            }
                            else if((textLength == 5 || textLength == 10) && (previousTextEnd.length() == 6 || previousTextEnd.length() == 11)){
                                String text = editTextDialogEndDate.getText().toString();
                                editTextDialogEndDate.setText(text.substring(0, text.length() - 3));
                                editTextDialogEndDate.setSelection(editTextDialogEndDate.getText().length());
                            }
                            else if(textLength == 3 || textLength == 8){
                                char c = editTextDialogEndDate.getText().charAt(textLength - 1);
                                textBuilder = new StringBuilder(previousTextEnd).append(" / ").append(c);
                                editTextDialogEndDate.setText(textBuilder.toString());
                                editTextDialogEndDate.setSelection(editTextDialogEndDate.getText().length());
                            }
                            else if(textLength == 4 || textLength == 9){
                                String text = editTextDialogEndDate.getText().toString();
                                editTextDialogEndDate.setText(text.substring(0, text.length() - 2));
                                editTextDialogEndDate.setSelection(editTextDialogEndDate.getText().length());
                            }
                        }
                        if(editTextDialogStartDate.getText().length() == 14 && editTextDialogEndDate.getText().length() == 14){
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            buttonDialogOK.setEnabled(true);
                        }
                        else{
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                            buttonDialogOK.setEnabled(false);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' watcherDialog afterTextChanged method.");
                    }
                }
            };
            editTextDialogStartDate.addTextChangedListener(watcherDialog);
            editTextDialogEndDate.addTextChangedListener(watcherDialog);

            buttonDialogOK.setOnClickListener(v -> {
                try{
                    progressBarDialog.setVisibility(View.VISIBLE);
                    if(addRequested){
                        vmMainAdmin.onAddSemesterRequested(editTextDialogStartDate.getText().toString(),editTextDialogEndDate.getText().toString());
                    }
                    else{
                        vmMainAdmin.onEditSemesterRequested(editTextDialogStartDate.getText().toString(),editTextDialogEndDate.getText().toString());
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDialogOK setOnClickListener method.");
                }
            });
            buttonDialogCancel.setOnClickListener(v -> {
                try{
                    alertDialogSemester.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDialogCancel setOnClickListener method.");
                }
            });

            builderDeleteConfirmation = new AlertDialog.Builder(this);
            viewDialogDeleteConfirmation = this.getLayoutInflater().inflate(R.layout.dialog_delete_confirmation, null);
            builderDeleteConfirmation.setView(viewDialogDeleteConfirmation);
            alertDialogDeleteConfirmation = builderDeleteConfirmation.create();
            alertDialogDeleteConfirmation.setCancelable(false);
            alertDialogDeleteConfirmation.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            textViewDeleteConfirmation = viewDialogDeleteConfirmation.findViewById(R.id.textView_confirmation);
            progressBarDeleteConfirmation = viewDialogDeleteConfirmation.findViewById(R.id.progressBar);
            buttonDeleteConfirmationYes = viewDialogDeleteConfirmation.findViewById(R.id.button_yes);
            buttonDeleteConfirmationNo = viewDialogDeleteConfirmation.findViewById(R.id.button_no);
            buttonDeleteConfirmationYes.setOnClickListener(v -> {
                try{
                    progressBarDeleteConfirmation.setVisibility(View.VISIBLE);
                    vmMainAdmin.onDeleteSemesterRequested(deletedSemester);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDeleteConfirmationYes setOnClickListener method.");
                }
            });
            buttonDeleteConfirmationNo.setOnClickListener(v -> {
                try{
                    alertDialogDeleteConfirmation.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDeleteConfirmationNo setOnClickListener method.");
                }
            });

            builderChangePassword = new AlertDialog.Builder(this);
            viewDialogChangePassword = this.getLayoutInflater().inflate(R.layout.dialog_change_password, null);
            builderChangePassword.setView(viewDialogChangePassword);
            alertDialogChangePassword = builderChangePassword.create();
            alertDialogChangePassword.setCancelable(false);
            alertDialogChangePassword.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            editTextDialogChangePasswordOldPassword = viewDialogChangePassword.findViewById(R.id.editText_old_password);
            editTextDialogChangePasswordNewPassword = viewDialogChangePassword.findViewById(R.id.editText_new_password);
            editTextDialogChangePasswordNewPasswordConfirm = viewDialogChangePassword.findViewById(R.id.editText_new_password_confirm);

            TextWatcher watcherPassword = new TextWatcher() {
                String fieldValue;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        fieldValue = s.toString();
                        textViewDialogChangePassword.setVisibility(View.INVISIBLE);
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' watcherPassword onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(s.hashCode() == editTextDialogChangePasswordOldPassword.getText().hashCode()){
                            buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(0,fieldValue));
                        }
                        else if (s.hashCode() == editTextDialogChangePasswordNewPassword.getText().hashCode()){
                            buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(1,fieldValue));
                        }
                        else if (s.hashCode() == editTextDialogChangePasswordNewPasswordConfirm.getText().hashCode()){
                            buttonDialogChangePasswordOK.setEnabled(helperDialogChangePassword.onFieldChanged(2,fieldValue));
                        }
                        if(buttonDialogChangePasswordOK.isEnabled()){
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        }
                        else{
                            buttonDialogChangePasswordOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' watcherPassword afterTextChanged method.");
                    }
                }
            };
            editTextDialogChangePasswordOldPassword.addTextChangedListener(watcherPassword);
            editTextDialogChangePasswordNewPassword.addTextChangedListener(watcherPassword);
            editTextDialogChangePasswordNewPasswordConfirm.addTextChangedListener(watcherPassword);

            textViewDialogChangePassword = viewDialogChangePassword.findViewById(R.id.textView_warning);
            buttonDialogChangePasswordOK = viewDialogChangePassword.findViewById(R.id.button_ok);
            buttonDialogChangePasswordOK.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onOKClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonDialogChangePasswordCancel setOnClickListener method.");
                }
            });
            progressBarChangePassword = viewDialogChangePassword.findViewById(R.id.progressBar);
            helperDialogChangePassword = new Helper_Dialog_Change_Password(this);

            buttonAdd = findViewById(R.id.button_add);
            buttonAdd.setOnClickListener(v -> {
                try{
                    addRequested = true;
                    editTextDialogSemester.setText(null);
                    editTextDialogStartDate.setText(null);
                    editTextDialogEndDate.setText(null);
                    editTextDialogStartDate.clearFocus();
                    editTextDialogEndDate.clearFocus();
                    textViewDialogWarning.setVisibility(View.INVISIBLE);
                    buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                    buttonDialogOK.setEnabled(false);
                    alertDialogSemester.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' buttonAdd setOnClickListener method.");
                }
            });

            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_semesters).setChecked(true);

            progressBarSemester = findViewById(R.id.progressBar);

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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
                }
            });

            vmMainAdmin = new ViewModelProvider(this).get(VM_Main_Admin.class);
            vmMainAdmin.getSetDetailedSemestersSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarSemester.setVisibility(View.INVISIBLE);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Semesters(this, vmMainAdmin.getDetailedSemesterList());
                            recyclerViewMainAdminSemesters.setAdapter(adapter);
                        }
                        else{
                            adapter.setDetailedSemesterList(vmMainAdmin.getDetailedSemesterList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' vmMainAdmin.getSetDetailedSemestersSuccessful().observe method.");
                }
            });
            vmMainAdmin.getAddSemesterSuccessful().observe(this, e_add_or_edit_semester_state -> {
                try{
                    if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogSemester.dismiss();
                        showToastMessage(R.string.toast_add_semester_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Semesters(this, vmMainAdmin.getDetailedSemesterList());
                            recyclerViewMainAdminSemesters.setAdapter(adapter);
                        }
                        else{
                            adapter.setDetailedSemesterList(vmMainAdmin.getDetailedSemesterList());
                        }
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_REVERSE_ORDER){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_reverse_order);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_LOW_DIFF){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_low_difference);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_HIGH_DIFF){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_high_difference);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' vmMainAdmin.getAddSemesterSuccessful().observe method.");
                }
            });
            vmMainAdmin.getEditSemesterSuccessful().observe(this, e_add_or_edit_semester_state -> {
                try{
                    if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogSemester.dismiss();
                        showToastMessage(R.string.toast_edit_semester_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Semesters(this, vmMainAdmin.getDetailedSemesterList());
                            recyclerViewMainAdminSemesters.setAdapter(adapter);
                        }
                        else{
                            adapter.setDetailedSemesterList(vmMainAdmin.getDetailedSemesterList());
                        }
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_REVERSE_ORDER){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_reverse_order);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_LOW_DIFF){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_low_difference);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_semester_state == E_Add_Or_Edit_Semester_State.UNSUCCESSFUL_HIGH_DIFF){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_semester_high_difference);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' vmMainAdmin.getEditSemesterSuccessful().observe method.");
                }
            });
            vmMainAdmin.getDeleteSemesterSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarDeleteConfirmation.setVisibility(View.INVISIBLE);
                        alertDialogDeleteConfirmation.dismiss();
                        showToastMessage(R.string.toast_delete_semester_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Semesters(this, vmMainAdmin.getDetailedSemesterList());
                            recyclerViewMainAdminSemesters.setAdapter(adapter);
                        }
                        else{
                            adapter.setDetailedSemesterList(vmMainAdmin.getDetailedSemesterList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' vmMainAdmin.getDeleteSemesterSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            progressBarSemester.setVisibility(View.VISIBLE);
            if(toastMessage != null){
                toastMessage.cancel();
            }
            fragmentUserAndSemester.setName("MAIN ADMIN");
            fragmentUserAndSemester.setSpinnerVisibility(View.INVISIBLE);
            vmMainAdmin.onDetailedSemesterListRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onResume method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' showToastMessage method.");
        }
    }

    public void onEditRequested(int position){
        try{
            addRequested = false;
            ArrayList<String[]> semesterList = vmMainAdmin.getDetailedSemesterList();
            editTextDialogSemester.setText(semesterList.get(position)[0]);
            editTextDialogStartDate.setText(semesterList.get(position)[1]);
            editTextDialogEndDate.setText(semesterList.get(position)[2]);
            editTextDialogStartDate.clearFocus();
            editTextDialogEndDate.clearFocus();
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            buttonDialogOK.setEnabled(true);
            alertDialogSemester.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onEditRequested method.");
        }
    }

    public void onDeleteRequested(int position){
        try{
            deletedSemester = vmMainAdmin.getSemesterList().get(position);
            textViewDeleteConfirmation.setText(getResources().getString(R.string.delete_confirmation_semester));
            alertDialogDeleteConfirmation.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onDeleteRequested method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' goToDeptAdmins method.");
        }
    }

    public void goToDeptAdmins(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' goToSemesters method.");
        }
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' goToStudents method.");
        }
    }

    public void goToSemesters(MenuItem item) {
        // nothing to do
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        // nothing to do, because spinner is invisible.
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onDismissDialogRequested method.");
        }
    }
}