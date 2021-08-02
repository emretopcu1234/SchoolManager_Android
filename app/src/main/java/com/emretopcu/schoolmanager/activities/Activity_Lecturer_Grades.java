package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Grades;

public class Activity_Lecturer_Grades extends AppCompatActivity {

    private RecyclerViewAdapter_Lecturer_Grades adapter;
    private RecyclerView recyclerViewLecturerGrades;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_grades);

            recyclerViewLecturerGrades = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Lecturer_Grades(this);
            recyclerViewLecturerGrades.setLayoutManager(layoutManager);
            recyclerViewLecturerGrades.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Lecturer_Grades class' onCreate method.");
        }


    }
}



