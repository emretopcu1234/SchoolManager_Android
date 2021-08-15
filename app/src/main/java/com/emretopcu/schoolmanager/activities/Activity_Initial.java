package com.emretopcu.schoolmanager.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



// TODO filtre yerlerine edittext kısımları eklenecek. (edittext değil de ayrı bi dialog gerekecek gibi)
// TODO username sembolüne basılınca menü açılacak.
// TODO dialog kısımlarına error textfieldları eklenecek.
// TODO login page!!!
// TODO swipetorefresh!!!



// TODO dialog kısımlarındaki ok butonları için input validation kısımları yapılacak. ona göre butonun rengi siyahlaşacak.
// TODO dialog kısımlarındaki edittext kısımlarındaki validationlar yapılacak.
// (date için ay girilince otomatik " / " karakteri eklenecek, course code için otomatik olarak bölümün kodu başa eklenecek vb)
// TODO dialog kısımlarındaki text'ler için düzenleme yapılacak. (operations research --> Operations Research, emre topcu --> Emre TOPÇU vb)



public class Activity_Initial extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Courses.class);
        startActivity(i);


    }


}
