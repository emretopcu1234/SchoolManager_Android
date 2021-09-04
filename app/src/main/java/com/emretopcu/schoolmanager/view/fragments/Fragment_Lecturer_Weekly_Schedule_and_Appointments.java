package com.emretopcu.schoolmanager.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.Common_Variables_View;

public class Fragment_Lecturer_Weekly_Schedule_and_Appointments extends Fragment {

    private View view;
    private TextView textViewDay1;
    private TextView textViewDay2;
    private Button[] buttonDay1Hours = new Button[Common_Variables_View.NUMBER_OF_LECTURE_HOURS_ON_A_DAY];
    private Button[] buttonDay2Hours = new Button[Common_Variables_View.NUMBER_OF_LECTURE_HOURS_ON_A_DAY];


    public Fragment_Lecturer_Weekly_Schedule_and_Appointments() {
        super(R.layout.fragment_lecturer_weekly_schedule_and_appointments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{
            view = inflater.inflate(R.layout.fragment_lecturer_weekly_schedule_and_appointments, container, false);

            textViewDay1 = view.findViewById(R.id.textView_day1);
            buttonDay1Hours[0] = view.findViewById(R.id.button_day1_hour1);
            buttonDay1Hours[1] = view.findViewById(R.id.button_day1_hour2);
            buttonDay1Hours[2] = view.findViewById(R.id.button_day1_hour3);
            buttonDay1Hours[3] = view.findViewById(R.id.button_day1_hour4);
            buttonDay1Hours[4] = view.findViewById(R.id.button_day1_hour5);
            buttonDay1Hours[5] = view.findViewById(R.id.button_day1_hour6);
            buttonDay1Hours[6] = view.findViewById(R.id.button_day1_hour7);
            buttonDay1Hours[7] = view.findViewById(R.id.button_day1_hour8);
            buttonDay1Hours[8] = view.findViewById(R.id.button_day1_hour9);
            textViewDay2 = view.findViewById(R.id.textView_day2);
            buttonDay2Hours[0] = view.findViewById(R.id.button_day2_hour1);
            buttonDay2Hours[1] = view.findViewById(R.id.button_day2_hour2);
            buttonDay2Hours[2] = view.findViewById(R.id.button_day2_hour3);
            buttonDay2Hours[3] = view.findViewById(R.id.button_day2_hour4);
            buttonDay2Hours[4] = view.findViewById(R.id.button_day2_hour5);
            buttonDay2Hours[5] = view.findViewById(R.id.button_day2_hour6);
            buttonDay2Hours[6] = view.findViewById(R.id.button_day2_hour7);
            buttonDay2Hours[7] = view.findViewById(R.id.button_day2_hour8);
            buttonDay2Hours[8] = view.findViewById(R.id.button_day2_hour9);


            // TODO tüm butonlar için eklenecek.
            registerForContextMenu(buttonDay2Hours[0]);
            registerForContextMenu(buttonDay2Hours[1]);

            return view;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_Lecturer_Weekly_Schedule_and_Appointments class' onCreateView method.");
            return null;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        if(v == buttonDay2Hours[0]){
            inflater.inflate(R.menu.menu_lecturer_add_appointment, menu);
        }
        else if(v == buttonDay2Hours[1]){
            inflater.inflate(R.menu.menu_lecturer_edit_or_delete_appointment, menu);
        }
    }
}


