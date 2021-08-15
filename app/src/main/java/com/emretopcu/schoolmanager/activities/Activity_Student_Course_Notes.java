package com.emretopcu.schoolmanager.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Student_Course_Notes;

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





