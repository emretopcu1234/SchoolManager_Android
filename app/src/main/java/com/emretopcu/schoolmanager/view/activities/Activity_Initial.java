package com.emretopcu.schoolmanager.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.model.Shared_Prefs;
import com.emretopcu.schoolmanager.viewmodel.enums.E_Successful_Unsuccessful_NoStatement;
import com.emretopcu.schoolmanager.viewmodel.enums.loginProcess.E_Person_Type;
import com.emretopcu.schoolmanager.viewmodel.vm.VM_Login_Process;

// TODO UNUTMA!!! ONCREATE'TE SADECE ATAMALAR YAPILIR, ONRESUME'DA SETTINGLER DUZENLENIR (VISIBILITY VB)!!!!!

// TODO UNUTMA!!! GENELDE LIVEDATA'LAR ONRESUME'DAN ONCE ISLENDIGI ICIN, ONA GORE HAREKET ET.
// (MESELA LIVEDATA'DA ACTIVITY YENIDEN CAGIRILINCA TOAST CIKIYOR
// AMA ASLINDA BIZ BUNU ISTEMIYORUZ, SADECE GEREKLI YERLERDE CIKMASINI ISTIYORUZ, BU YUZDEN ONRESUME'DA O TOAST'U CANCEL EDIYORUZ.)

// AMA LIVEDATA'NIN ISLENDIGI YERDE COK PROCESS VAR BELKI BAZI KISIMLARI ONRESUME'DAN SONRA ISLENEBILIR
// BU DURUMDA BIR BOOLEAN YARATILIP ONRESUME'DA DEGERI DEGISTIRILIP, LIVEDATA'NIN COK PROCESS EDILEN
// YERLERINDE BU DEGISKENE BAKILABILIR

// TODO progress bar'ı gereken her yere eklemeyi unutma!

// TODO dataloaderror kısımlarını viewmodel ve view kısmı için implement et. (model'de zaten edildi.)

// TODO tüm activity, fragment vblerde (Button) ya da (TextView) cast'ları silinecek.
// TODO tüm java classlarda (recyclerviewadapter'ları unutma!!!) yapılmayan try catchler yapılacak (mesela buttona basınca oluşturulan onclick metodunda ya da overridelarda vb.)
// TODO user and semester ve/veya course code and name fragmenti içeren tüm aktiviteler Interface_General_Activity'den implemente edilecek.



// TODO main admin'de eklenecekler eklendiğinde firestore rule'unu "if auth" olarak değiştir!
// TODO main admin'e bişey eklendiğinde relogin durumunda unsuccessful olursa durumunu unutma!

// TODO dialog kısımlarındaki edittext kısımlarındaki validationlar yapılacak.
// (date için ay girilince otomatik " / " karakteri eklenecek, course code için otomatik olarak bölümün kodu başa eklenecek vb)


// TODO weekly schedule ve appointment kısımlarında spinner'da sadece içinde bulunulan dönem seçili olacak, diğer seçenekler çıkmayacak.
// TODO push notification!!!
// TODO id olmayan yerlerde duplicate data'ya izin verme. (bir section içindeki birden fazla announcement'ta ayni title olamaz, course note'ta ayni filename olamaz vb)

// TODO main admin'deki delete işlemlerinden sonra henüz eklenmemiş olan bazı collectionların ve/veya documentların da silinmesi vb gerekebilir.

public class Activity_Initial extends AppCompatActivity {

    private VM_Login_Process vmLoginProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_initial);

            Shared_Prefs.initialize(this);
            vmLoginProcess = new ViewModelProvider(this).get(VM_Login_Process.class);
            vmLoginProcess.getLoginSuccessful().observe(this, e_login_successful -> {
                try{
                    if(e_login_successful == E_Successful_Unsuccessful_NoStatement.SUCCESSFUL){
                        if(vmLoginProcess.getPersonType() == E_Person_Type.MAIN_ADMIN){
                            Intent i = new Intent(getApplicationContext(), Activity_Main_Admin_Departments.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.DEPT_ADMIN){
                            Intent i = new Intent(getApplicationContext(), Activity_Dept_Admin_Courses.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.LECTURER){
                            Intent i = new Intent(getApplicationContext(), Activity_Lecturer_Main_Page.class);
                            startActivity(i);
                        }
                        else if(vmLoginProcess.getPersonType() == E_Person_Type.STUDENT){
                            Intent i = new Intent(getApplicationContext(), Activity_Student_Main_Page.class);
                            startActivity(i);
                        }
                        else{
                            Log.d("Exception","Unexpected person type!");
                        }
                    }
                    else if(e_login_successful == E_Successful_Unsuccessful_NoStatement.UNSUCCESSFUL){
                        Intent i = new Intent(getApplicationContext(), Activity_Login_Page.class);
                        startActivity(i);
                    }
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on Activity_Initial class' vmLoginProcess.getLoginSuccessful().observe method.");
                }
            });
            vmLoginProcess.onLoginInfoRequested();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Activity_Initial class' onCreate method.");
        }
    }
}