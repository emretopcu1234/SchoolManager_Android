package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
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
import com.emretopcu.schoolmanager.view.Common_Variables_View;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Specific_Course_Students;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Students;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Dept_Admin;

import java.util.ArrayList;

public class Activity_Dept_Admin_Specific_Course extends AppCompatActivity {

    private TextView textViewCourseId;
    private Spinner spinnerSection;
    private ArrayAdapter arrayAdapterSection;
    private ArrayList<String> spinnerSectionList;
    private Spinner spinnerLecturer;
    private ArrayList<String> spinnerLecturerList;
    private ArrayAdapter arrayAdapterLecturer;

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

    private Button buttonOk;
    private Button buttonCancel;

    private RecyclerViewAdapter_Dept_Admin_Specific_Course_Students adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderStudent;
    private View viewDialogStudent;
    private AlertDialog alertDialogStudent;

    private AlertDialog.Builder builderStudentFilter;
    private View viewDialogStudentFilter;
    private AlertDialog alertDialogStudentFilter;

    private AlertDialog.Builder builderHour;
    private View viewDialogHour;
    private AlertDialog alertDialogHour;

    private ProgressBar progressBar;
    private ProgressBar progressBarCourseHour;
    private ProgressBar progressBarStudent;

    private VM_Dept_Admin vmDeptAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_specific_course);

            textViewCourseId = findViewById(R.id.textView_course_code);
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

            adapter = new RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            builderHour = new AlertDialog.Builder(this);
            viewDialogHour = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_hour, null);
            builderHour.setView(viewDialogHour);
            alertDialogHour = builderHour.create();
            alertDialogHour.setCancelable(false);
            alertDialogHour.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            builderStudent = new AlertDialog.Builder(this);
            viewDialogStudent = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_student, null);
            builderStudent.setView(viewDialogStudent);
            alertDialogStudent = builderStudent.create();
            alertDialogStudent.setCancelable(false);
            alertDialogStudent.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            builderStudentFilter = new AlertDialog.Builder(this);
            viewDialogStudentFilter = this.getLayoutInflater().inflate(R.layout.dialog_filter_department, null);
            builderStudentFilter.setView(viewDialogStudentFilter);
            alertDialogStudentFilter = builderStudentFilter.create();
            alertDialogStudentFilter.setCancelable(false);
            alertDialogStudentFilter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            progressBar = findViewById(R.id.progressBar);
            progressBarCourseHour = viewDialogHour.findViewById(R.id.progressBar);
            progressBarStudent = viewDialogStudent.findViewById(R.id.progressBar);

            vmDeptAdmin = new ViewModelProvider(this).get(VM_Dept_Admin.class);
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
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetLecturersSuccessful().observe method.");
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
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' vmDeptAdmin.getSetSpecificStudentsSuccessful().observe method.");
                }
            });











            ArrayList<String> sections = new ArrayList<>();
            sections.add("Section 1");
            sections.add("Section 2");
            sections.add("Section 3");
            sections.add("Section 4");
            // TODO input olarak gelecek.

            spinnerSection = (Spinner) findViewById(R.id.spinner_section);
            spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayAdapterSection = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, sections);
            arrayAdapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSection.setAdapter(arrayAdapterSection);

            ArrayList<String> lecturers = new ArrayList<>();
            lecturers.add("Mustafa Kemal TURAN");
            lecturers.add("Müge Gökçe ADİL");
            lecturers.add("Ahmet YILDIR");
            lecturers.add("Seren USER TOPÇU");
            lecturers.add("Emre TOPÇU");
            // TODO input olarak gelecek.

            spinnerLecturer = (Spinner) findViewById(R.id.spinner_lecturer);
            spinnerLecturer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayAdapterLecturer = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, lecturers);
            arrayAdapterLecturer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLecturer.setAdapter(arrayAdapterLecturer);




            buttonAddDelete = (Button) findViewById(R.id.button_add_delete);
            buttonAddDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogStudent.show();
                }
            });

            builderStudent = new AlertDialog.Builder(this);
            viewDialogStudent = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_student, null);
            builderStudent.setView(viewDialogStudent);
            alertDialogStudent = builderStudent.create();
            alertDialogStudent.setCancelable(false);
            alertDialogStudent.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            ArrayList<String> semesters = new ArrayList<>();
            semesters.add("Electrical and Electronics Engineeering");
            semesters.add("Civil Engineering");
            semesters.add("Mathematical Engineering");
            // TODO input olarak gelecek.

            Spinner spinnerDept = (Spinner) viewDialogStudent.findViewById(R.id.spinner_dept);
            spinnerDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterDept = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, semesters);
            arrayAdapterDept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDept.setAdapter(arrayAdapterDept);


            ArrayList<String> students = new ArrayList<>();
            students.add("1938794 - Emre Mehmet DENKGENCER");
            students.add("2020234 - İnsan İsmi Soyismi");
            students.add("1846573 - Acaba Soyisim");
            students.add("1919230 - Adım Işıklı");
            // TODO input olarak gelecek.

            Spinner spinnerStudent= (Spinner) viewDialogStudent.findViewById(R.id.spinner_student);
            spinnerStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterStudent = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, students);
            arrayAdapterStudent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStudent.setAdapter(arrayAdapterStudent);







            buttonAddCourseHour = (Button) findViewById(R.id.button_add_course_hour);
            buttonAddCourseHour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogHour.show();
                }
            });

            builderHour = new AlertDialog.Builder(this);
            viewDialogHour = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_specific_course_hour, null);
            builderHour.setView(viewDialogHour);
            alertDialogHour = builderHour.create();
            alertDialogHour.setCancelable(false);
            alertDialogHour.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            ArrayList<String> semesters1 = new ArrayList<>();
            semesters1.add("Monday");
            semesters1.add("Tuesday");
            semesters1.add("Friday");
            semesters1.add("Saturday");
            // TODO input olarak gelecek.

            Spinner spinnerDay = (Spinner) viewDialogHour.findViewById(R.id.spinner_day);
            spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterDay = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, semesters1);
            arrayAdapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDay.setAdapter(arrayAdapterDay);


            ArrayList<String> startHour = new ArrayList<>();
            startHour.add("09:00");
            startHour.add("10:00");
            startHour.add("11:00");
            startHour.add("12:00");
            // TODO input olarak gelecek.

            Spinner spinnerStartHour = (Spinner) viewDialogHour.findViewById(R.id.spinner_start_hour);
            spinnerStartHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterStartHour = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, startHour);
            arrayAdapterStartHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStartHour.setAdapter(arrayAdapterStartHour);

            ArrayList<String> endHour = new ArrayList<>();
            endHour.add("09:50");
            endHour.add("10:50");
            endHour.add("11:50");
            endHour.add("12:50");
            // TODO input olarak gelecek.

            Spinner spinnerEndHour = (Spinner) viewDialogHour.findViewById(R.id.spinner_end_hour);
            spinnerEndHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterEndHour = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, endHour);
            arrayAdapterEndHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEndHour.setAdapter(arrayAdapterEndHour);







        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onCreate method.");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            vmDeptAdmin.getLecturerList();
            vmDeptAdmin.getSpecificStudentList(Common_Variables_View.SELECTED_SEMESTER, Common_Variables_View.COURSE_ID, spinnerSection.getSelectedItem());
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onResume method.");
        }
    }

    private void setSpinnerLecturerList(ArrayList<String> spinnerLecturerList) {
        try{
            this.spinnerLecturerList = spinnerLecturerList;
            arrayAdapterLecturer = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_dept_admin_specific_course, spinnerLecturerList);
            arrayAdapterLecturer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLecturer.setAdapter(arrayAdapterLecturer);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' setSpinnerLecturerList method.");
        }
    }
}



