package com.emretopcu.schoolmanager.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Semesters;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Activity_Main_Admin_Semesters extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Semesters adapter;
    private RecyclerView recyclerViewMainAdminSemesters;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;


    private BottomNavigationView bottomNavigationView;

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








            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_semesters).setChecked(true);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Semesters class' onCreate method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
        startActivity(i);
    }

    public void goToDeptAdmins(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
        startActivity(i);
    }

    public void goToLecturers(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Lecturers.class);
        startActivity(i);
    }

    public void goToStudents(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
        startActivity(i);
    }

    public void goToSemesters(MenuItem item) {
        // nothing to do
    }
}


