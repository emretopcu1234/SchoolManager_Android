package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Filter_Department;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Main_Admin_Lecturers extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Lecturers adapter;
    private RecyclerView recyclerViewMainAdminLecturers;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;

    private RecyclerViewAdapter_Filter_Department adapterFilter;
    private RecyclerView recyclerViewFilter;
    private LinearLayoutManager layoutManagerFilter;

    private AlertDialog.Builder builderFilter;
    private View viewDialogFilter;
    private AlertDialog alertDialogFilter;
    private Button buttonFilter;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_lecturers);

            recyclerViewMainAdminLecturers = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Lecturers(this);
            recyclerViewMainAdminLecturers.setLayoutManager(layoutManager);
            recyclerViewMainAdminLecturers.setAdapter(adapter);



            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_lecturers, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            ArrayList<String> semesters = new ArrayList<>();
            semesters.add("2017-2018 Spring");
            semesters.add("2017-2018 Fall ayni hhgfgdf");
            semesters.add("2017-2018 Summer");
            semesters.add("2018-2019 Fall");
            semesters.add("2018-2019 Spring");
            semesters.add("2018-2019 Summer");
            semesters.add("2019-2020 Fall");
            semesters.add("2019-2020 Spring");
            semesters.add("2019-2020 Summer");
            semesters.add("2020-2021 Fall");
            semesters.add("2020-2021 Spring");
            semesters.add("2020-2021 Summer");
            // TODO input olarak gelecek.

            Spinner spinner = (Spinner) viewDialog.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, semesters);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);




            buttonFilter = (Button) findViewById(R.id.button_filter_empty_closed);
            buttonFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogFilter.show();
                }
            });

            builderFilter = new AlertDialog.Builder(this);
            viewDialogFilter = this.getLayoutInflater().inflate(R.layout.dialog_filter_department, null);
            builderFilter.setView(viewDialogFilter);
            alertDialogFilter = builderFilter.create();
            alertDialogFilter.setCancelable(false);
            alertDialogFilter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            recyclerViewFilter = viewDialogFilter.findViewById(R.id.recyclerView);
            layoutManagerFilter = new LinearLayoutManager(this);

            adapterFilter = new RecyclerViewAdapter_Filter_Department(this);
            recyclerViewFilter.setLayoutManager(layoutManagerFilter);
            recyclerViewFilter.setAdapter(adapterFilter);






            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_lecturers).setChecked(true);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Lecturers class' onCreate method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
        startActivity(i);
    }

    public void goToDeptAdmins(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
        startActivity(i);
    }

    public void goToLecturers(MenuItem item) {
        // nothing to do
    }

    public void goToStudents(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
        startActivity(i);
    }

    public void goToSemesters(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
        startActivity(i);
    }
}

