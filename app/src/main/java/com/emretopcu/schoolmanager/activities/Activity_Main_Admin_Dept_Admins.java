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
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;

import java.util.ArrayList;

public class Activity_Main_Admin_Dept_Admins extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Dept_Admins adapter;
    private RecyclerView recyclerViewMainAdminDeptAdmins;
    private LinearLayoutManager layoutManager;


    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_dept_admins);

            recyclerViewMainAdminDeptAdmins = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this);
            recyclerViewMainAdminDeptAdmins.setLayoutManager(layoutManager);
            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);






            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_students_lecturers_dept_admins, null);
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


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onCreate method.");
        }


    }
}
