package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests extends RecyclerView.Adapter<ViewHolder_LSAR> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LSAR onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_specific_appointment_requests, parent, false);
            ViewHolder_LSAR holder = new ViewHolder_Lecturer_Specific_Appointment_Requests(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LSAR viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Specific_Appointment_Requests holder = (ViewHolder_Lecturer_Specific_Appointment_Requests) viewHolder;
            holder.textViewStudent.setText("19356 - Emre TOPÇU");
            holder.buttonAccept.setVisibility(View.VISIBLE);
            holder.buttonReject.setVisibility(View.VISIBLE);
            holder.buttonAccepted.setVisibility(View.INVISIBLE);
            holder.buttonRejected.setVisibility(View.INVISIBLE);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 15;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Appointment_Requests class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LSAR extends RecyclerView.ViewHolder {
    public ViewHolder_LSAR(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Specific_Appointment_Requests extends ViewHolder_LSAR {

    public TextView textViewStudent;
    public Button buttonReject;
    public Button buttonAccept;
    public Button buttonRejected;
    public Button buttonAccepted;

    public ViewHolder_Lecturer_Specific_Appointment_Requests(View view) {
        super(view);
        try{
            textViewStudent = view.findViewById(R.id.textView_student);
            buttonReject = view.findViewById(R.id.button_reject);
            buttonAccept = view.findViewById(R.id.button_accept);
            buttonRejected = view.findViewById(R.id.button_rejected);
            buttonAccepted = view.findViewById(R.id.button_accepted);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Specific_Appointment_Requests class' constructor method.");
        }

    }
}






