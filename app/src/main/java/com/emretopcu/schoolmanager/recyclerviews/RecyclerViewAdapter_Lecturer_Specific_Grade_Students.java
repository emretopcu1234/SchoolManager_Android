package com.emretopcu.schoolmanager.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Lecturer_Specific_Grade_Students extends RecyclerView.Adapter<ViewHolder_LSGS> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Specific_Grade_Students(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Grade_Students class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LSGS onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_specific_grade_students, parent, false);
            ViewHolder_LSGS holder = new ViewHolder_Lecturer_Specific_Grade_Students(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Grade_Students class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LSGS viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Specific_Grade_Students holder = (ViewHolder_Lecturer_Specific_Grade_Students) viewHolder;
            holder.checkBox.setChecked(false);
            holder.textViewId.setText("30016");
            holder.textViewName.setText("Ahmet Mehmet");
            holder.textViewSurname.setText("KAYIŞOĞLU");
            holder.textViewGrade.setText("45.5");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Grade_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 20;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Grade_Students class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Specific_Grade_Students class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LSGS extends RecyclerView.ViewHolder {
    public ViewHolder_LSGS(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Specific_Grade_Students extends ViewHolder_LSGS {

    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewGrade;

    public ViewHolder_Lecturer_Specific_Grade_Students(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewGrade = view.findViewById(R.id.textView_grade);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Specific_Grade_Students class' constructor method.");
        }

    }
}




