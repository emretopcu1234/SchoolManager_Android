package com.emretopcu.schoolmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Specific_Course_Students;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Lecturer_Specific_Grade_Students;

import java.util.ArrayList;

public class Activity_Lecturer_Specific_Grade extends AppCompatActivity {

    private RecyclerViewAdapter_Lecturer_Specific_Grade_Students adapter;
    private RecyclerView recyclerViewLecturerSpecificGradeStudents;
    private LinearLayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_specific_grade);


            recyclerViewLecturerSpecificGradeStudents = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Lecturer_Specific_Grade_Students(this);
            recyclerViewLecturerSpecificGradeStudents.setLayoutManager(layoutManager);
            recyclerViewLecturerSpecificGradeStudents.setAdapter(adapter);




            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_lecturer_specific_grade, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



            ArrayList<String> students = new ArrayList<>();
            students.add("1938794 - Emre Mehmet DENKGENCER");
            students.add("2020234 - İnsan İsmi Soyismi");
            students.add("1846573 - Acaba Soyisim");
            students.add("1919230 - Adım Işıklı");
            // TODO input olarak gelecek.

            Spinner spinnerStudent= (Spinner) viewDialog.findViewById(R.id.spinner);
            spinnerStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter arrayAdapterStudent = new ArrayAdapter(getApplicationContext(),R.layout.spinner_type_department_and_student, students);
            arrayAdapterStudent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStudent.setAdapter(arrayAdapterStudent);



        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Specific_Course class' onCreate method.");
        }


    }
}




