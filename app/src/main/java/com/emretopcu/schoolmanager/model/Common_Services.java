package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.interfaces.Interface_Student;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;

public class Common_Services {

    public static String convertUnprocessedSemester(String unprocessedSemester){
        try{
            String semesterType;
            if(unprocessedSemester.endsWith("Fall")){
                semesterType = "fall";
            }
            else if(unprocessedSemester.endsWith("Spring")){
                semesterType = "spring";
            }
            else if(unprocessedSemester.endsWith("Summer")){
                semesterType = "summer";
            }
            else{
                Log.d("Exception", "Unexpected semester type!");
                return null;
            }
            int startingYear = Integer.parseInt(unprocessedSemester.substring(0,4));
            return semesterType + startingYear + (startingYear+1);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' convertUnprocessedSemester method.");
            return null;
        }
    }

    public static String convertProcessedSemester(String processedSemester){
        try{
            String semesterType;
            if(processedSemester.startsWith("fall")){
                semesterType = "Fall";
            }
            else if(processedSemester.startsWith("spring")){
                semesterType = "Spring";
            }
            else if(processedSemester.endsWith("summer")){
                semesterType = "Summer";
            }
            else{
                Log.d("Exception", "Unexpected semester type!");
                return null;
            }
            int endingYear = Integer.parseInt(processedSemester.substring(processedSemester.length()-4));
            return (endingYear-1) + "-" + endingYear + " " + semesterType;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' convertProcessedSemester method.");
            return null;
        }
    }
}
