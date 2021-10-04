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

// TODO dataloaderror kısımlarını viewmodel ve model kısmı için implement et. (model'de zaten edildi.)

// TODO tüm layoutlarda add select cancel delete butonlarının rengi lightblack'ten black'e çevirilecek. (main admin kısmı için çevrildi.)
// TODO tüm layoutlardaki fragmentlar fragmentcontainerview ile değiştirilecek. (örnek: layout main admin departments)
// TODO tüm activity, fragment vblerde (Button) ya da (TextView) cast'ları silinecek.
// TODO tüm java classlarda (recyclerviewadapter'ları unutma!!!) yapılmayan try catchler yapılacak (mesela buttona basınca oluşturulan onclick metodunda ya da overridelarda vb.)
// TODO user and semester ve/veya course code and name fragmenti içeren tüm aktiviteler Interface_General_Activity'den implemente edilecek.



// TODO main admin'de eklenecekler eklendiğinde firestore rule'unu "if auth" olarak değiştir!
// TODO main admin'e bişey eklendiğinde relogin durumunda unsuccessful olursa durumunu unutma!


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
// TODO id olmayan yerlerde duplicate data'ya izin verme. (bir section içindeki birden fazla announcement'ta ayni title olamaz, course note'ta ayni filename olamaz vb)

// TODO vmmainadmin'deki todo yazılı metodları doldur. (semester eklerken extreme datalar girilmesi durumu için)

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


//        Intent i = new Intent(getApplicationContext(), Activity_Login_Page.class);
//        i.putExtra("from","initial");
//        startActivity(i);





//            CollectionReference semesterRef = FirebaseFirestore.getInstance().collection("semesters");
//
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//
//            Map<String, Object> docData = new HashMap<>();
//
//            Date date = dateFormat.parse("14/06/2021 00:00:00");
//            Calendar c = Calendar.getInstance();
//
//
//            for(int i=1; i<=6; i++){    // üst sınırı manuel vermek yerine semester'ın bitiş tarihine göre while koşuluyla bak.
//                docData.put("startDate", date);
//                c.setTime(date);
//                c.add(Calendar.DATE, 7);
//                date = c.getTime();
//                docData.put("endDate", date);
//
//                semesterRef.document("summer20202021")
//                        .collection("weeks")
//                        .document("week" + i)
//                        .set(docData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//            }










//            ArrayList<Integer> ids = new ArrayList<>();
//            for(int i=0; i<360; i++){
//                ids.add(30001 + i);
//            }
//
//            ArrayList<String> names = new ArrayList<>();
//
//            names.add("Pippa");
//            names.add("Brian");
//            names.add("Audrey");
//            names.add("Max Ave");
//            names.add("Adrian");
//            names.add("Bernadet");
//            names.add("Peter");
//            names.add("Jacob");
//            names.add("Melanie");
//            names.add("Abigail");
//            names.add("Joan	San");
//            names.add("Madelein");
//            names.add("Pippa");
//            names.add("Sue Gra");
//            names.add("Abigail");
//            names.add("Felicity");
//            names.add("Jane");
//            names.add("Boris");
//            names.add("Deirdre");
//            names.add("Kimberly");
//            names.add("Piers");
//            names.add("Jasmine");
//            names.add("Michael");
//            names.add("Felicity");
//            names.add("Owen Bon");
//            names.add("Faith");
//            names.add("Alexander");
//            names.add("Leah	Jacob");
//            names.add("Dylan");
//            names.add("Neil	Reim");
//            names.add("Angela");
//            names.add("Joshua");
//            names.add("Amanda");
//            names.add("Samantha");
//            names.add("Oliver");
//
//
//
//            ArrayList<String> surnames = new ArrayList<>();
//
//            surnames.add("Hart");
//            surnames.add("MacDonald");
//            surnames.add("Morgan");
//            surnames.add("Avery");
//            surnames.add("Wallace");
//            surnames.add("Hardacre");
//            surnames.add("Pullman");
//            surnames.add("Hemmings");
//            surnames.add("Scott");
//            surnames.add("Hemmings");
//            surnames.add("Sanderson");
//            surnames.add("Cornish");
//            surnames.add("Mackenzie");
//            surnames.add("Gray");
//            surnames.add("Hamilton");
//            surnames.add("Clark");
//            surnames.add("Springer");
//            surnames.add("Edmunds");
//            surnames.add("Hodges");
//            surnames.add("Walker");
//            surnames.add("Johnston");
//            surnames.add("Bailey");
//            surnames.add("Clark");
//            surnames.add("Knox");
//            surnames.add("Bond");
//            surnames.add("Hughes");
//            surnames.add("Terry");
//            surnames.add("Jackson");
//            surnames.add("Burgess");
//            surnames.add("Reid");
//            surnames.add("Paige");
//            surnames.add("Alsop");
//            surnames.add("Cornish");
//            surnames.add("Turner");
//            surnames.add("Vaughan");
//
//
//
//
//
//            CollectionReference deptAdminsRef = FirebaseFirestore.getInstance().collection("students");
//            Map<String, Object> docData = new HashMap<>();
//
//            Random random = new Random();
//            int index;
//            for(int i=0;i<360;i++){
//                index = random.nextInt(35);
//                docData.put("name",names.get(index));
//                index = random.nextInt(35);
//                docData.put("surname",surnames.get(index));
//                deptAdminsRef.document(ids.get(i).toString())
//                        .set(docData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//            }






//            CollectionReference semesterConditionsRef = FirebaseFirestore.getInstance().collection("semesterConditions");
//            Map<String, Object> docData = new HashMap<>();
//
//            ArrayList<String> depts = new ArrayList<>();
//            depts.add("ce");
//            depts.add("chem");
//            depts.add("ee");
//            depts.add("ie");
//            depts.add("me");
//            depts.add("phi");
//            depts.add("phy");
//            depts.add("psy");
//
//
//            ArrayList<Integer> ids = new ArrayList<>();
//            for(int i=0; i<360; i++){
//                ids.add(30001 + i);
//            }
//
//
//            Random random = new Random();
//            int index;
//            int[] totals = new int[8];
//            for(int i=0; i<8;i++){
//                totals[i] = 0;
//            }
//
//            for(int i=0;i<360;i++){
//                index = random.nextInt(8);
//                totals[index]++;
//                docData.put("deptId",depts.get(index));
//
//                semesterConditionsRef.document("fall20202021")
//                        .collection("students")
//                        .document(ids.get(i).toString())
//                        .set(docData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//
//                docData.put("deptId",depts.get(index));
//                semesterConditionsRef.document("spring20202021")
//                        .collection("students")
//                        .document(ids.get(i).toString())
//                        .set(docData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//            }
//
//            for(int i=0;i<8;i++){
//                Log.d("deneme","totals " + i + " " + totals[i]);
//            }
        
    }

}
