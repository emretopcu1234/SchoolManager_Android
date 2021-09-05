package com.emretopcu.schoolmanager.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.viewmodel.VM_Login_Process;


public class Activity_Login_Page extends AppCompatActivity {

    private VM_Login_Process viewModel;
    private TextView tv;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_login_page);

//            tv = findViewById(R.id.editText_id);
//            button = findViewById(R.id.button_login);
//
//            viewModel = new ViewModelProvider(this).get(VM_Activity_Login_Page.class);
//
//            final Observer<Student> studentObserver = new Observer<Student>() {
//                @Override
//                public void onChanged(@Nullable final Student newStudent) {
//                    // Update the UI, in this case, a TextView.
//                    tv.setText(newStudent.getName());
//                }
//            };
//
//            viewModel.getStudent().observe(this, studentObserver);
//
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewModel.refresh();
//                }
//            });



        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Login_Page class' onCreate method.");

            //TODO login id ya da password kısmı boş bırakılırsa uyarı yazısı oluştur. (textView_warning'de)
        }

    }
}
