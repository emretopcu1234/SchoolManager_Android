package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;

public class Activity_Main_Admin_Lecturers extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Lecturers adapter;
    private RecyclerView recyclerViewMainAdminLecturers;
    private LinearLayoutManager layoutManager;


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


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Lecturers class' onCreate method.");
        }


    }
}

