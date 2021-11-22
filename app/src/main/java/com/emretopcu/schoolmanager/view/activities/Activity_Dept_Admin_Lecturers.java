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
import com.emretopcu.schoolmanager.commonObjectTypes.PersonFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Dept_Admin;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Dept_Admin_Lecturers extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Dept_Admin_Lecturers adapter;
    private RecyclerView recyclerViewDeptAdminLecturers;
    private LinearLayoutManager layoutManager;

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

    private Button buttonSearchId;
    private Button buttonCancelSearchId;
    private Button buttonSearchName;
    private Button buttonCancelSearchName;
    private Button buttonSearchSurname;
    private Button buttonCancelSearchSurname;

    private TextView textViewId;
    private EditText editTextId;
    private TextView textViewName;
    private EditText editTextName;
    private TextView textViewSurname;
    private EditText editTextSurname;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Dept_Admin vmDeptAdmin;

    private ProgressBar progressBarLecturer;
    private ProgressBar progressBarChangePassword;

    private PersonType deptAdminInfo = new PersonType();
    private final PersonFilterType personFilter = new PersonFilterType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_lecturers);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_dept_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewDeptAdminLecturers = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewDeptAdminLecturers.setLayoutManager(layoutManager);

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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' watcherPassword onTextChanged method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' watcherPassword afterTextChanged method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonDialogChangePasswordCancel setOnClickListener method.");
                }
            });
            progressBarChangePassword = viewDialogChangePassword.findViewById(R.id.progressBar);
            helperDialogChangePassword = new Helper_Dialog_Change_Password(this);

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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonSearchId setOnClickListener method.");
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
                    progressBarLecturer.setVisibility(View.VISIBLE);
                    personFilter.setIdFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonCancelSearchId setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonSearchName setOnClickListener method.");
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
                    progressBarLecturer.setVisibility(View.VISIBLE);
                    personFilter.setNameFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonCancelSearchName setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonSearchSurname setOnClickListener method.");
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
                    progressBarLecturer.setVisibility(View.VISIBLE);
                    personFilter.setSurnameFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' buttonCancelSearchSurname setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' watcherFilter onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(s.hashCode() == editTextId.getText().hashCode()){
                            personFilter.setIdFilter(fieldValue);
                            if(personFilter.getIdFilter().length() == 0){
                                if (buttonSearchId.getVisibility() == View.INVISIBLE){
                                    progressBarLecturer.setVisibility(View.VISIBLE);
                                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                                }
                            }
                            else{
                                progressBarLecturer.setVisibility(View.VISIBLE);
                                personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                            }
                        }
                        else if(s.hashCode() == editTextName.getText().hashCode()){
                            personFilter.setNameFilter(fieldValue);
                            if(personFilter.getNameFilter().length() == 0){
                                if (buttonSearchName.getVisibility() == View.INVISIBLE){
                                    progressBarLecturer.setVisibility(View.VISIBLE);
                                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                                }
                            }
                            else{
                                progressBarLecturer.setVisibility(View.VISIBLE);
                                personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                            }
                        }
                        else if(s.hashCode() == editTextSurname.getText().hashCode()){
                            personFilter.setSurnameFilter(fieldValue);
                            if(personFilter.getSurnameFilter().length() == 0){
                                if (buttonSearchSurname.getVisibility() == View.INVISIBLE){
                                    progressBarLecturer.setVisibility(View.VISIBLE);
                                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                    vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                                }
                            }
                            else{
                                progressBarLecturer.setVisibility(View.VISIBLE);
                                personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                vmDeptAdmin.onFilteredLecturerListRequested(personFilter);
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' watcherFilter afterTextChanged method.");
                    }
                }
            };
            editTextId.addTextChangedListener(watcherFilter);
            editTextName.addTextChangedListener(watcherFilter);
            editTextSurname.addTextChangedListener(watcherFilter);

            bottomNavigationView = findViewById(R.id.bottom_navigation_dept_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_dept_admin_lecturers).setChecked(true);

            progressBarLecturer = findViewById(R.id.progressBar);

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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
                }
            });

            vmDeptAdmin = new ViewModelProvider(this).get(VM_Dept_Admin.class);
            vmDeptAdmin.getPersonInfoSuccessful().observe(this,e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        deptAdminInfo = vmDeptAdmin.getDeptAdminInfo();
                        fragmentUserAndSemester.setName(deptAdminInfo.getName() + " " + deptAdminInfo.getSurname());
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' vmDeptAdmin.getPersonInfoSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetSemestersSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        fragmentUserAndSemester.setSpinnerList(vmDeptAdmin.getSemesterList());
                        fragmentUserAndSemester.setSpinnerItem(Common_Variables_View.SEMESTER_SPINNER_POSITION);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' vmDeptAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetLecturersSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarLecturer.setVisibility(View.INVISIBLE);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Lecturers(this, vmDeptAdmin.getLecturerList());
                            recyclerViewDeptAdminLecturers.setAdapter(adapter);
                        }
                        else{
                            adapter.setLecturerList(vmDeptAdmin.getLecturerList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' vmDeptAdmin.getSetLecturersSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            resetWidgets();
            if(toastMessage != null){
                toastMessage.cancel();
            }
            vmDeptAdmin.onLoadSemestersRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onResume method.");
        }
    }

    private void resetWidgets(){
        try{
            progressBarLecturer.setVisibility(View.VISIBLE);
            buttonSearchId.setVisibility(View.VISIBLE);
            buttonCancelSearchId.setVisibility(View.INVISIBLE);
            buttonSearchName.setVisibility(View.VISIBLE);
            buttonCancelSearchName.setVisibility(View.INVISIBLE);
            buttonSearchSurname.setVisibility(View.VISIBLE);
            buttonCancelSearchSurname.setVisibility(View.INVISIBLE);
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
            personFilter.setIdFilter("");
            personFilter.setNameFilter("");
            personFilter.setSurnameFilter("");
            personFilter.setDeptFilter(new ArrayList<>());
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' resetWidgets method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' showToastMessage method.");
        }
    }

    public void goToCourses(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Courses.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' goToCourses method.");
        }
    }

    public void goToLecturers(MenuItem item) {
        // nothing to do
    }

    public void goToStudents(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Students.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' goToStudents method.");
        }
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            resetWidgets();
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmDeptAdmin.onPersonInfoRequested(selectedSemester);
            vmDeptAdmin.onLecturerListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onSemesterChanged method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onDismissDialogRequested method.");
        }
    }
}