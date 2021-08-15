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
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;

import java.util.ArrayList;

public class Activity_Lecturer_Specific_Appointment extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;

    private RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests adapterAcceptedRequests;
    private RecyclerView recyclerViewLecturerSpecificAppointmentAcceptedRequests;
    private LinearLayoutManager layoutManagerAcceptedRequests;

    private RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests adapterRequests;
    private RecyclerView recyclerViewLecturerSpecificAppointmentRequests;
    private LinearLayoutManager layoutManagerRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_specific_appointment);

            ArrayList<String> sections = new ArrayList<>();
            sections.add("IE 251 - Section 1");
            sections.add("IE 266 - All Sections");
            sections.add("IE 266 - Section 3");
            sections.add("IE 266 - Section 4");
            // TODO input olarak gelecek.

            spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, sections);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);


            recyclerViewLecturerSpecificAppointmentAcceptedRequests = findViewById(R.id.recyclerView_accepted_requests);
            layoutManagerAcceptedRequests = new LinearLayoutManager(this);

            adapterAcceptedRequests = new RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests(this);
            recyclerViewLecturerSpecificAppointmentAcceptedRequests.setLayoutManager(layoutManagerAcceptedRequests);
            recyclerViewLecturerSpecificAppointmentAcceptedRequests.setAdapter(adapterAcceptedRequests);


            recyclerViewLecturerSpecificAppointmentRequests = findViewById(R.id.recyclerView_requests);
            layoutManagerRequests = new LinearLayoutManager(this);

            adapterRequests = new RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests(this);
            recyclerViewLecturerSpecificAppointmentRequests.setLayoutManager(layoutManagerRequests);
            recyclerViewLecturerSpecificAppointmentRequests.setAdapter(adapterRequests);



        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onCreate method.");
        }


    }
}



