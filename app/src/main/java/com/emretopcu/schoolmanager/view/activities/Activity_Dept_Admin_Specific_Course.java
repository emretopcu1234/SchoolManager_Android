package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonFilterType;
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Specific_Course_Students;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Filter_Department;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Dept_Admin;

import java.util.ArrayList;

public class Activity_Dept_Admin_Specific_Course extends AppCompatActivity {

    private TextView textViewCourseId;
    private TextView textViewCourseName;

    private Spinner spinnerSection;
    private ArrayAdapter arrayAdapterSection;
    private ArrayList<String> spinnerSectionList;

    private Spinner spinnerLecturer;
    private ArrayAdapter arrayAdapterLecturer;
    private ArrayList<String> spinnerLecturerList;

    private TextView textViewCourseHours;
    private Button buttonAddCourseHour;
    private Button buttonResetCourseHour;

    private Button buttonSelectCancel;
    private Button buttonAddDelete;

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

    private RecyclerViewAdapter_Dept_Admin_Specific_Course_Students adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private Button buttonOk;
    private Button buttonCancel;

    private AlertDialog.Builder builderStudent;
    private View viewDialogStudent;
    private AlertDialog alertDialogStudent;

    private Button buttonStudentOk;
    private Button buttonStudentCancel;
    private Spinner spinnerStudentDept;
    private ArrayAdapter arrayAdapterStudentDept;
    private ArrayList<String> spinnerStudentDeptList;
    private Spinner spinnerStudentInfo;
    private ArrayAdapter arrayAdapterStudentInfo;
    private ArrayList<String> spinnerStudentInfoList;

    private AlertDialog.Builder builderHour;
    private View viewDialogHour;
    private AlertDialog alertDialogHour;

    private Button buttonHourOk;
    private Button buttonHourCancel;
    private Spinner spinnerHourDay;
    private ArrayAdapter arrayAdapterHourDay;
    private ArrayList<String> spinnerHourDayList;
    private Spinner spinnerStartHour;
    private ArrayAdapter arrayAdapterStartHour;
    private ArrayList<String> spinnerStartHourList;
    private Spinner spinnerEndHour;
    private ArrayAdapter arrayAdapterEndHour;
    private ArrayList<String> spinnerEndHourList;

    private AlertDialog.Builder builderStudentFilter;
    private View viewDialogStudentFilter;
    private AlertDialog alertDialogStudentFilter;

    private RecyclerViewAdapter_Filter_Department adapterFilter;
    private RecyclerView recyclerViewFilter;
    private LinearLayoutManager layoutManagerFilter;

    private Button buttonFilterClear;
    private Button buttonFilterOk;
    private Button buttonFilterCancel;

    private ProgressBar progressBar;
    private ProgressBar progressBarCourseHour;
    private ProgressBar progressBarStudent;

    private final PersonFilterType personFilter = new PersonFilterType();
    private ArrayList<Boolean> previousFilterChecks = new ArrayList<>();
    private final ArrayList<Boolean> checks = new ArrayList<>();

