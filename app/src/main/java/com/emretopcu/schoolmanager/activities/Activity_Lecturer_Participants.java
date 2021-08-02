package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Participants;

public class Activity_Lecturer_Participants extends AppCompatActivity {

    private RecyclerViewAdapter_Lecturer_Participants adapter;
    private RecyclerView recyclerViewLecturerParticipants;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_participants);

            recyclerViewLecturerParticipants = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Lecturer_Participants(this);
            recyclerViewLecturerParticipants.setLayoutManager(layoutManager);
            recyclerViewLecturerParticipants.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Lecturer_Participants class' onCreate method.");
        }


    }
}


