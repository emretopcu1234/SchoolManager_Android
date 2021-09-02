package com.emretopcu.schoolmanager.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.emretopcu.schoolmanager.view.fragments.Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Main_Admin_Departments extends AppCompatActivity implements Interface_General_Activity {

    private Interface_Fragment_User_and_Semester fragmentUserAndSemester;

    private RecyclerViewAdapter_Main_Admin_Departments adapter;
    private RecyclerView recyclerViewMainAdminDepartments;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;

    private BottomNavigationView bottomNavigationView;


    // TODO department name değişirse hem semesterconditions'taki son dönem tablosunda hem de departments collectionında güncelle.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_departments);

            fragmentUserAndSemester = new Fragment_User_and_Semester(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_main_admin_user_and_semester, (Fragment) fragmentUserAndSemester, null)
                    .commit();

            recyclerViewMainAdminDepartments = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this);
            recyclerViewMainAdminDepartments.setLayoutManager(layoutManager);
            recyclerViewMainAdminDepartments.setAdapter(adapter);

            button = findViewById(R.id.button_add_delete);
            button.setOnClickListener(v -> alertDialog.show());

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_departments, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));









            bottomNavigationView = findViewById(R.id.bottom_navigation_main_admin);
            bottomNavigationView.getMenu().findItem(R.id.menu_main_admin_departments).setChecked(true);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onCreate method.");
        }
    }

    public void goToDepartments(MenuItem item) {
        // nothing to do
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
        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Semesters.class);
        startActivity(i);
    }

    @Override
    public void onSemesterChanged(String selectedSemester) {
        Log.d("deneme", "sth happened");
    }

//    @Override
//    public void onSemesterChanged(String selectedSemester) {
//        Log.d("deneme","sth happened");
//    }
}