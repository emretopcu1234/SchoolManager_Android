package com.emretopcu.schoolmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Fragment_User_and_Semester extends Fragment {

    View view;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    TextView textView;
    Button button;

    public Fragment_User_and_Semester() {
        super(R.layout.fragment_user_and_semester);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try{
            view = inflater.inflate(R.layout.fragment_user_and_semester, container, false);

            ArrayList<String> semesters = new ArrayList<>();
            semesters.add("All Semesters");
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


            spinner = (Spinner) view.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayAdapter = new ArrayAdapter(getActivity(),R.layout.spinner_type_fragment_user_and_semester, semesters);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);

            textView = (TextView) view.findViewById(R.id.textView);
            button = (Button) view.findViewById(R.id.button);

            return view;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' onCreateView method.");
            return null;
        }


    }
}