    private VM_Dept_Admin vmDeptAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_specific_course);

            textViewCourseId = findViewById(R.id.textView_course_code);
            textViewCourseName = findViewById(R.id.textView_course_name);

            spinnerSection = findViewById(R.id.spinner_section);
            spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        // TODO
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' spinnerSection's onItemSelected method.");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // nothing to do
                }
            });
            spinnerSectionList = new ArrayList<>();
            for(int i=0;i< Common_Variables_View.NUMBER_OF_SECTIONS;i++){
                spinnerSectionList.add("Section" + (i+1));
            }
            arrayAdapterSection = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerSectionList);
            arrayAdapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSection.setAdapter(arrayAdapterSection);

            spinnerLecturer = findViewById(R.id.spinner_lecturer);
            spinnerLecturerList = new ArrayList<>();

            textViewCourseHours = findViewById(R.id.textView_course_hours);

            buttonAddCourseHour = findViewById(R.id.button_add_course_hour);
            buttonAddCourseHour.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonAddCourseHour.setOnClickListener method.");
                }
            });
            buttonResetCourseHour = findViewById(R.id.button_reset_course_hour);
            buttonResetCourseHour.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonResetCourseHour.setOnClickListener method.");
                }
            });

            buttonSelectCancel = findViewById(R.id.button_select_cancel);
            buttonSelectCancel.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonSelectCancel.setOnClickListener method.");
                }
            });
            buttonAddDelete = findViewById(R.id.button_add_delete);
            buttonAddDelete.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonAddDelete.setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonSearchId setOnClickListener method.");
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
                    personFilter.setIdFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredSpecificStudentListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonCancelSearchId setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonSearchName setOnClickListener method.");
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
                    personFilter.setNameFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredSpecificStudentListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonCancelSearchName setOnClickListener method.");
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
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonSearchSurname setOnClickListener method.");
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
                    personFilter.setSurnameFilter("");
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredSpecificStudentListRequested(personFilter);
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonCancelSearchSurname setOnClickListener method.");
                }
            });

            buttonFilterDeptName = findViewById(R.id.button_filter_empty_closed);
            buttonFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogStudentFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonFilterDeptName setOnClickListener method.");
                }
            });

            buttonCancelFilterDeptName = findViewById(R.id.button_filter_full_closed);
            buttonCancelFilterDeptName.setOnClickListener(v -> {
                try{
                    alertDialogStudentFilter.show();
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonCancelFilterDeptName setOnClickListener method.");
                }
            });

            textViewId = findViewById(R.id.textView_id);
            textViewName = findViewById(R.id.textView_name);
            textViewSurname = findViewById(R.id.textView_surname);
            editTextId = findViewById(R.id.editText_id);
            editTextName = findViewById(R.id.editText_name);
            editTextSurname = findViewById(R.id.editText_surname);

            buttonOk = findViewById(R.id.button_ok);
            buttonOk.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonOk setOnClickListener method.");
                }
            });
            buttonCancel = findViewById(R.id.button_cancel);
            buttonCancel.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonCancel setOnClickListener method.");
                }
            });

            recyclerView = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            builderHour = new AlertDialog.Builder(this);
            viewDialogHour = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_hour, null);
            builderHour.setView(viewDialogHour);
            alertDialogHour = builderHour.create();
            alertDialogHour.setCancelable(false);
            alertDialogHour.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            buttonHourOk = viewDialogHour.findViewById(R.id.button_ok);
            buttonHourOk.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonHourOk setOnClickListener method.");
                }
            });
            buttonHourCancel = viewDialogHour.findViewById(R.id.button_cancel);
            buttonHourCancel.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonHourCancel setOnClickListener method.");
                }
            });

            spinnerHourDay = viewDialogHour.findViewById(R.id.spinner_day);
            spinnerHourDayList = new ArrayList<>();
            spinnerHourDayList.add("Monday");
            spinnerHourDayList.add("Tuesday");
            spinnerHourDayList.add("Wednesday");
            spinnerHourDayList.add("Thursday");
            spinnerHourDayList.add("Friday");
            spinnerHourDayList.add("Saturday");
            arrayAdapterHourDay = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerHourDayList);
            arrayAdapterHourDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerHourDay.setAdapter(arrayAdapterHourDay);

            spinnerStartHour = viewDialogHour.findViewById(R.id.spinner_start_hour);
            spinnerStartHourList = new ArrayList<>();
            for(int i=0;i<Common_Variables_View.NUMBER_OF_SECTIONS;i++){
                spinnerStartHourList.add((i+9) + ":00");
            }
            arrayAdapterStartHour = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerStartHourList);
            arrayAdapterStartHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStartHour.setAdapter(arrayAdapterStartHour);

            spinnerEndHour = viewDialogHour.findViewById(R.id.spinner_end_hour);
            spinnerEndHourList = new ArrayList<>();
            for(int i=0;i<Common_Variables_View.NUMBER_OF_SECTIONS;i++){
                spinnerEndHourList.add((i+9) + ":50");
            }
            arrayAdapterEndHour = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerEndHourList);
            arrayAdapterEndHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEndHour.setAdapter(arrayAdapterEndHour);

            builderStudent = new AlertDialog.Builder(this);
            viewDialogStudent = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_student, null);
            builderStudent.setView(viewDialogStudent);
            alertDialogStudent = builderStudent.create();
            alertDialogStudent.setCancelable(false);
            alertDialogStudent.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            buttonStudentOk = viewDialogStudent.findViewById(R.id.button_ok);
            buttonStudentOk.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonStudentOk setOnClickListener method.");
                }
            });
            buttonStudentCancel = viewDialogStudent.findViewById(R.id.button_cancel);
            buttonStudentCancel.setOnClickListener(v -> {
                try{
                    // TODO
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonStudentCancel setOnClickListener method.");
                }
            });

            spinnerStudentDeptList = new ArrayList<>();
            spinnerStudentDept = viewDialogStudent.findViewById(R.id.spinner_dept);
            spinnerStudentDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        vmDeptAdmin.onDeptStudentListRequested(Common_Variables_View.SELECTED_SEMESTER,spinnerStudentDept.getSelectedItem().toString());
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' spinnerStudentDept's onItemSelected method.");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // nothing to do
                }
            });

            spinnerStudentInfoList = new ArrayList<>();
            spinnerStudentInfo = viewDialogStudent.findViewById(R.id.spinner_student);

            builderStudentFilter = new AlertDialog.Builder(this);
            viewDialogStudentFilter = this.getLayoutInflater().inflate(R.layout.dialog_filter_department, null);
            builderStudentFilter.setView(viewDialogStudentFilter);
            alertDialogStudentFilter = builderStudentFilter.create();
            alertDialogStudentFilter.setCancelable(false);
            alertDialogStudentFilter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            recyclerViewFilter = viewDialogStudentFilter.findViewById(R.id.recyclerView);
            layoutManagerFilter = new LinearLayoutManager(this);
            recyclerViewFilter.setLayoutManager(layoutManagerFilter);

            buttonFilterClear = viewDialogStudentFilter.findViewById(R.id.button_clear);
            buttonFilterOk = viewDialogStudentFilter.findViewById(R.id.button_ok);
            buttonFilterCancel = viewDialogStudentFilter.findViewById(R.id.button_cancel);
            buttonFilterClear.setOnClickListener(v -> {
                try{
                    adapterFilter.resetChecks();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonFilterClear setOnClickListener method.");
                }
            });
            buttonFilterOk.setOnClickListener(v -> {
                try{
                    previousFilterChecks.clear();
                    for(int i=0;i<adapterFilter.getChecks().size();i++){
                        previousFilterChecks.add(adapterFilter.getChecks().get(i));
                    }
                    personFilter.setDeptFilter(adapterFilter.getFilteredDepartmentList());
                    if(personFilter.getDeptFilter().size() > 0){
                        buttonFilterDeptName.setVisibility(View.INVISIBLE);
                        buttonCancelFilterDeptName.setVisibility(View.VISIBLE);
                    }
                    else{
                        buttonFilterDeptName.setVisibility(View.VISIBLE);
                        buttonCancelFilterDeptName.setVisibility(View.INVISIBLE);
                    }
                    personFilter.setSemester(Common_Variables_View.SELECTED_SEMESTER);
                    vmDeptAdmin.onFilteredSpecificStudentListRequested(personFilter);
                    alertDialogStudentFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonFilterOk setOnClickListener method.");
                }
            });
            buttonFilterCancel.setOnClickListener(v -> {
                try{
                    adapterFilter.setChecks(previousFilterChecks);
                    alertDialogStudentFilter.dismiss();
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' buttonFilterCancel setOnClickListener method.");
                }
            });

            // TODO gerekli değişkenler oluşturulacak, böylece tüm datalar alındığında progress bar invisible olacak.
            progressBar = findViewById(R.id.progressBar);
            progressBarCourseHour = viewDialogHour.findViewById(R.id.progressBar);
            progressBarStudent = viewDialogStudent.findViewById(R.id.progressBar);

            vmDeptAdmin = new ViewModelProvider(this).get(VM_Dept_Admin.class);
            vmDeptAdmin.getSetCourseSectionSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                            if(!spinnerLecturerList.isEmpty()){
                                for(String s : spinnerLecturerList){
                                }
                                int position = spinnerLecturerList.indexOf(vmDeptAdmin.getCourseSectionInfo().getLecturerFullName());
                                spinnerLecturer.setSelection(position);
                            }
                            if(adapter == null){
                                adapter = new RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(this, vmDeptAdmin.getSpecificStudentList());
                                recyclerView.setAdapter(adapter);
                            }
                            else{
                                adapter.setSpecificStudentList(vmDeptAdmin.getSpecificStudentList());
                            }
                            if(vmDeptAdmin.getCourseSectionInfo().getStartHours().isEmpty()){
                                textViewCourseHours.setText(null);
                            }
                            else{
                                String text = "";
                                for(int i=0;i<vmDeptAdmin.getCourseSectionInfo().getStartHours().size()-1;i++){
                                    text += vmDeptAdmin.getCourseSectionInfo().getHourDays().get(i) + " "
                                            + vmDeptAdmin.getCourseSectionInfo().getStartHours().get(i) + "-"
                                            + vmDeptAdmin.getCourseSectionInfo().getEndHours().get(i) + "\n";
                                }
                                text += vmDeptAdmin.getCourseSectionInfo().getHourDays().get(vmDeptAdmin.getCourseSectionInfo().getStartHours().size()-1) + " "
                                        + vmDeptAdmin.getCourseSectionInfo().getStartHours().get(vmDeptAdmin.getCourseSectionInfo().getStartHours().size()-1) + "-"
                                        + vmDeptAdmin.getCourseSectionInfo().getEndHours().get(vmDeptAdmin.getCourseSectionInfo().getStartHours().size()-1);
                                textViewCourseHours.setText(text);
                            }
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetCourseSectionSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetLecturersSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        spinnerLecturerList.clear();
                        for(int i=0;i<vmDeptAdmin.getLecturerList().size();i++){
                            spinnerLecturerList.add(vmDeptAdmin.getLecturerList().get(i).getName() + " " + vmDeptAdmin.getLecturerList().get(i).getSurname());
                        }
                        arrayAdapterLecturer = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerLecturerList);
                        arrayAdapterLecturer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerLecturer.setAdapter(arrayAdapterLecturer);
                    }
                    int position = spinnerLecturerList.indexOf(vmDeptAdmin.getCourseSectionInfo().getLecturerFullName());
                    spinnerLecturer.setSelection(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetLecturersSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetDepartmentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        spinnerStudentDeptList.clear();
                        for(String key : vmDeptAdmin.getDepartmentInfo().keySet()){
                            spinnerStudentDeptList.add(vmDeptAdmin.getDepartmentInfo().get(key));
                        }
                        if(arrayAdapterStudentDept == null){
                            arrayAdapterStudentDept = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerStudentDeptList);
                            arrayAdapterStudentDept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        }
                        spinnerStudentDept.setAdapter(arrayAdapterStudentDept);
                    }
                }
                catch (Exception e) {
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetDepartmentsSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetSpecificStudentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        if(adapter == null){
                            adapter = new RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(this, vmDeptAdmin.getSpecificStudentList());
                            recyclerView.setAdapter(adapter);
                        }
                        else{
                            adapter.setSpecificStudentList(vmDeptAdmin.getSpecificStudentList());
                        }
                        // TODO studentların departmentları hashset'e kaydedilecek. recyclerviewadapter filter student'a gönderilecek.
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetSpecificStudentsSuccessful().observe method.");
                }
            });
            vmDeptAdmin.getSetDeptStudentsSuccessful().observe(this, e_successful_unsuccessful_noStatement -> {
                try{
                    if(e_successful_unsuccessful_noStatement == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        spinnerStudentInfoList.clear();
                        for(int i=0;i<vmDeptAdmin.getDeptStudentList().size();i++){
                            spinnerStudentInfoList.add(vmDeptAdmin.getDeptStudentList().get(i).getId() + " - "
                                    + vmDeptAdmin.getDeptStudentList().get(i).getName() + " " + vmDeptAdmin.getDeptStudentList().get(i).getSurname());
                        }
                        arrayAdapterStudentInfo = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerStudentInfoList);
                        arrayAdapterStudentInfo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerStudentInfo.setAdapter(arrayAdapterStudentInfo);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetDeptStudentsSuccessful().observe method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onCreate method.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            textViewCourseId.setText(vmDeptAdmin.getDeptAdminInfo().getDeptId().toUpperCase() + " " + Common_Variables_View.COURSE_ID);
            textViewCourseName.setText(Common_Variables_View.COURSE_NAME);
            vmDeptAdmin.onCourseSectionInfoRequested(Common_Variables_View.SELECTED_SEMESTER, Common_Variables_View.COURSE_ID, spinnerSection.getSelectedItem().toString());
            vmDeptAdmin.onLecturerListRequested(Common_Variables_View.SELECTED_SEMESTER);
            vmDeptAdmin.onDepartmentListRequested(Common_Variables_View.SELECTED_SEMESTER);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onResume method.");
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
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onListItemClicked method.");
        }
    }
}