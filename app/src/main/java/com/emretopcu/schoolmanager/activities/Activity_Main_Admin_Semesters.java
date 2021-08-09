package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Semesters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Activity_Main_Admin_Semesters extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Semesters adapter;
    private RecyclerView recyclerViewMainAdminSemesters;
    private LinearLayoutManager layoutManager;


    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_semesters);


            // TODO spinner invisible olacak!!!!!!!!!!!!

            recyclerViewMainAdminSemesters = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Semesters(this);
            recyclerViewMainAdminSemesters.setLayoutManager(layoutManager);
            recyclerViewMainAdminSemesters.setAdapter(adapter);


            button = (Button) findViewById(R.id.button_add);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_semesters, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));








        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onCreate method.");
        }


    }
}


