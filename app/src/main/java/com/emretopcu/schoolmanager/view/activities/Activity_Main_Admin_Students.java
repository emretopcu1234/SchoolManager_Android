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
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Students;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Activity_Main_Admin_Students extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Students adapter;
    private RecyclerView recyclerViewMainAdminStudents;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;

    private BottomNavigationView bottomNavigationView;

    // TODO bilgi amaçlı: studentlar içinde 30011 ee'den ce'ye, 30028 ee'den me'ye, 30072 phy'den ee'ye bölümü değişti.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_students);

            recyclerViewMainAdminStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Students(this);
            recyclerViewMainAdminStudents.setLayoutManager(layoutManager);
            recyclerViewMainAdminStudents.setAdapter(adapter);




            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_students, null);
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






            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_students).setChecked(true);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Students class' onCreate method.");
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
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
        startActivity(i);
    }

    public void goToStudents(MenuItem item) {
        // nothing to do
    }

    public void goToSemesters(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
        startActivity(i);
    }
}

