package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.emretopcu.schoolmanager.R;

public class Activity_Lecturer_Weekly_Schedule_and_Appointments extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_weekly_schedule_and_appointments);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Lecturer_Weekly_Schedule_and_Appointments class' onCreate method.");
        }


    }
}

