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
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Courses;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Dept_Admin_Lecturers;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Departments;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Dept_Admins;
import com.emretopcu.schoolmanager.recyclerviews.RecyclerViewAdapter_Main_Admin_Lecturers;


// TODO filtre ve arama yerlerine edittext kısımları eklenecek.
// TODO username sembolüne basılınca menü açılacak.
// TODO dialog kısımlarına error textfieldları eklenecek.
// TODO dialog kısımlarındaki ok butonları için input validation kısımları yapılacak. ona göre butonun rengi siyahlaşacak.
// TODO dialog kısımlarındaki edittext kısımlarındaki validationlar yapılacak.
// (date için ay girilince otomatik " / " karakteri eklenecek, course code için otomatik olarak bölümün kodu başa eklenecek vb)

// TODO dialog kısımlarındaki text'ler için düzenleme yapılacak. (operations research --> Operations Research, emre topcu --> Emre TOPÇU vb)

// TODO eksik layoutlar:
// student appointment'ın dialog kısmı
// lecturer appointment (yeni bir layout gerekecek), participants'ta herhangi bir öğrenciye tıklayınca açılacak öğrencinin grades dialog'u
// student ve lecturer'ın course notes kısmı (hem layout, hem dialog (ya da duruma göre başka bir şey)

// TODO login page!!!






public class Activity_Dept_Admin_Courses extends AppCompatActivity {

    private RecyclerViewAdapter_Dept_Admin_Courses adapter;
    private RecyclerView recyclerViewDeptAdminCourses;
    private LinearLayoutManager layoutManager;


    private AlertDialog.Builder builder;
    private View viewDialog;
    private AlertDialog alertDialog;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dept_admin_courses);

            recyclerViewDeptAdminCourses = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Dept_Admin_Courses(this);
            recyclerViewDeptAdminCourses.setLayoutManager(layoutManager);
            recyclerViewDeptAdminCourses.setAdapter(adapter);


            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });

            builder = new AlertDialog.Builder(this);
            viewDialog = this.getLayoutInflater().inflate(R.layout.dialog_dept_admin_courses, null);
            builder.setView(viewDialog);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Dept_Admin_Courses class' onCreate method.");
        }


    }
}


