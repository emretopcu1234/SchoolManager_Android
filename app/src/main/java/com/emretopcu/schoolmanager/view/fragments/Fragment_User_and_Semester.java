package com.emretopcu.schoolmanager.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;

import java.util.ArrayList;

public class Fragment_User_and_Semester extends Fragment implements Interface_Fragment_User_and_Semester {

    private View view;
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private TextView textView;
    private Button button;

    private Interface_General_Activity interfaceUserAndSemester;

    public Fragment_User_and_Semester(Interface_General_Activity interfaceUserAndSemester) {
        super(R.layout.fragment_user_and_semester);
        try{
            this.interfaceUserAndSemester = interfaceUserAndSemester;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' constructor method.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            view = inflater.inflate(R.layout.fragment_user_and_semester, container, false);

            ArrayList<String> semesters = new ArrayList<>();
            semesters.add("2017-2018 Fall");
            semesters.add("2017-2018 Spring");
            semesters.add("2017-2018 Summer");
            semesters.add("2018-2019 Fall");
            semesters.add("2018-2019 Spring");
            semesters.add("2018-2019 Summer");
            semesters.add("2019-2020 Fall");
            semesters.add("2019-2020 Spring");
            semesters.add("2019-2020 Summer");
            semesters.add("2020-2021 Fall");
            semesters.add("2020-2021 Spring");
            semesters.add("2020-2021 Summer");
            // TODO input olarak gelecek.


            spinner = view.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    interfaceUserAndSemester.onSemesterChanged(spinner.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayAdapter = new ArrayAdapter(getActivity(),R.layout.spinner_type_fragment_user_and_semester, semesters);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

            textView = view.findViewById(R.id.textView);
            button = view.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_user, popup.getMenu());
                    popup.show();
                }
            });

            return view;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' onCreateView method.");
            return null;
        }
    }

    @Override
    public void setSpinnerVisibility(int visibility) {
        spinner.setVisibility(visibility);
    }
}