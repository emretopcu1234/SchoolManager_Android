package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Students;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Students;

public class Activity_Dept_Admin_Students extends AppCompatActivity {

    private RecyclerViewAdapter_Dept_Admin_Students adapter;
    private RecyclerView recyclerViewDeptAdminStudents;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_students);

            recyclerViewDeptAdminStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Dept_Admin_Students(this);
            recyclerViewDeptAdminStudents.setLayoutManager(layoutManager);
            recyclerViewDeptAdminStudents.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Students class' onCreate method.");
        }


    }
}

