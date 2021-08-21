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

public class RecyclerViewAdapter_Lecturer_Grades extends RecyclerView.Adapter<ViewHolder_LG> {

    private Context context;

    public RecyclerViewAdapter_Lecturer_Grades(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Grades class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LG onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_grades, parent, false);
            ViewHolder_LG holder = new ViewHolder_Lecturer_Grades(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Grades class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LG viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Grades holder = (ViewHolder_Lecturer_Grades) viewHolder;
            holder.checkBox.setChecked(false);
            holder.textViewName.setText("Midterm 1");
            holder.textViewWeight.setText("35%");
            holder.textViewAverageResult.setText("46.3");
            holder.textViewAveragePoint.setText("24.61");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Grades class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Grades class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Grades class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LG extends RecyclerView.ViewHolder {
    public ViewHolder_LG(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Grades extends ViewHolder_LG {

    public CheckBox checkBox;
    public TextView textViewName;
    public TextView textViewWeight;
    public TextView textViewAverageResult;
    public TextView textViewAveragePoint;

    public ViewHolder_Lecturer_Grades(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewName = view.findViewById(R.id.textView_name);
            textViewWeight = view.findViewById(R.id.textView_weight);
            textViewAverageResult = view.findViewById(R.id.textView_average_result);
            textViewAveragePoint = view.findViewById(R.id.textView_average_point);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Grades class' constructor method.");
        }

    }
}







