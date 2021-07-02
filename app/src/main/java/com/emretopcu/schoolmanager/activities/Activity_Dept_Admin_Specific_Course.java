package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Courses;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Specific_Course_Students;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Students;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;

import java.util.ArrayList;

public class Activity_Dept_Admin_Specific_Course extends AppCompatActivity {

    Spinner spinnerSection;
    ArrayAdapter arrayAdapterSection;
    Spinner spinnerLecturer;
    ArrayAdapter arrayAdapterLecturer;

    private RecyclerViewAdapter_Dept_Admin_Specific_Course_Students adapter;
    private RecyclerView recyclerViewDeptAdminSpecificCourseStudents;
    private LinearLayoutManager layoutManager;

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





        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onCreate method.");
        }


    }
}



