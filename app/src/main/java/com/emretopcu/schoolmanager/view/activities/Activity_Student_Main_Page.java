package com.emretopcu.schoolmanager.view.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.emretopcu.schoolmanager.R;

public class Activity_Student_Main_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_student_main_page);



        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Student_Main_Page class' onCreate method.");
        }
    }
}
