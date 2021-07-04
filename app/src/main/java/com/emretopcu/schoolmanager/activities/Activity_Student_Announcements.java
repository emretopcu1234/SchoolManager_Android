package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Courses;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Announcements;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Student_Announcements;

public class Activity_Student_Announcements extends AppCompatActivity {

    private RecyclerViewAdapter_Student_Announcements adapter;
    private RecyclerView recyclerViewStudentAnnouncements;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_student_announcements);

            recyclerViewStudentAnnouncements = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Student_Announcements(this);
            recyclerViewStudentAnnouncements.setLayoutManager(layoutManager);
            recyclerViewStudentAnnouncements.setAdapter(adapter);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Student_Announcements class' onCreate method.");
        }


    }
}



