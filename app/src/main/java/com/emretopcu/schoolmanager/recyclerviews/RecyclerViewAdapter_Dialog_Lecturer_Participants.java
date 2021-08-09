package com.emretopcu.schoolmanager.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Dialog_Lecturer_Participants extends RecyclerView.Adapter<ViewHolder_DLP> {

    private Context context;


    public RecyclerViewAdapter_Dialog_Lecturer_Participants(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dialog_Lecturer_Participants class' constructor method.");
        }
    }

    @Override
    public ViewHolder_DLP onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dialog_lecturer_participants, parent, false);
            ViewHolder_DLP holder = new ViewHolder_Dialog_Lecturer_Participants(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dialog_Lecturer_Participants class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DLP viewHolder, int position) {
        try{
            final ViewHolder_Dialog_Lecturer_Participants holder = (ViewHolder_Dialog_Lecturer_Participants) viewHolder;
            holder.textViewName.setText("Quiz 1");
            holder.textViewWeight.setText("15%");
            holder.textViewResult.setText("55");
            holder.textViewPoint.setText("16.5");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dialog_Lecturer_Participants class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 20;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dialog_Lecturer_Participants class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dialog_Lecturer_Participants class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DLP extends RecyclerView.ViewHolder {
    public ViewHolder_DLP(View v) {
        super(v);
    }
}

class ViewHolder_Dialog_Lecturer_Participants extends ViewHolder_DLP {

    public TextView textViewName;
    public TextView textViewWeight;
    public TextView textViewResult;
    public TextView textViewPoint;

    public ViewHolder_Dialog_Lecturer_Participants(View view) {
        super(view);
        try{
            textViewName = view.findViewById(R.id.textView_name);
            textViewWeight = view.findViewById(R.id.textView_weight);
            textViewResult = view.findViewById(R.id.textView_result);
            textViewPoint = view.findViewById(R.id.textView_point);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dialog_Lecturer_Participants class' constructor method.");
        }

    }
}





