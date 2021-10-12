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
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Filter_Department;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Person_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity_Main_Admin_Dept_Admins extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Main_Admin_Dept_Admins adapter;
    private RecyclerView recyclerViewMainAdminDeptAdmins;
    private LinearLayoutManager layoutManager;

    private RecyclerViewAdapter_Filter_Department adapterFilter;
    private RecyclerView recyclerViewMainAdminDeptAdminsFilter;
    private LinearLayoutManager layoutManagerFilter;

    private AlertDialog.Builder builderDeptAdmin;
    private View viewDialogDeptAdmin;
    private AlertDialog alertDialogDeptAdmin;

    private EditText editTextDialogId;
    private EditText editTextDialogName;
    private EditText editTextDialogSurname;
    private Spinner spinnerDialogDept;
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

    private AlertDialog.Builder builderFilter;
    private View viewDialogFilter;
    private AlertDialog alertDialogFilter;

    private Button buttonFilterClear;
    private Button buttonFilterOK;
    private Button buttonFilterCancel;

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

    private TextView textViewId;
    private EditText editTextId;
    private TextView textViewName;
    private EditText editTextName;
    private TextView textViewSurname;
    private EditText editTextSurname;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Main_Admin vmMainAdmin;

    private ProgressBar progressBarDeptAdmin;
    private ProgressBar progressBarChangePassword;

    private boolean progressBarIndicator_isSemesterActive;
    private boolean progressBarIndicator_setDeptAdmins;
    private boolean progressBarIndicator_setDepartments;
    private String idFilter = "";
    private String nameFilter = "";
    private String surnameFilter = "";
    private ArrayList<String> deptFilter = new ArrayList<>();
    private ArrayList<Boolean> previousFilterChecks = new ArrayList<>();
    private boolean selectIndicator = true;
    private ArrayList<Boolean> checks = new ArrayList<>();
    private ArrayList<String> deletedIdList = new ArrayList<>();
    private boolean addRequested;
    private boolean semesterActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_dept_admins);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_main_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewMainAdminDeptAdmins = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewMainAdminDeptAdmins.setLayoutManager(layoutManager);

            builderDeptAdmin = new AlertDialog.Builder(this);
            viewDialogDeptAdmin = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_dept_admins, null);
            builderDeptAdmin.setView(viewDialogDeptAdmin);
            alertDialogDeptAdmin = builderDeptAdmin.create();
            alertDialogDeptAdmin.setCancelable(false);
            alertDialogDeptAdmin.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            editTextDialogId = viewDialogDeptAdmin.findViewById(R.id.editText_id);
            editTextDialogName = viewDialogDeptAdmin.findViewById(R.id.editText_name);
            editTextDialogSurname = viewDialogDeptAdmin.findViewById(R.id.editText_surname);
            spinnerDialogDept = viewDialogDeptAdmin.findViewById(R.id.spinner);
            textViewDialogWarning = viewDialogDeptAdmin.findViewById(R.id.textView_warning);
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            progressBarDialog = viewDialogDeptAdmin.findViewById(R.id.progressBar);
            buttonDialogOK = viewDialogDeptAdmin.findViewById(R.id.button_ok);
            buttonDialogCancel = viewDialogDeptAdmin.findViewById(R.id.button_cancel);

            TextWatcher watcherDialog = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        textViewDialogWarning.setVisibility(View.INVISIBLE);
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherDialog onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(editTextDialogId.getText().length() == 0 || editTextDialogName.getText().length() == 0
                                || editTextDialogSurname.getText().length() == 0){
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                            buttonDialogOK.setEnabled(false);
                        }
                        else{
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            buttonDialogOK.setEnabled(true);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherDialog afterTextChanged method.");
                    }
                }
            };
            editTextDialogId.addTextChangedListener(watcherDialog);
            editTextDialogName.addTextChangedListener(watcherDialog);
            editTextDialogSurname.addTextChangedListener(watcherDialog);

            buttonDialogOK.setOnClickListener(v -> {
                try{
                    if(editTextDialogId.getText().length() != 5){
                        textViewDialogWarning.setText(R.string.warning_dialog_id_length);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                        return;
                    }
                    progressBarDialog.setVisibility(View.VISIBLE);
                    if(addRequested){
                        vmMainAdmin.onAddDeptAdminRequested(editTextDialogId.getText().toString(),editTextDialogName.getText().toString(),
                                editTextDialogSurname.getText().toString(),spinnerDialogDept.getSelectedItem().toString(), Common_Variables_View.SELECTED_SEMESTER);
                    }
                    else{
                        vmMainAdmin.onEditDeptAdminRequested(editTextDialogId.getText().toString(),editTextDialogName.getText().toString(),
                                editTextDialogSurname.getText().toString(),spinnerDialogDept.getSelectedItem().toString(),Common_Variables_View.SELECTED_SEMESTER);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDialogOK setOnClickListener method.");
                }
            });
            buttonDialogCancel.setOnClickListener(v -> {
                try{
                    alertDialogDeptAdmin.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDialogCancel setOnClickListener method.");
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
                    vmMainAdmin.onDeleteDeptAdminsRequested(Common_Variables_View.SELECTED_SEMESTER, deletedIdList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDeleteConfirmationYes setOnClickListener method.");
                }
            });
            buttonDeleteConfirmationNo.setOnClickListener(v -> {
                try{
                    alertDialogDeleteConfirmation.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDeleteConfirmationNo setOnClickListener method.");
                }
            });

            builderFilter = new AlertDialog.Builder(this);
            viewDialogFilter = this.getLayoutInflater().inflate(R.layout.dialog_filter_department, null);
            builderFilter.setView(viewDialogFilter);
            alertDialogFilter = builderFilter.create();
            alertDialogFilter.setCancelable(false);
            alertDialogFilter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            recyclerViewMainAdminDeptAdminsFilter = viewDialogFilter.findViewById(R.id.recyclerView);
            layoutManagerFilter = new LinearLayoutManager(this);
            recyclerViewMainAdminDeptAdminsFilter.setLayoutManager(layoutManagerFilter);

            buttonFilterClear = viewDialogFilter.findViewById(R.id.button_clear);
            buttonFilterOK = viewDialogFilter.findViewById(R.id.button_ok);
            buttonFilterCancel = viewDialogFilter.findViewById(R.id.button_cancel);
            buttonFilterClear.setOnClickListener(v -> {
                try{
                    adapterFilter.resetChecks();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonFilterClear setOnClickListener method.");
                }
            });
            buttonFilterOK.setOnClickListener(v -> {
                try{
                    previousFilterChecks.clear();
                    for(int i=0;i<adapterFilter.getChecks().size();i++){
                        previousFilterChecks.add(adapterFilter.getChecks().get(i));
                    }
                    deptFilter = adapterFilter.getFilteredDepartmentList();
                    if(deptFilter.size() > 0){
                        buttonFilterDeptName.setVisibility(View.INVISIBLE);
                        buttonCancelFilterDeptName.setVisibility(View.VISIBLE);
                    }
                    else{
                        buttonFilterDeptName.setVisibility(View.VISIBLE);
                        buttonCancelFilterDeptName.setVisibility(View.INVISIBLE);
                    }
                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                    alertDialogFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonFilterOK setOnClickListener method.");
                }
            });
            buttonFilterCancel.setOnClickListener(v -> {
                try{
                    adapterFilter.setChecks(previousFilterChecks);
                    alertDialogFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonFilterCancel setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherPassword onTextChanged method.");
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
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherPassword afterTextChanged method.");
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonDialogChangePasswordCancel setOnClickListener method.");
                }
            });
            progressBarChangePassword = viewDialogChangePassword.findViewById(R.id.progressBar);
            helperDialogChangePassword = new Helper_Dialog_Change_Password(this);

            buttonAddDelete = findViewById(R.id.button_add_delete);
            buttonAddDelete.setVisibility(View.INVISIBLE);
            buttonAddDelete.setOnClickListener(v -> {
                try{
                    if(selectIndicator){
                        addRequested = true;
                        editTextDialogId.setEnabled(true);
                        editTextDialogId.setText(null);
                        editTextDialogName.setText(null);
                        editTextDialogSurname.setText(null);
                        ArrayList<String[]> departmentList = vmMainAdmin.getDepartmentList();
                        ArrayList<String> spinnerList = new ArrayList<>();
                        for(int i=0;i<departmentList.size();i++){
                            spinnerList.add(departmentList.get(i)[0]);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, spinnerList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerDialogDept.setAdapter(arrayAdapter);
                        editTextDialogId.clearFocus();
                        editTextDialogName.clearFocus();
                        editTextDialogSurname.clearFocus();
                        buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        buttonDialogOK.setEnabled(false);
                        alertDialogDeptAdmin.show();
                    }
                    else{
                        deletedIdList.clear();
                        for(int i=0;i<checks.size();i++){
                            if(checks.get(i)){
                                deletedIdList.add(vmMainAdmin.getDeptAdminList().get(i)[0]);
                            }
                        }
                        textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_dept_admin));
                        alertDialogDeleteConfirmation.show();
                    }
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonAddDelete setOnClickListener method.");
                }
            });

            buttonSelectCancel = findViewById(R.id.button_select_cancel);
            buttonSelectCancel.setVisibility(View.INVISIBLE);
            buttonSelectCancel.setOnClickListener(v -> {
                try{
                    selectIndicator = !selectIndicator;
                    adapter.setCheckBoxActive(!selectIndicator);
                    if(selectIndicator){
                        buttonSelectCancel.setText(R.string.button_indicator_select);
                        buttonAddDelete.setText(R.string.button_indicator_add);
                        buttonAddDelete.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        buttonAddDelete.setEnabled(true);
                    }
                    else{
                        buttonSelectCancel.setText(R.string.button_indicator_cancel);
                        buttonAddDelete.setText(R.string.button_indicator_delete);
                        buttonAddDelete.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        buttonAddDelete.setEnabled(false);
                        checks.clear();
                        for(int i=0;i<adapter.getItemCount();i++){
                            checks.add(false);
                        }
                        adapter.resetChecks();
                    }
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonSelectCancel setOnClickListener method.");
                }
            });

            buttonSearchId = findViewById(R.id.button_search_id);
            buttonSearchId.setOnClickListener(v -> {
                try{
                    buttonSearchId.setVisibility(View.INVISIBLE);
                    buttonCancelSearchId.setVisibility(View.VISIBLE);
                    textViewId.setVisibility(View.INVISIBLE);
                    editTextId.setVisibility(View.VISIBLE);
                    editTextId.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonSearchId setOnClickListener method.");
                }
            });

            buttonCancelSearchId = findViewById(R.id.button_cancel_search_id);
            buttonCancelSearchId.setOnClickListener(v -> {
                try{
                    buttonSearchId.setVisibility(View.VISIBLE);
                    buttonCancelSearchId.setVisibility(View.INVISIBLE);
                    textViewId.setVisibility(View.VISIBLE);
                    editTextId.setText(null);
                    editTextId.setVisibility(View.INVISIBLE);
                    editTextId.clearFocus();
                    progressBarDeptAdmin.setVisibility(View.VISIBLE);
                    idFilter = "";
                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonCancelSearchId setOnClickListener method.");
                }
            });

            buttonSearchName = findViewById(R.id.button_search_name);
            buttonSearchName.setOnClickListener(v -> {
                try{
                    buttonSearchName.setVisibility(View.INVISIBLE);
                    buttonCancelSearchName.setVisibility(View.VISIBLE);
                    textViewName.setVisibility(View.INVISIBLE);
                    editTextName.setVisibility(View.VISIBLE);
                    editTextName.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonSearchName setOnClickListener method.");
                }
            });

            buttonCancelSearchName = findViewById(R.id.button_cancel_search_name);
            buttonCancelSearchName.setOnClickListener(v -> {
                try{
                    buttonSearchName.setVisibility(View.VISIBLE);
                    buttonCancelSearchName.setVisibility(View.INVISIBLE);
                    textViewName.setVisibility(View.VISIBLE);
                    editTextName.setText(null);
                    editTextName.setVisibility(View.INVISIBLE);
                    editTextName.clearFocus();
                    progressBarDeptAdmin.setVisibility(View.VISIBLE);
                    nameFilter = "";
                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonCancelSearchName setOnClickListener method.");
                }
            });

            buttonSearchSurname = findViewById(R.id.button_search_surname);
            buttonSearchSurname.setOnClickListener(v -> {
                try{
                    buttonSearchSurname.setVisibility(View.INVISIBLE);
                    buttonCancelSearchSurname.setVisibility(View.VISIBLE);
                    textViewSurname.setVisibility(View.INVISIBLE);
                    editTextSurname.setVisibility(View.VISIBLE);
                    editTextSurname.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonSearchSurname setOnClickListener method.");
                }
            });

            buttonCancelSearchSurname = findViewById(R.id.button_cancel_search_surname);
            buttonCancelSearchSurname.setOnClickListener(v -> {
                try{
                    buttonSearchSurname.setVisibility(View.VISIBLE);
                    buttonCancelSearchSurname.setVisibility(View.INVISIBLE);
                    textViewSurname.setVisibility(View.VISIBLE);
                    editTextSurname.setText(null);
                    editTextSurname.setVisibility(View.INVISIBLE);
                    editTextSurname.clearFocus();
                    progressBarDeptAdmin.setVisibility(View.VISIBLE);
                    surnameFilter = "";
                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonCancelSearchSurname setOnClickListener method.");
                }
            });

            buttonFilterDeptName = findViewById(R.id.button_filter_empty_closed);
            buttonFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonFilterDeptName setOnClickListener method.");
                }
            });

            buttonCancelFilterDeptName = findViewById(R.id.button_filter_full_closed);
            buttonCancelFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' buttonCancelFilterDeptName setOnClickListener method.");
                }
            });

            textViewId = findViewById(R.id.textView_id);
            textViewName = findViewById(R.id.textView_name);
            textViewSurname = findViewById(R.id.textView_surname);
            editTextId = findViewById(R.id.editText_id);
            editTextName = findViewById(R.id.editText_name);
            editTextSurname = findViewById(R.id.editText_surname);

            TextWatcher watcherFilter = new TextWatcher() {
                String fieldValue;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        fieldValue = s.toString();
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherFilter onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(s.hashCode() == editTextId.getText().hashCode()){
                            idFilter = fieldValue;
                            if(idFilter.length() == 0){
                                if (buttonSearchId.getVisibility() == View.INVISIBLE){
                                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarDeptAdmin.setVisibility(View.VISIBLE);
                                vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                        else if(s.hashCode() == editTextName.getText().hashCode()){
                            nameFilter = fieldValue;
                            if(nameFilter.length() == 0){
                                if (buttonSearchName.getVisibility() == View.INVISIBLE){
                                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarDeptAdmin.setVisibility(View.VISIBLE);
                                vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                        else if(s.hashCode() == editTextSurname.getText().hashCode()){
                            surnameFilter = fieldValue;
                            if(surnameFilter.length() == 0){
                                if (buttonSearchSurname.getVisibility() == View.INVISIBLE){
                                    vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarDeptAdmin.setVisibility(View.VISIBLE);
                                vmMainAdmin.onFilteredDeptAdminListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' watcherFilter afterTextChanged method.");
                    }
                }
            };
            editTextId.addTextChangedListener(watcherFilter);
            editTextName.addTextChangedListener(watcherFilter);
            editTextSurname.addTextChangedListener(watcherFilter);

            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_dept_admins).setChecked(true);

            progressBarDeptAdmin = findViewById(R.id.progressBar);

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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmMainAdmin.getIsSemesterActiveOrFutureSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_isSemesterActive = true;
                        if(progressBarIndicator_setDeptAdmins && progressBarIndicator_setDepartments){
                            progressBarDeptAdmin.setVisibility(View.INVISIBLE);
                        }
                        if(vmMainAdmin.isSemesterActiveOrFuture()){
                            semesterActive = true;
                            buttonAddDelete.setVisibility(View.VISIBLE);
                            buttonSelectCancel.setVisibility(View.VISIBLE);
                        }
                        else{
                            semesterActive = false;
                            buttonAddDelete.setVisibility(View.INVISIBLE);
                            buttonSelectCancel.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getIsSemesterActiveSuccessful().observe method.");
                }
            });
            vmMainAdmin.getSetDeptAdminsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setDeptAdmins = true;
                        if(progressBarIndicator_isSemesterActive && progressBarIndicator_setDepartments){
                            progressBarDeptAdmin.setVisibility(View.INVISIBLE);
                        }
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this, vmMainAdmin.getDeptAdminList());
                            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);
                        }
                        else{
                            adapter.setDeptAdminList(vmMainAdmin.getDeptAdminList());
                        }
                        adapter.setPopupMenuActive(semesterActive);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getSetDeptAdminsSuccessful().observe method.");
                }
            });
            vmMainAdmin.getSetDepartmentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setDepartments = true;
                        if(progressBarIndicator_isSemesterActive && progressBarIndicator_setDeptAdmins){
                            progressBarDeptAdmin.setVisibility(View.INVISIBLE);
                        }
                        if(adapterFilter == null){
                            adapterFilter = new RecyclerViewAdapter_Filter_Department(this, vmMainAdmin.getDepartmentList());
                            recyclerViewMainAdminDeptAdminsFilter.setAdapter(adapterFilter);
                        }
                        else{
                            adapterFilter.setDepartmentList(vmMainAdmin.getDepartmentList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getSetDepartmentsSuccessful().observe method.");
                }
            });
            vmMainAdmin.getAddDeptAdminSuccessful().observe(this, e_add_or_edit_person_state -> {
                try{
                    if(e_add_or_edit_person_state == E_Add_Or_Edit_Person_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogDeptAdmin.dismiss();
                        showToastMessage(R.string.toast_add_dept_admin_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this, vmMainAdmin.getDeptAdminList());
                            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);
                        }
                        else{
                            adapter.setDeptAdminList(vmMainAdmin.getDeptAdminList());
                        }
                        resetWidgets();
                    }
                    else if(e_add_or_edit_person_state == E_Add_Or_Edit_Person_State.UNSUCCESSFUL_DUPLICATED_ID){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_dept_admin_duplicated_id);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getAddDeptAdminSuccessful().observe method.");
                }
            });
            vmMainAdmin.getEditDeptAdminSuccessful().observe(this, e_add_or_edit_person_state -> {
                try{
                    if(e_add_or_edit_person_state == E_Add_Or_Edit_Person_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogDeptAdmin.dismiss();
                        showToastMessage(R.string.toast_edit_dept_admin_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this, vmMainAdmin.getDeptAdminList());
                            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);
                        }
                        else{
                            adapter.setDeptAdminList(vmMainAdmin.getDeptAdminList());
                        }
                        resetWidgets();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getEditDeptAdminSuccessful().observe method.");
                }
            });
            vmMainAdmin.getDeleteDeptAdminsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarDeleteConfirmation.setVisibility(View.INVISIBLE);
                        alertDialogDeleteConfirmation.dismiss();
                        showToastMessage(R.string.toast_delete_dept_admin_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this, vmMainAdmin.getDeptAdminList());
                            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);
                        }
                        else{
                            adapter.setDeptAdminList(vmMainAdmin.getDeptAdminList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' vmMainAdmin.getDeleteDeptAdminsSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            resetWidgets();
            progressBarIndicator_isSemesterActive = false;
            progressBarIndicator_setDeptAdmins = false;
            if(toastMessage != null){
                toastMessage.cancel();
            }
            fragmentUserAndSemester.setName("MAIN ADMIN");
            vmMainAdmin.onLoadSemestersRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onResume method.");
        }
    }

    private void resetWidgets(){
        try{
            progressBarDeptAdmin.setVisibility(View.VISIBLE);
            buttonSelectCancel.setText(R.string.button_indicator_select);
            buttonAddDelete.setText(R.string.button_indicator_add);
            selectIndicator = true;
            if(adapter != null){    // uygulama ilk acildiginda henuz adapter set edilmemis oldugu icin
                adapter.setCheckBoxActive(false);
            }
            buttonSearchId.setVisibility(View.VISIBLE);
            buttonCancelSearchId.setVisibility(View.INVISIBLE);
            buttonSearchName.setVisibility(View.VISIBLE);
            buttonCancelSearchName.setVisibility(View.INVISIBLE);
            buttonSearchSurname.setVisibility(View.VISIBLE);
            buttonCancelSearchSurname.setVisibility(View.INVISIBLE);
            buttonFilterDeptName.setVisibility(View.VISIBLE);
            buttonCancelFilterDeptName.setVisibility(View.INVISIBLE);
            textViewId.setVisibility(View.VISIBLE);
            editTextId.setText(null);
            editTextId.setVisibility(View.INVISIBLE);
            editTextId.clearFocus();
            textViewName.setVisibility(View.VISIBLE);
            editTextName.setText(null);
            editTextName.setVisibility(View.INVISIBLE);
            editTextName.clearFocus();
            textViewSurname.setVisibility(View.VISIBLE);
            editTextSurname.setText(null);
            editTextSurname.setVisibility(View.INVISIBLE);
            editTextSurname.clearFocus();

            idFilter = "";
            nameFilter = "";
            surnameFilter = "";
            deptFilter.clear();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' resetWidgets method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' showToastMessage method.");
        }
    }

    public void onListItemClicked(int position, boolean isChecked){
        try{
            checks.set(position,isChecked);
            if(checks.contains(true)){
                buttonAddDelete.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                buttonAddDelete.setEnabled(true);
            }
            else{
                buttonAddDelete.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                buttonAddDelete.setEnabled(false);
            }
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onListItemClicked method.");
        }
    }

    public void onEditRequested(int position){
        try{
            addRequested = false;
            ArrayList<String[]> deptAdminList = vmMainAdmin.getDeptAdminList();
            editTextDialogId.setEnabled(false);
            editTextDialogId.setText(deptAdminList.get(position)[0]);
            editTextDialogName.setText(deptAdminList.get(position)[1]);
            editTextDialogSurname.setText(deptAdminList.get(position)[2]);
            HashMap<String,String> departmentIdInfo = vmMainAdmin.getDepartmentIdInfo();
            ArrayList<String[]> departmentList = vmMainAdmin.getDepartmentList();
            ArrayList<String> spinnerList = new ArrayList<>();
            for(int i=0;i<departmentList.size();i++){
                spinnerList.add(departmentList.get(i)[0]);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, spinnerList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDialogDept.setAdapter(arrayAdapter);
            String department = "";
            for (String key : departmentIdInfo.keySet() ) {
                if(deptAdminList.get(position)[3].equals(key)){
                    department = departmentIdInfo.get(key);
                    break;
                }
            }
            for(int i=0;i<spinnerList.size();i++){
                if(department.equals(spinnerList.get(i))){
                    spinnerDialogDept.setSelection(i);
                    break;
                }
            }
            editTextDialogId.clearFocus();
            editTextDialogName.clearFocus();
            editTextDialogSurname.clearFocus();
            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            buttonDialogOK.setEnabled(true);
            alertDialogDeptAdmin.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onEditRequested method.");
        }
    }

    public void onDeleteRequested(int position){
        try{
            deletedIdList.clear();
            deletedIdList.add(vmMainAdmin.getDeptAdminList().get(position)[0]);
            textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_dept_admin));
            alertDialogDeleteConfirmation.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onDeleteRequested method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' goToDeptAdmins method.");
        }
    }

    public void goToDeptAdmins(MenuItem item) {
        // nothing to do
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' goToStudents method.");
        }
    }

    public void goToSemesters(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' goToSemesters method.");
        }
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            resetWidgets();
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmMainAdmin.onSemesterActiveOrFutureRequested(selectedSemester);
            vmMainAdmin.onDeptAdminListRequested(selectedSemester);
            vmMainAdmin.onDepartmentListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onSemesterChanged method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onDismissDialogRequested method.");
        }
    }
}