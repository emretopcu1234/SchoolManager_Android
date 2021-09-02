package com.emretopcu.schoolmanager.view.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.recyclerviews.RecyclerViewAdapter_Lecturer_Course_Notes;

public class Activity_Lecturer_Course_Notes extends AppCompatActivity {

    private RecyclerViewAdapter_Lecturer_Course_Notes adapter;
    private RecyclerView recyclerViewLecturerCourseNotes;
    private LinearLayoutManager layoutManager;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_lecturer_course_notes);

            recyclerViewLecturerCourseNotes = findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this);

            adapter = new RecyclerViewAdapter_Lecturer_Course_Notes(this);
            recyclerViewLecturerCourseNotes.setLayoutManager(layoutManager);
            recyclerViewLecturerCourseNotes.setAdapter(adapter);

            ActivityResultLauncher someActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri uri) {
//                            result = uri.getPath();
//                            int cut = result.lastIndexOf('/');
//                            if (cut != -1) {
//                                result = result.substring(cut + 1);   ---> filename ismini almak için
//                            }
                            button.setText(uri.getPath());
                        }
                    });


            button = (Button) findViewById(R.id.button_add_delete);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    someActivityResultLauncher.launch("*/*");
                }
            });


        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Lecturer_Course_Notes class' onCreate method.");
        }


    }
}




