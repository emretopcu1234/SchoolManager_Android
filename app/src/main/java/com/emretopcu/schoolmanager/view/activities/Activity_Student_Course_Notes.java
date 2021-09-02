package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Student_Course_Notes;

public class Activity_Student_Course_Notes extends AppCompatActivity {

    private RecyclerViewAdapter_Student_Course_Notes adapter;
    private RecyclerView recyclerViewStudentCourseNotes;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_student_course_notes);

            recyclerViewStudentCourseNotes = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Student_Course_Notes(this);
            recyclerViewStudentCourseNotes.setLayoutManager(layoutManager);
            recyclerViewStudentCourseNotes.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Student_Course_Notes class' onCreate method.");
        }


    }
}





