package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;

public class Activity_Dept_Admin_Lecturers extends AppCompatActivity {

    private RecyclerViewAdapter_Dept_Admin_Lecturers adapter;
    private RecyclerView recyclerViewDeptAdminLecturers;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_lecturers);

            recyclerViewDeptAdminLecturers = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Dept_Admin_Lecturers(this);
            recyclerViewDeptAdminLecturers.setLayoutManager(layoutManager);
            recyclerViewDeptAdminLecturers.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Lecturers class' onCreate method.");
        }


    }
}


