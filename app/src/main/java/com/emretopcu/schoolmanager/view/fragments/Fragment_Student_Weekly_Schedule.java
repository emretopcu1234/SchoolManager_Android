package com.emretopcu.schoolmanager.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.model.Common_Parameters_and_Variables;

public class Fragment_Student_Weekly_Schedule extends Fragment {

    View view;
    TextView textViewDay1;
    TextView textViewDay2;
    TextView[] textViewDay1Hours = new TextView[Common_Parameters_and_Variables.NUMBER_OF_LECTURE_HOURS_ON_A_DAY];
    TextView[] textViewDay2Hours = new TextView[Common_Parameters_and_Variables.NUMBER_OF_LECTURE_HOURS_ON_A_DAY];


    public Fragment_Student_Weekly_Schedule() {
        super(R.layout.fragment_student_weekly_schedule);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{
            view = inflater.inflate(R.layout.fragment_student_weekly_schedule, container, false);

            textViewDay1 = (TextView) view.findViewById(R.id.textView_day1);
            textViewDay1Hours[0] = (TextView) view.findViewById(R.id.textView_day1_hour1);
            textViewDay1Hours[1] = (TextView) view.findViewById(R.id.textView_day1_hour2);
            textViewDay1Hours[2] = (TextView) view.findViewById(R.id.textView_day1_hour3);
            textViewDay1Hours[3] = (TextView) view.findViewById(R.id.textView_day1_hour4);
            textViewDay1Hours[4] = (TextView) view.findViewById(R.id.textView_day1_hour5);
            textViewDay1Hours[5] = (TextView) view.findViewById(R.id.textView_day1_hour6);
            textViewDay1Hours[6] = (TextView) view.findViewById(R.id.textView_day1_hour7);
            textViewDay1Hours[7] = (TextView) view.findViewById(R.id.textView_day1_hour8);
            textViewDay1Hours[8] = (TextView) view.findViewById(R.id.textView_day1_hour9);
            textViewDay2 = (TextView) view.findViewById(R.id.textView_day2);
            textViewDay2Hours[0] = (TextView) view.findViewById(R.id.textView_day2_hour1);
            textViewDay2Hours[1] = (TextView) view.findViewById(R.id.textView_day2_hour2);
            textViewDay2Hours[2] = (TextView) view.findViewById(R.id.textView_day2_hour3);
            textViewDay2Hours[3] = (TextView) view.findViewById(R.id.textView_day2_hour4);
            textViewDay2Hours[4] = (TextView) view.findViewById(R.id.textView_day2_hour5);
            textViewDay2Hours[5] = (TextView) view.findViewById(R.id.textView_day2_hour6);
            textViewDay2Hours[6] = (TextView) view.findViewById(R.id.textView_day2_hour7);
            textViewDay2Hours[7] = (TextView) view.findViewById(R.id.textView_day2_hour8);
            textViewDay2Hours[8] = (TextView) view.findViewById(R.id.textView_day2_hour9);

            return view;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_Student_Weekly_Schedule class' onCreateView method.");
            return null;
        }
    }
}

