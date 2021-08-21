package com.emretopcu.schoolmanager.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests extends RecyclerView.Adapter<ViewHolder_LSAAR> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LSAAR onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_specific_appointment_accepted_requests, parent, false);
            ViewHolder_LSAAR holder = new ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LSAAR viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests holder = (ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests) viewHolder;
            holder.textViewStudent.setText("19356 - Emre TOPÇU");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 15;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Accepted_Requests class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LSAAR extends RecyclerView.ViewHolder {
    public ViewHolder_LSAAR(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests extends ViewHolder_LSAAR {

    public TextView textViewStudent;

    public ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests(View view) {
        super(view);
        try{
            textViewStudent = view.findViewById(R.id.textView_student);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Specific_Appointment_Accepted_Requests class' constructor method.");
        }

    }
}





