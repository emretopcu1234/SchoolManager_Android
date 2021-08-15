package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Spinner;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Specific_Course_Students;

import java.util.ArrayList;

public class Activity_Dept_Admin_Specific_Course extends AppCompatActivity {

    Spinner spinnerSection;
    ArrayAdapter arrayAdapterSection;
    Spinner spinnerLecturer;
    ArrayAdapter arrayAdapterLecturer;

    private RecyclerViewAdapter_Dept_Admin_Specific_Course_Students adapter;
    private RecyclerView recyclerViewDeptAdminSpecificCourseStudents;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builderStudent;
    private View viewDialogStudent;
    private AlertDialog alertDialogStudent;
    private Button buttonStudent;

    private AlertDialog.Builder builderHour;
    private View viewDialogHour;
    private AlertDialog alertDialogHour;
    private Button buttonHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_specific_course);

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

            recyclerViewDeptAdminSpecificCourseStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(this);
            recyclerViewDeptAdminSpecificCourseStudents.setLayoutManager(layoutManager);
            recyclerViewDeptAdminSpecificCourseStudents.setAdapter(adapter);











            buttonStudent = (Button) findViewById(R.id.button_add_delete);
            buttonStudent.setOnClickListener(new View.OnClickListener() {
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







            buttonHour = (Button) findViewById(R.id.button_add_course_hour);
            buttonHour.setOnClickListener(new View.OnClickListener() {
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
}



