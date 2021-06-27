package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;

public class Activity_Main_Admin_Dept_Admins extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Dept_Admins adapter;
    private RecyclerView recyclerViewMainAdminDeptAdmins;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_dept_admins);

            recyclerViewMainAdminDeptAdmins = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Dept_Admins(this);
            recyclerViewMainAdminDeptAdmins.setLayoutManager(layoutManager);
            recyclerViewMainAdminDeptAdmins.setAdapter(adapter);


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Dept_Admins class' onCreate method.");
        }


    }
}
