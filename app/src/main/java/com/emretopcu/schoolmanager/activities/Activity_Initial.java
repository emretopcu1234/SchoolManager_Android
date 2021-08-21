package com.emretopcu.schoolmanager.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



// TODO main admin eğer herhangi bir şeyi (student, dept admin vb) edit etmek isterse, açılacak dialogda id kısmı disabled olacak.
// TODO herhangi bir şey siliniyorsa onay sorusu dialogu çıkar.
// TODO dialog kısımlarındaki ok butonları için input validation kısımları yapılacak. ona göre butonun rengi siyahlaşacak.
// TODO dialog kısımlarındaki edittext kısımlarındaki validationlar yapılacak.
// (date için ay girilince otomatik " / " karakteri eklenecek, course code için otomatik olarak bölümün kodu başa eklenecek vb)
// TODO dialog kısımlarındaki text'ler için düzenleme yapılacak. (operations research --> Operations Research, emre topcu --> Emre TOPÇU vb)
// TODO checkboxlar enabled iken o satıra basılması durumunda işaretle/işareti kaldır (tam olarak kutucuğa basılmasına zorlamamak için)
// TODO recyclerview'da onscrolllistener (tek seferde tüm datayı çekmemek, aşağı kaydırıldıkça yüklemek için)
// TODO ayrıca scroll edilince progress bar da gösterilecek     https://stackoverflow.com/questions/28741645/how-to-implement-onscrolllistener-to-a-listview/28742473
// TODO weekly schedule ve appointment kısımlarında spinner'da sadece içinde bulunulan dönem seçili olacak, diğer seçenekler çıkmayacak.
// TODO push notification!!!



public class Activity_Initial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Students.class);
        startActivity(i);

    }

}
