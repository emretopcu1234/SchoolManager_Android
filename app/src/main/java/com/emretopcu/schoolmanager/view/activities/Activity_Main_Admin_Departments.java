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
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentFilterType;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.enums.mainAdmin.E_Add_Or_Edit_Department_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Main_Admin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import javax.sql.CommonDataSource;

public class Activity_Main_Admin_Departments extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Main_Admin_Departments adapter;
    private RecyclerView recyclerViewMainAdminDepartments;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderDepartment;
    private View viewDialogDepartment;
    private AlertDialog alertDialogDepartment;

    private EditText editTextDialogDeptId;
    private EditText editTextDialogDeptName;
    private TextView textViewDialogWarning;
    private Button buttonDialogOK;
    private Button buttonDialogCancel;
    private ProgressBar progressBarDialog;

    private AlertDialog.Builder builderChangePassword;
    private View viewDialogChangePassword;
    private AlertDialog alertDialogChangePassword;

    private AlertDialog.Builder builderDeleteConfirmation;
    private View viewDialogDeleteConfirmation;
    private AlertDialog alertDialogDeleteConfirmation;

    private TextView textViewDeleteConfirmation;
    private Button buttonDeleteConfirmationYes;
    private Button buttonDeleteConfirmationNo;
    private ProgressBar progressBarDeleteConfirmation;

    private EditText editTextDialogChangePasswordOldPassword;
    private EditText editTextDialogChangePasswordNewPassword;
    private EditText editTextDialogChangePasswordNewPasswordConfirm;
    private TextView textViewDialogChangePassword;
    private Button buttonDialogChangePasswordOK;
    private Button buttonDialogChangePasswordCancel;
    private Helper_Dialog_Change_Password helperDialogChangePassword;

    private Button buttonAddDelete;
    private Button buttonSelectCancel;
    private Button buttonSearchDeptName;
    private Button buttonCancelSearchDeptName;

    private TextView textViewDeptName;
    private EditText editTextDeptName;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Main_Admin vmMainAdmin;

    private ProgressBar progressBarDepartment;
    private ProgressBar progressBarChangePassword;

    private boolean progressBarIndicator_isSemesterActive;
    private boolean progressBarIndicator_setDepartments;
    private boolean selectIndicator = true;
    private final ArrayList<Boolean> checks = new ArrayList<>();
    private final ArrayList<String> deletedIdList = new ArrayList<>();
    private boolean addRequested;
    private boolean semesterActive;

    private final DepartmentFilterType departmentFilter = new DepartmentFilterType();
    private final DepartmentAddOrEditType department = new DepartmentAddOrEditType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_departments);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_main_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewMainAdminDepartments = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewMainAdminDepartments.setLayoutManager(layoutManager);

            builderDepartment = new AlertDialog.Builder(this);
            viewDialogDepartment = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_departments, null);
            builderDepartment.setView(viewDialogDepartment);
            alertDialogDepartment = builderDepartment.create();
            alertDialogDepartment.setCancelable(false);
            alertDialogDepartment.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            editTextDialogDeptId = viewDialogDepartment.findViewById(R.id.editText_id);
            editTextDialogDeptName = viewDialogDepartment.findViewById(R.id.editText_name);
            textViewDialogWarning = viewDialogDepartment.findViewById(R.id.textView_warning);
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            progressBarDialog = viewDialogDepartment.findViewById(R.id.progressBar);
            buttonDialogOK = viewDialogDepartment.findViewById(R.id.button_ok);
            buttonDialogCancel = viewDialogDepartment.findViewById(R.id.button_cancel);

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
                        Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' watcherDialog onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(editTextDialogDeptId.getText().length() == 0 || editTextDialogDeptName.getText().length() == 0){
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                            buttonDialogOK.setEnabled(false);
                        }
                        else{
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            buttonDialogOK.setEnabled(true);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' watcherDialog afterTextChanged method.");
                    }
                }
            };
            editTextDialogDeptId.addTextChangedListener(watcherDialog);
            editTextDialogDeptName.addTextChangedListener(watcherDialog);

            buttonDialogOK.setOnClickListener(v -> {
                try{
                    progressBarDialog.setVisibility(View.VISIBLE);
                    department.setDeptName(editTextDialogDeptName.getText().toString());
                    department.setDeptId(editTextDialogDeptId.getText().toString());
                    department.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    if(addRequested){
                        vmMainAdmin.onAddDepartmentRequested(department);
                    }
                    else{
                        vmMainAdmin.onEditDepartmentRequested(department);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDialogOK setOnClickListener method.");
                }
            });
            buttonDialogCancel.setOnClickListener(v -> {
                try{
                    alertDialogDepartment.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDialogCancel setOnClickListener method.");
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
                    vmMainAdmin.onDeleteDepartmentsRequested(Common_Variables_View.SELECTED_SEMESTER, deletedIdList);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDeleteConfirmationYes setOnClickListener method.");
                }
            });
            buttonDeleteConfirmationNo.setOnClickListener(v -> {
                try{
                    alertDialogDeleteConfirmation.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDeleteConfirmationNo setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' watcherPassword onTextChanged method.");
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
                        Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' watcherPassword afterTextChanged method.");
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonDialogChangePasswordCancel setOnClickListener method.");
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
                        editTextDialogDeptName.setText(null);
                        editTextDialogDeptId.setText(null);
                        editTextDialogDeptId.setEnabled(true);
                        editTextDialogDeptName.clearFocus();
                        editTextDialogDeptId.clearFocus();
                        textViewDialogWarning.setVisibility(View.INVISIBLE);
                        buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        buttonDialogOK.setEnabled(false);
                        alertDialogDepartment.show();
                    }
                    else{
                        deletedIdList.clear();
                        for(int i=0;i<checks.size();i++){
                            if(checks.get(i)){
                                deletedIdList.add(vmMainAdmin.getDepartmentList().get(i)[1]);
                            }
                        }
                        textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_department));
                        alertDialogDeleteConfirmation.show();
                    }
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonAddDelete setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonSelectCancel setOnClickListener method.");
                }
            });

            buttonSearchDeptName = findViewById(R.id.button_search_dept_name);
            buttonSearchDeptName.setOnClickListener(v -> {
                try{
                    buttonSearchDeptName.setVisibility(View.INVISIBLE);
                    buttonCancelSearchDeptName.setVisibility(View.VISIBLE);
                    textViewDeptName.setVisibility(View.INVISIBLE);
                    editTextDeptName.setVisibility(View.VISIBLE);
                    editTextDeptName.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonSearchDeptName setOnClickListener method.");
                }
            });

            buttonCancelSearchDeptName = findViewById(R.id.button_cancel_search_dept_name);
            buttonCancelSearchDeptName.setOnClickListener(v -> {
                try{
                    buttonSearchDeptName.setVisibility(View.VISIBLE);
                    buttonCancelSearchDeptName.setVisibility(View.INVISIBLE);
                    textViewDeptName.setVisibility(View.VISIBLE);
                    editTextDeptName.setText(null);
                    editTextDeptName.setVisibility(View.INVISIBLE);
                    editTextDeptName.clearFocus();
                    progressBarDepartment.setVisibility(View.VISIBLE);
                    vmMainAdmin.onDepartmentListRequested(Common_Variables_View.SELECTED_SEMESTER);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' buttonCancelSearchDeptName setOnClickListener method.");
                }
            });

            textViewDeptName = findViewById(R.id.textView_dept_name);
            editTextDeptName = findViewById(R.id.editText_dept_name);
            editTextDeptName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        departmentFilter.setDeptNameFilter(s.toString());
                        if(departmentFilter.getDeptNameFilter().length() == 0){
                            if (buttonSearchDeptName.getVisibility() == View.INVISIBLE){
                                progressBarDepartment.setVisibility(View.VISIBLE);
                                vmMainAdmin.onDepartmentListRequested(Common_Variables_View.SELECTED_SEMESTER);
                            }
                        }
                        else{
                            progressBarDepartment.setVisibility(View.VISIBLE);
                            departmentFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                            vmMainAdmin.onFilteredDepartmentListRequested(departmentFilter);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' editTextDeptName onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_departments).setChecked(true);

            progressBarDepartment = findViewById(R.id.progressBar);

            vmLoginProcess = new ViewModelProvider(this).get(VM_Login_Process.class);
            vmLoginProcess.getChangePasswordSuccessful().observe(this, e_change_password_state -> {
                try{
                    if(e_change_password_state == E_Change_Password_State.SUCCESSFUL){
                        progressBarChangePassword.setVisibility(View.INVISIBLE);
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmMainAdmin.getIsSemesterActiveOrFutureSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_isSemesterActive = true;
                        if(progressBarIndicator_setDepartments){
                            progressBarDepartment.setVisibility(View.INVISIBLE);
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
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getIsSemesterActiveSuccessful().observe method.");
                }
            });
            vmMainAdmin.getSetDepartmentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setDepartments = true;
                        if(progressBarIndicator_isSemesterActive){
                            progressBarDepartment.setVisibility(View.INVISIBLE);
                        }
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this, vmMainAdmin.getDepartmentList());
                            recyclerViewMainAdminDepartments.setAdapter(adapter);
                        }
                        else{
                            adapter.setDepartmentList(vmMainAdmin.getDepartmentList());
                        }
                        adapter.setPopupMenuActive(semesterActive);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getSetDepartmentsSuccessful().observe method.");
                }
            });
            vmMainAdmin.getAddDepartmentSuccessful().observe(this, e_add_or_edit_department_state -> {
                try{
                    if(e_add_or_edit_department_state == E_Add_Or_Edit_Department_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogDepartment.dismiss();
                        showToastMessage(R.string.toast_add_department_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this, vmMainAdmin.getDepartmentList());
                            recyclerViewMainAdminDepartments.setAdapter(adapter);
                        }
                        else{
                            adapter.setDepartmentList(vmMainAdmin.getDepartmentList());
                        }
                        resetWidgets();
                    }
                    else if(e_add_or_edit_department_state == E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_ID){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_department_duplicated_id);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_department_state == E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_NAME){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_department_duplicated_name);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getAddDepartmentSuccessful().observe method.");
                }
            });
            vmMainAdmin.getEditDepartmentSuccessful().observe(this, e_add_or_edit_department_state -> {
                try{
                    if(e_add_or_edit_department_state == E_Add_Or_Edit_Department_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogDepartment.dismiss();
                        showToastMessage(R.string.toast_edit_department_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this, vmMainAdmin.getDepartmentList());
                            recyclerViewMainAdminDepartments.setAdapter(adapter);
                        }
                        else{
                            adapter.setDepartmentList(vmMainAdmin.getDepartmentList());
                        }
                        resetWidgets();
                    }
                    else if(e_add_or_edit_department_state == E_Add_Or_Edit_Department_State.UNSUCCESSFUL_DUPLICATED_NAME){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_department_duplicated_name);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getEditDepartmentSuccessful().observe method.");
                }
            });
            vmMainAdmin.getDeleteDepartmentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarDeleteConfirmation.setVisibility(View.INVISIBLE);
                        alertDialogDeleteConfirmation.dismiss();
                        showToastMessage(R.string.toast_delete_department_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this, vmMainAdmin.getDepartmentList());
                            recyclerViewMainAdminDepartments.setAdapter(adapter);
                        }
                        else{
                            adapter.setDepartmentList(vmMainAdmin.getDepartmentList());
                        }
                        resetWidgets();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' vmMainAdmin.getDeleteDepartmentsSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            resetWidgets();
            progressBarIndicator_isSemesterActive = false;
            progressBarIndicator_setDepartments = false;
            if(toastMessage != null){
                toastMessage.cancel();
            }
            fragmentUserAndSemester.setName("MAIN ADMIN");
            vmMainAdmin.onLoadSemestersRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onResume method.");
        }
    }

    private void resetWidgets(){
        try{
            progressBarDepartment.setVisibility(View.VISIBLE);
            buttonSelectCancel.setText(R.string.button_indicator_select);
            buttonAddDelete.setText(R.string.button_indicator_add);
            selectIndicator = true;
            if(adapter != null){    // uygulama ilk acildiginda henuz adapter set edilmemis oldugu icin
                adapter.setCheckBoxActive(false);
            }
            buttonSearchDeptName.setVisibility(View.VISIBLE);
            buttonCancelSearchDeptName.setVisibility(View.INVISIBLE);
            textViewDeptName.setVisibility(View.VISIBLE);
            editTextDeptName.setText(null);
            editTextDeptName.setVisibility(View.INVISIBLE);
            editTextDeptName.clearFocus();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' resetWidgets method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' showToastMessage method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onListItemClicked method.");
        }
    }

    public void onEditRequested(int position){
        try{
            addRequested = false;
            ArrayList<String[]> departmentList = vmMainAdmin.getDepartmentList();
            editTextDialogDeptName.setText(departmentList.get(position)[0]);
            editTextDialogDeptId.setText(departmentList.get(position)[1]);
            editTextDialogDeptId.setEnabled(false);
            editTextDialogDeptName.clearFocus();
            editTextDialogDeptId.clearFocus();
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            buttonDialogOK.setEnabled(true);
            alertDialogDepartment.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onEditRequested method.");
        }
    }

    public void onDeleteRequested(int position){
        try{
            deletedIdList.clear();
            deletedIdList.add(vmMainAdmin.getDepartmentList().get(position)[1]);
            textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_department));
            alertDialogDeleteConfirmation.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onDeleteRequested method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        // nothing to do
    }

    public void goToDeptAdmins(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' goToDeptAdmins method.");
        }
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' goToStudents method.");
        }
    }

    public void goToSemesters(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' goToSemesters method.");
        }
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            resetWidgets();
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmMainAdmin.onSemesterActiveOrFutureRequested(selectedSemester);
            vmMainAdmin.onDepartmentListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onSemesterChanged method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onDismissDialogRequested method.");
        }
    }
}