package com.emretopcu.schoolmanager.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.emretopcu.schoolmanager.R;

public class Activity_Login_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_login_page);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Activity_Login_Page class' onCreate method.");

            //TODO login id ya da password kısmı boş bırakılırsa uyarı yazısı oluştur. (textView_warning'de)
        }

    }
}
