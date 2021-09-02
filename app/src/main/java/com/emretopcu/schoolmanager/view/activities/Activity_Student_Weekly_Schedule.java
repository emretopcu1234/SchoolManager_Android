package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.emretopcu.schoolmanager.R;

public class Activity_Student_Weekly_Schedule extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_student_weekly_schedule);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Student_Weekly_Schedule class' onCreate method.");
        }


    }
}



