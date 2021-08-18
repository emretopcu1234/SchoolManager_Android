package com.emretopcu.schoolmanager.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


// TODO username sembolüne basılınca menü açılacak.
// TODO swipetorefresh!!!


// TODO dialog kısımlarındaki ok butonları için input validation kısımları yapılacak. ona göre butonun rengi siyahlaşacak.
// TODO dialog kısımlarındaki edittext kısımlarındaki validationlar yapılacak.
// (date için ay girilince otomatik " / " karakteri eklenecek, course code için otomatik olarak bölümün kodu başa eklenecek vb)
// TODO dialog kısımlarındaki text'ler için düzenleme yapılacak. (operations research --> Operations Research, emre topcu --> Emre TOPÇU vb)
// TODO checkboxlar enabled iken o satıra basılması durumunda işaretle/işareti kaldır (tam olarak kutucuğa basılmasına zorlamamak için)
// TODO recyclerview'da onscrolllistener (tek seferde tüm datayı çekmemek, aşağı kaydırıldıkça yüklemek için)



public class Activity_Initial extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Dept_Admins.class);
        startActivity(i);


    }


}
