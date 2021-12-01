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
import com.emretopcu.schoolmanager.commonObjectTypes.CourseAddOrEditType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseDeleteType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseFilterType;
import com.emretopcu.schoolmanager.commonObjectTypes.CourseType;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Courses;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.deptAdmin.E_Add_Or_Edit_Course_State;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Dept_Admin;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Dept_Admin_Courses extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Dept_Admin_Courses adapter;
    private RecyclerView recyclerViewDeptAdminCourses;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderCourse;
    private View viewDialogCourse;
    private AlertDialog alertDialogCourse;

    private EditText editTextDialogCourseId;
    private EditText editTextDialogCourseName;
    private EditText editTextDialogSections;
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
    private Button buttonSearchCourseId;
    private Button buttonCancelSearchCourseId;
    private Button buttonSearchCourseName;
    private Button buttonCancelSearchCourseName;

    private TextView textViewCourseId;
    private EditText editTextCourseId;
    private TextView textViewCourseName;
    private EditText editTextCourseName;

    private BottomNavigationView bottomNavigationView;
    private Toast toastMessage;

    private VM_Login_Process vmLoginProcess;
    private VM_Dept_Admin vmDeptAdmin;

    private ProgressBar progressBarCourse;
    private ProgressBar progressBarChangePassword;

    private boolean progressBarIndicator_isSemesterActive;
    private boolean progressBarIndicator_setCourses;
    private ArrayList<Boolean> previousFilterChecks = new ArrayList<>();
    private boolean selectIndicator = true;
    private ArrayList<Boolean> checks = new ArrayList<>();
    private ArrayList<String> deletedIdList = new ArrayList<>();
    private boolean addRequested;
    private boolean semesterActive;

    private PersonType deptAdminInfo = new PersonType();
    private final CourseFilterType courseFilter = new CourseFilterType();
    private final CourseAddOrEditType courseInfo = new CourseAddOrEditType();
    private final CourseDeleteType deletedCourses = new CourseDeleteType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_courses);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_dept_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewDeptAdminCourses = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewDeptAdminCourses.setLayoutManager(layoutManager);

            builderCourse = new AlertDialog.Builder(this);
            viewDialogCourse = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_courses, null);
            builderCourse.setView(viewDialogCourse);
            alertDialogCourse = builderCourse.create();
            alertDialogCourse.setCancelable(false);
            alertDialogCourse.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            editTextDialogCourseId = viewDialogCourse.findViewById(R.id.editText_id);
            editTextDialogCourseName = viewDialogCourse.findViewById(R.id.editText_name);
            editTextDialogSections = viewDialogCourse.findViewById(R.id.editText_sections);
            textViewDialogWarning = viewDialogCourse.findViewById(R.id.textView_warning);
            textViewDialogWarning.setVisibility(View.INVISIBLE);
            progressBarDialog = viewDialogCourse.findViewById(R.id.progressBar);
            buttonDialogOK = viewDialogCourse.findViewById(R.id.button_ok);
            buttonDialogCancel = viewDialogCourse.findViewById(R.id.button_cancel);

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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherDialog onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        // IE ise 6, METE ise 8 vb. çünkü IE 205, METE 220...
                        if(editTextDialogCourseId.getText().length() < vmDeptAdmin.getDeptAdminInfo().getDeptId().length() + 1){
                            editTextDialogCourseId.setText(vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase() + " ");
                            editTextDialogCourseId.setSelection(editTextDialogCourseId.getText().length());
                        }
                        if(editTextDialogCourseId.getText().length() < vmDeptAdmin.getDeptAdminInfo().getDeptId().length() + 4 ||
                                editTextDialogCourseName.getText().length() == 0 ||
                                editTextDialogSections.getText().length() == 0){
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                            buttonDialogOK.setEnabled(false);
                        }
                        else{
                            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            buttonDialogOK.setEnabled(true);
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherDialog afterTextChanged method.");
                    }
                }
            };
            editTextDialogCourseId.addTextChangedListener(watcherDialog);
            editTextDialogCourseName.addTextChangedListener(watcherDialog);
            editTextDialogSections.addTextChangedListener(watcherDialog);

            buttonDialogOK.setOnClickListener(v -> {
                try{
                    progressBarDialog.setVisibility(View.VISIBLE);
                    courseInfo.setCourseId(editTextDialogCourseId.getText().toString());
                    courseInfo.setCourseName(editTextDialogCourseName.getText().toString());
                    courseInfo.setSections(editTextDialogSections.getText().toString());
                    courseInfo.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    if(addRequested){
                        vmDeptAdmin.onAddCourseRequested(courseInfo);
                    }
                    else{
                        vmDeptAdmin.onEditCourseRequested(courseInfo);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDialogOK setOnClickListener method.");
                }
            });
            buttonDialogCancel.setOnClickListener(v -> {
                try{
                    alertDialogCourse.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDialogCancel setOnClickListener method.");
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
                    deletedCourses.setCourseIdList(deletedIdList);
                    deletedCourses.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onDeleteCoursesRequested(deletedCourses);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDeleteConfirmationYes setOnClickListener method.");
                }
            });
            buttonDeleteConfirmationNo.setOnClickListener(v -> {
                try{
                    alertDialogDeleteConfirmation.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDeleteConfirmationNo setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherPassword onTextChanged method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherPassword afterTextChanged method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonDialogChangePasswordCancel setOnClickListener method.");
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
                        editTextDialogCourseId.setEnabled(true);
                        editTextDialogCourseId.setText(vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase() + " ");
                        editTextDialogCourseName.setText(null);
                        editTextDialogSections.setText(null);
                        editTextDialogCourseId.clearFocus();
                        editTextDialogCourseName.clearFocus();
                        editTextDialogSections.clearFocus();
                        buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_black));
                        buttonDialogOK.setEnabled(false);
                        alertDialogCourse.show();
                    }
                    else{
                        deletedIdList.clear();
                        for(int i=0;i<checks.size();i++){
                            if(checks.get(i)){
                                deletedIdList.add(vmDeptAdmin.getCourseList().get(i).getCourseId());
                            }
                        }
                        textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_course));
                        alertDialogDeleteConfirmation.show();
                    }
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonAddDelete setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonSelectCancel setOnClickListener method.");
                }
            });

            buttonSearchCourseId = findViewById(R.id.button_search_id);
            buttonSearchCourseId.setOnClickListener(v -> {
                try{
                    buttonSearchCourseId.setVisibility(View.INVISIBLE);
                    buttonCancelSearchCourseId.setVisibility(View.VISIBLE);
                    textViewCourseId.setVisibility(View.INVISIBLE);
                    editTextCourseId.setVisibility(View.VISIBLE);
                    editTextCourseId.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonSearchCourseId setOnClickListener method.");
                }
            });

            buttonCancelSearchCourseId = findViewById(R.id.button_cancel_search_id);
            buttonCancelSearchCourseId.setOnClickListener(v -> {
                try{
                    buttonSearchCourseId.setVisibility(View.VISIBLE);
                    buttonCancelSearchCourseId.setVisibility(View.INVISIBLE);
                    textViewCourseId.setVisibility(View.VISIBLE);
                    editTextCourseId.setText(null);
                    editTextCourseId.setVisibility(View.INVISIBLE);
                    editTextCourseId.clearFocus();
                    progressBarCourse.setVisibility(View.VISIBLE);
                    courseFilter.setIdFilter("");
                    courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonCancelSearchCourseId setOnClickListener method.");
                }
            });

            buttonSearchCourseName = findViewById(R.id.button_search_name);
            buttonSearchCourseName.setOnClickListener(v -> {
                try{
                    buttonSearchCourseName.setVisibility(View.INVISIBLE);
                    buttonCancelSearchCourseName.setVisibility(View.VISIBLE);
                    textViewCourseName.setVisibility(View.INVISIBLE);
                    editTextCourseName.setVisibility(View.VISIBLE);
                    editTextCourseName.requestFocus();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonSearchCourseName setOnClickListener method.");
                }
            });

            buttonCancelSearchCourseName = findViewById(R.id.button_cancel_search_name);
            buttonCancelSearchCourseName.setOnClickListener(v -> {
                try{
                    buttonSearchCourseName.setVisibility(View.VISIBLE);
                    buttonCancelSearchCourseName.setVisibility(View.INVISIBLE);
                    textViewCourseName.setVisibility(View.VISIBLE);
                    editTextCourseName.setText(null);
                    editTextCourseName.setVisibility(View.INVISIBLE);
                    editTextCourseName.clearFocus();
                    progressBarCourse.setVisibility(View.VISIBLE);
                    courseFilter.setNameFilter("");
                    courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' buttonCancelSearchCourseName setOnClickListener method.");
                }
            });

            textViewCourseId = findViewById(R.id.textView_id);
            textViewCourseName = findViewById(R.id.textView_name);
            editTextCourseId = findViewById(R.id.editText_id);
            editTextCourseName = findViewById(R.id.editText_name);

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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherFilter onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(s.hashCode() == editTextCourseId.getText().hashCode()){
                            courseFilter.setIdFilter(fieldValue);
                            if(courseFilter.getIdFilter().length() == 0){
                                if (buttonSearchCourseId.getVisibility() == View.INVISIBLE){
                                    progressBarCourse.setVisibility(View.VISIBLE);
                                    courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                    vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                                }
                            }
                            else{
                                progressBarCourse.setVisibility(View.VISIBLE);
                                courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                            }
                        }
                        else if(s.hashCode() == editTextCourseName.getText().hashCode()){
                            courseFilter.setNameFilter(fieldValue);
                            if(courseFilter.getNameFilter().length() == 0){
                                if (buttonSearchCourseName.getVisibility() == View.INVISIBLE){
                                    progressBarCourse.setVisibility(View.VISIBLE);
                                    courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                    vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                                }
                            }
                            else{
                                progressBarCourse.setVisibility(View.VISIBLE);
                                courseFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                                vmDeptAdmin.onFilteredCourseListRequested(courseFilter);
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' watcherFilter afterTextChanged method.");
                    }
                }
            };
            editTextCourseId.addTextChangedListener(watcherFilter);
            editTextCourseName.addTextChangedListener(watcherFilter);

            bottomNavigationView = findViewById(R.id.bottom_navigation_dept_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_dept_admin_courses).setChecked(true);

            progressBarCourse = findViewById(R.id.progressBar);

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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getPersonInfoSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getIsSemesterActiveOrFutureSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_isSemesterActive = true;
                        if(progressBarIndicator_setCourses){
                            progressBarCourse.setVisibility(View.INVISIBLE);
                        }
                        if(vmDeptAdmin.isSemesterActive()){
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getIsSemesterActiveSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetCoursesSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setCourses = true;
                        if(progressBarIndicator_isSemesterActive){
                            progressBarCourse.setVisibility(View.INVISIBLE);
                        }
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Courses(this, vmDeptAdmin.getCourseList(), vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase());
                            recyclerViewDeptAdminCourses.setAdapter(adapter);
                        }
                        else{
                            adapter.setCourseList(vmDeptAdmin.getCourseList());
                        }
                        adapter.setPopupMenuActive(semesterActive);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getSetCoursesSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getAddCourseSuccessful().observe(this, e_add_or_edit_course_state -> {
                try{
                    if(e_add_or_edit_course_state == E_Add_Or_Edit_Course_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogCourse.dismiss();
                        showToastMessage(R.string.toast_add_course_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Courses(this, vmDeptAdmin.getCourseList(), vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase());
                            recyclerViewDeptAdminCourses.setAdapter(adapter);
                        }
                        else{
                            adapter.setCourseList(vmDeptAdmin.getCourseList());
                        }
                        resetWidgets();
                    }
                    else if(e_add_or_edit_course_state == E_Add_Or_Edit_Course_State.UNSUCCESSFUL_DUPLICATED_ID){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_course_duplicated_id);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                    else if(e_add_or_edit_course_state == E_Add_Or_Edit_Course_State.UNSUCCESSFUL_DUPLICATED_NAME){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        textViewDialogWarning.setText(R.string.warning_add_or_edit_course_duplicated_name);
                        textViewDialogWarning.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getAddCourseSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getEditCourseSuccessful().observe(this, e_add_or_edit_course_state -> {
                try{
                    if(e_add_or_edit_course_state == E_Add_Or_Edit_Course_State.SUCCESSFUL){
                        progressBarDialog.setVisibility(View.INVISIBLE);
                        alertDialogCourse.dismiss();
                        showToastMessage(R.string.toast_edit_course_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Courses(this, vmDeptAdmin.getCourseList(), vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase());
                            recyclerViewDeptAdminCourses.setAdapter(adapter);
                        }
                        else{
                            adapter.setCourseList(vmDeptAdmin.getCourseList());
                        }
                        resetWidgets();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getEditCourseSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getDeleteCoursesSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarDeleteConfirmation.setVisibility(View.INVISIBLE);
                        alertDialogDeleteConfirmation.dismiss();
                        showToastMessage(R.string.toast_delete_course_successful);
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Courses(this, vmDeptAdmin.getCourseList(), vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase());
                            recyclerViewDeptAdminCourses.setAdapter(adapter);
                        }
                        else{
                            adapter.setCourseList(vmDeptAdmin.getCourseList());
                        }
                        resetWidgets();
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' vmDeptAdmin.getDeleteCourseSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            resetWidgets();
            progressBarIndicator_isSemesterActive = false;
            progressBarIndicator_setCourses = false;
            if(toastMessage != null){
                toastMessage.cancel();
            }
            vmDeptAdmin.onLoadSemestersRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onResume method.");
        }
    }

    private void resetWidgets(){
        try{
            progressBarCourse.setVisibility(View.VISIBLE);
            buttonSelectCancel.setText(R.string.button_indicator_select);
            buttonAddDelete.setText(R.string.button_indicator_add);
            selectIndicator = true;
            if(adapter != null){    // uygulama ilk acildiginda henuz adapter set edilmemis oldugu icin
                adapter.setCheckBoxActive(false);
            }
            buttonSearchCourseId.setVisibility(View.VISIBLE);
            buttonCancelSearchCourseId.setVisibility(View.INVISIBLE);
            buttonSearchCourseName.setVisibility(View.VISIBLE);
            buttonCancelSearchCourseName.setVisibility(View.INVISIBLE);
            textViewCourseId.setVisibility(View.VISIBLE);
            editTextCourseId.setText(null);
            editTextCourseId.setVisibility(View.INVISIBLE);
            editTextCourseId.clearFocus();
            textViewCourseName.setVisibility(View.VISIBLE);
            editTextCourseName.setText(null);
            editTextCourseName.setVisibility(View.INVISIBLE);
            editTextCourseName.clearFocus();
            courseFilter.setIdFilter("");
            courseFilter.setNameFilter("");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' resetWidgets method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' showToastMessage method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onListItemClicked method.");
        }
    }

    public void onEditRequested(int position){
        try{
            addRequested = false;
            ArrayList<CourseType> courseList = vmDeptAdmin.getCourseList();
            editTextDialogCourseId.setEnabled(false);
            editTextDialogCourseId.setText(vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase() + " " + courseList.get(position).getCourseId());
            editTextDialogCourseName.setText(courseList.get(position).getCourseName());
            editTextDialogSections.setEnabled(false);
            editTextDialogSections.setText(courseList.get(position).getSections());
            editTextDialogCourseId.clearFocus();
            editTextDialogCourseName.clearFocus();
            editTextDialogSections.clearFocus();
            buttonDialogOK.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            buttonDialogOK.setEnabled(true);
            alertDialogCourse.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onEditRequested method.");
        }
    }

    public void onEditSectionsRequested(int position){
        try{
            // TODO common variables view kısmına gerekli değişkenler atanacak.
            Common_Variables_View.NUMBER_OF_SECTIONS = Integer.parseInt(vmDeptAdmin.getCourseList().get(position).getSections());
            Common_Variables_View.COURSE_ID = vmDeptAdmin.getCourseList().get(position).getCourseId();
            Common_Variables_View.COURSE_NAME = vmDeptAdmin.getCourseList().get(position).getCourseName();
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Specific_Course.class);
            startActivity(i);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onEditSectionsRequested method.");
        }
    }

    public void onDeleteRequested(int position){
        try{
            deletedIdList.clear();
            deletedIdList.add(vmDeptAdmin.getCourseList().get(position).getCourseId());
            textViewDeleteConfirmation.setText(deletedIdList.size() + " " + getResources().getString(R.string.delete_confirmation_course));
            alertDialogDeleteConfirmation.show();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onDeleteRequested method.");
        }
    }

    public void goToCourses(MenuItem item) {
        // nothing to do
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Students.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' goToStudents method.");
        }
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            resetWidgets();
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmDeptAdmin.onPersonInfoRequested(selectedSemester);
            vmDeptAdmin.onSemesterActiveOrFutureRequested(selectedSemester);
            vmDeptAdmin.onCourseListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onSemesterChanged method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onDismissDialogRequested method.");
        }
    }
}