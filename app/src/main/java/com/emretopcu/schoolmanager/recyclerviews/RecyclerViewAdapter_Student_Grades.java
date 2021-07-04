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

public class RecyclerViewAdapter_Student_Grades extends RecyclerView.Adapter<ViewHolder_SG> {

    private Context context;

    public RecyclerViewAdapter_Student_Grades(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Grades class' constructor method.");
        }
    }

    @Override
    public ViewHolder_SG onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_student_grades, parent, false);
            ViewHolder_SG holder = new ViewHolder_Student_Grades(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Grades class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_SG viewHolder, int position) {
        try{
            final ViewHolder_Student_Grades holder = (ViewHolder_Student_Grades) viewHolder;
            holder.textViewName.setText("Case Study 123");
            holder.textViewWeight.setText("15%");
            holder.textViewAverageResult.setText("56.53");
            holder.textViewAveragePoint.setText("14.42");
            holder.textViewResult.setText("76.5");
            holder.textViewPoint.setText("22.45");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Grades class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Grades class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Grades class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_SG extends RecyclerView.ViewHolder {
    public ViewHolder_SG(View v) {
        super(v);
    }
}

class ViewHolder_Student_Grades extends ViewHolder_SG {

    public TextView textViewName;
    public TextView textViewWeight;
    public TextView textViewAverageResult;
    public TextView textViewAveragePoint;
    public TextView textViewResult;
    public TextView textViewPoint;


    public ViewHolder_Student_Grades(View view) {
        super(view);
        try{
            textViewName = view.findViewById(R.id.textView_name);
            textViewWeight = view.findViewById(R.id.textView_weight);
            textViewAverageResult = view.findViewById(R.id.textView_average_result);
            textViewAveragePoint = view.findViewById(R.id.textView_average_point);
            textViewResult = view.findViewById(R.id.textView_result);
            textViewPoint = view.findViewById(R.id.textView_point);

        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Student_Grades class' constructor method.");
        }

    }
}






