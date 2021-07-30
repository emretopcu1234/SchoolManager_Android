package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.emretopcu.schoolmanager.R;

public class Activity_Student_Appointments extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_student_appointments);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Student_Appointments class' onCreate method.");
        }


    }
}
