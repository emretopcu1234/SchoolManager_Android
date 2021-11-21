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
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.Helper_Dialog_Change_Password;
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Students;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Filter_Department;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Change_Password_State;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Dept_Admin;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Dept_Admin_Students extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Dept_Admin_Students adapter;
    private RecyclerView recyclerViewDeptAdminStudents;
    private LinearLayoutManager layoutManager;

    private RecyclerViewAdapter_Filter_Department adapterFilter;
    private RecyclerView recyclerViewDeptAdminStudentsFilter;
    private LinearLayoutManager layoutManagerFilter;

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
    private VM_Dept_Admin vmDeptAdmin;

    private ProgressBar progressBarStudent;
    private ProgressBar progressBarChangePassword;

    private boolean progressBarIndicator_setStudents;
    private boolean progressBarIndicator_setDepartments;
    private String idFilter = "";
    private String nameFilter = "";
    private String surnameFilter = "";
    private ArrayList<String> deptFilter = new ArrayList<>();
    private ArrayList<Boolean> previousFilterChecks = new ArrayList<>();

    private PersonType deptAdminInfo = new PersonType();



    // TODO checkbox eklenecek (show all departments ismiyle)
    // TODO buna göre ya tüm bölümlerdeki öğrenciler ya da sadece ilgili departmentın öğrencileri gelecek

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_students);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_dept_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewDeptAdminStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewDeptAdminStudents.setLayoutManager(layoutManager);

            builderFilter = new AlertDialog.Builder(this);
            viewDialogFilter = this.getLayoutInflater().inflate(R.layout.dialog_filter_department, null);
            builderFilter.setView(viewDialogFilter);
            alertDialogFilter = builderFilter.create();
            alertDialogFilter.setCancelable(false);
            alertDialogFilter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            recyclerViewDeptAdminStudentsFilter = viewDialogFilter.findViewById(R.id.recyclerView);
            layoutManagerFilter = new LinearLayoutManager(this);
            recyclerViewDeptAdminStudentsFilter.setLayoutManager(layoutManagerFilter);

            buttonFilterClear = viewDialogFilter.findViewById(R.id.button_clear);
            buttonFilterOK = viewDialogFilter.findViewById(R.id.button_ok);
            buttonFilterCancel = viewDialogFilter.findViewById(R.id.button_cancel);
            buttonFilterClear.setOnClickListener(v -> {
                try{
                    adapterFilter.resetChecks();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonFilterClear setOnClickListener method.");
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
                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                    alertDialogFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonFilterOK setOnClickListener method.");
                }
            });
            buttonFilterCancel.setOnClickListener(v -> {
                try{
                    adapterFilter.setChecks(previousFilterChecks);
                    alertDialogFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonFilterCancel setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' watcherPassword onTextChanged method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' watcherPassword afterTextChanged method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonDialogChangePasswordOK setOnClickListener method.");
                }
            });
            buttonDialogChangePasswordCancel = viewDialogChangePassword.findViewById(R.id.button_cancel);
            buttonDialogChangePasswordCancel.setOnClickListener(v -> {
                try{
                    helperDialogChangePassword.onCancelClicked();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonDialogChangePasswordCancel setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonSearchId setOnClickListener method.");
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
                    progressBarStudent.setVisibility(View.VISIBLE);
                    idFilter = "";
                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonCancelSearchId setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonSearchName setOnClickListener method.");
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
                    progressBarStudent.setVisibility(View.VISIBLE);
                    nameFilter = "";
                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonCancelSearchName setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonSearchSurname setOnClickListener method.");
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
                    progressBarStudent.setVisibility(View.VISIBLE);
                    surnameFilter = "";
                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonCancelSearchSurname setOnClickListener method.");
                }
            });

            buttonFilterDeptName = findViewById(R.id.button_filter_empty_closed);
            buttonFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonFilterDeptName setOnClickListener method.");
                }
            });

            buttonCancelFilterDeptName = findViewById(R.id.button_filter_full_closed);
            buttonCancelFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' buttonCancelFilterDeptName setOnClickListener method.");
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
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' watcherFilter onTextChanged method.");
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        if(s.hashCode() == editTextId.getText().hashCode()){
                            idFilter = fieldValue;
                            if(idFilter.length() == 0){
                                if (buttonSearchId.getVisibility() == View.INVISIBLE){
                                    progressBarStudent.setVisibility(View.VISIBLE);
                                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarStudent.setVisibility(View.VISIBLE);
                                vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                        else if(s.hashCode() == editTextName.getText().hashCode()){
                            nameFilter = fieldValue;
                            if(nameFilter.length() == 0){
                                if (buttonSearchName.getVisibility() == View.INVISIBLE){
                                    progressBarStudent.setVisibility(View.VISIBLE);
                                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarStudent.setVisibility(View.VISIBLE);
                                vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                        else if(s.hashCode() == editTextSurname.getText().hashCode()){
                            surnameFilter = fieldValue;
                            if(surnameFilter.length() == 0){
                                if (buttonSearchSurname.getVisibility() == View.INVISIBLE){
                                    progressBarStudent.setVisibility(View.VISIBLE);
                                    vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                                }
                            }
                            else{
                                progressBarStudent.setVisibility(View.VISIBLE);
                                vmDeptAdmin.onFilteredStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,idFilter,nameFilter,surnameFilter,deptFilter);
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' watcherFilter afterTextChanged method.");
                    }
                }
            };
            editTextId.addTextChangedListener(watcherFilter);
            editTextName.addTextChangedListener(watcherFilter);
            editTextSurname.addTextChangedListener(watcherFilter);

            bottomNavigationView = findViewById(R.id.bottom_navigation_dept_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_dept_admin_students).setChecked(true);

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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' vmLoginProcess.getChangePasswordSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' vmDeptAdmin.getPersonInfoSuccessful().observe method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' vmDeptAdmin.getSetSemestersSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetDepartmentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setDepartments = true;
                        if(progressBarIndicator_setStudents){
                            progressBarStudent.setVisibility(View.INVISIBLE);
                        }
                        if(adapterFilter == null){
                            adapterFilter = new RecyclerViewAdapter_Filter_Department(this, vmDeptAdmin.getDepartmentList());
                            recyclerViewDeptAdminStudentsFilter.setAdapter(adapterFilter);
                        }
                        else{
                            adapterFilter.setDepartmentList(vmDeptAdmin.getDepartmentList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' vmDeptAdmin.getSetDepartmentsSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetStudentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        progressBarIndicator_setStudents = true;
                        if(progressBarIndicator_setDepartments){
                            progressBarStudent.setVisibility(View.INVISIBLE);
                        }
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Students(this, vmDeptAdmin.getStudentList());
                            recyclerViewDeptAdminStudents.setAdapter(adapter);
                        }
                        else{
                            adapter.setStudentList(vmDeptAdmin.getStudentList());
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' vmDeptAdmin.getSetStudentsSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onCreate method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onResume method.");
        }
    }

    private void resetWidgets(){
        try{
            progressBarStudent.setVisibility(View.VISIBLE);
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' resetWidgets method.");
        }
    }

    private void showToastMessage(int message){
        try{
            toastMessage = Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT);
            toastMessage.show();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' showToastMessage method.");
        }
    }

    public void goToCourses(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Courses.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' goToCourses method.");
        }
    }

    public void goToLecturers(MenuItem item) {
        try{
            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Lecturers.class);
            startActivity(i);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' goToLecturers method.");
        }
    }

    public void goToStudents(MenuItem item) {
        // nothing to do
    }

    @Override
    public void onSemesterChanged(String selectedSemester, int position) {
        try{
            resetWidgets();
            Common_Variables_View.SELECTED_SEMESTER = selectedSemester;
            Common_Variables_View.SEMESTER_SPINNER_POSITION = position;
            vmDeptAdmin.onPersonInfoRequested(selectedSemester);
            vmDeptAdmin.onStudentListRequested(selectedSemester);
            vmDeptAdmin.onDepartmentListRequested(selectedSemester);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onSemesterChanged method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onChangePasswordClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onLogoutClicked method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' setAndShowWarningOnDialogChangePassword method.");
        }
    }

    @Override
    public void onChangePasswordRequested(String oldPassword, String newPassword) {
        try{
            progressBarChangePassword.setVisibility(View.VISIBLE);
            vmLoginProcess.onChangePasswordRequested(oldPassword, newPassword);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onChangePasswordRequested method.");
        }
    }

    @Override
    public void onDismissDialogRequested() {
        try{
            progressBarChangePassword.setVisibility(View.INVISIBLE);
            alertDialogChangePassword.dismiss();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onDismissDialogRequested method.");
        }
    }
}