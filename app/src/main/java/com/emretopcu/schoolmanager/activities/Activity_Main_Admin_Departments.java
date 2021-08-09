package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;

public class Activity_Main_Admin_Departments extends AppCompatActivity {

    private RecyclerViewAdapter_Main_Admin_Departments adapter;
    private RecyclerView recyclerViewMainAdminDepartments;
    private LinearLayoutManager layoutManager;


    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_main_admin_departments);

            recyclerViewMainAdminDepartments = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Main_Admin_Departments(this);
            recyclerViewMainAdminDepartments.setLayoutManager(layoutManager);
            recyclerViewMainAdminDepartments.setAdapter(adapter);


            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_main_admin_departments, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Main_Admin_Departments class' onCreate method.");
        }


    }
}