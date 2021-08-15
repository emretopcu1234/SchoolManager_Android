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
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Courses;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Announcements;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Course_Notes;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;

public class Activity_Lecturer_Course_Notes extends AppCompatActivity {

    private RecyclerViewAdapter_Lecturer_Course_Notes adapter;
    private RecyclerView recyclerViewLecturerCourseNotes;
    private LinearLayoutManager layoutManager;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_course_notes);

            recyclerViewLecturerCourseNotes = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Lecturer_Course_Notes(this);
            recyclerViewLecturerCourseNotes.setLayoutManager(layoutManager);
            recyclerViewLecturerCourseNotes.setAdapter(adapter);

            ActivityResultLauncher someActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri uri) {
//                            result = uri.getPath();
//                            int cut = result.lastIndexOf('/');
//                            if (cut != -1) {
//                                result = result.substring(cut + 1);   ---> filename ismini almak için
//                            }
                            button.setText(uri.getPath());
                        }
                    });


            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    someActivityResultLauncher.launch("*/*");
                }
            });


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Lecturer_Course_Notes class' onCreate method.");
        }


    }
}




