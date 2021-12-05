package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common_Services {

    private static DateFormat formatterOriginal = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    private static DateFormat formatterSpecified = new SimpleDateFormat("dd / MM / yyyy");

    protected static String convertUnprocessedSemester(String unprocessedSemester){
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

    protected static String convertProcessedSemester(String processedSemester){
        try{
            String semesterType;
            if(processedSemester.startsWith("fall")){
                semesterType = "Fall";
            }
            else if(processedSemester.startsWith("spring")){
                semesterType = "Spring";
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

    protected static String convertTimestampToDateString(Timestamp timestamp){
        try{
            return formatterSpecified.format(formatterOriginal.parse(timestamp.toDate().toString()));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' convertTimestampToDateString method.");
            return null;
        }
    }

    protected static Timestamp convertDateStringToTimestamp(String date){
        try{
            return new Timestamp(formatterSpecified.parse(date));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' convertDateStringToTimestamp method.");
            return null;
        }
    }

    protected static boolean isSemesterActive(Timestamp startDate, Timestamp endDate){
        try{
            Date todayDate = new Date();
            return(startDate.compareTo(new Timestamp(todayDate)) <= 0
                    && endDate.compareTo(new Timestamp(todayDate)) >= 0);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' isSemesterActive method.");
            return false;
        }
    }

    protected static boolean isSemesterFuture(Timestamp startDate){
        try{
            Date todayDate = new Date();
            return(startDate.compareTo(new Timestamp(todayDate)) >= 0);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' isSemesterFuture method.");
            return false;
        }
    }

    protected static String convertUnprocessedName(String unprocessedName){
        try{
            if(unprocessedName.length() == 0){
                return "";
            }
            StringBuilder name = new StringBuilder(Character.toString(unprocessedName.charAt(0)).toUpperCase());
            for(int i=1;i<unprocessedName.length();i++){
                if(unprocessedName.charAt(i-1) == ' '){
                    name.append(Character.toString(unprocessedName.charAt(i)).toUpperCase());
                }
                else{
                    name.append(Character.toString(unprocessedName.charAt(i)).toLowerCase());
                }
            }
            return name.toString();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' convertUnprocessedFilter method.");
            return null;
        }
    }

    protected static String specifySemesterName(String startDate, String endDate){
        try{
            String semesterName;
            int startYear = Integer.parseInt(startDate.substring(startDate.length() - 4));
            int endYear = Integer.parseInt(endDate.substring(endDate.length() - 4));
            if(startYear != endYear){
                semesterName = "fall" + startYear + endYear;
            }
            else{
                semesterName = "spring" + (startYear - 1) + endYear;
            }
            return semesterName;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' specifySemesterName method.");
            return null;
        }
    }

    protected static String getActiveSemester(){
        try{
            if(Calendar.getInstance().get(Calendar.MONTH) == 0){
                return "fall" + (Calendar.getInstance().get(Calendar.YEAR)-1) + Calendar.getInstance().get(Calendar.YEAR);
            }
            else if(Calendar.getInstance().get(Calendar.MONTH) > 0 && Calendar.getInstance().get(Calendar.MONTH) <= 5){
                return "spring" + (Calendar.getInstance().get(Calendar.YEAR)-1) + Calendar.getInstance().get(Calendar.YEAR);
            }
            else if(Calendar.getInstance().get(Calendar.MONTH) >= 8){
                return "fall" + Calendar.getInstance().get(Calendar.YEAR) + (Calendar.getInstance().get(Calendar.YEAR)+1);
            }
            return null;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Common_Services class' getActiveSemester method.");
            return null;
        }
    }
}