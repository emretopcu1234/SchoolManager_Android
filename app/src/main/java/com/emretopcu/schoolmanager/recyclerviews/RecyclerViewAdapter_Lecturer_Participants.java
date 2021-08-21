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

public class RecyclerViewAdapter_Lecturer_Participants extends RecyclerView.Adapter<ViewHolder_LP> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Participants(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Participants class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LP onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_participants, parent, false);
            ViewHolder_LP holder = new ViewHolder_Lecturer_Participants(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Participants class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LP viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Participants holder = (ViewHolder_Lecturer_Participants) viewHolder;
            holder.textViewId.setText("40001");
            holder.textViewName.setText("Sinan");
            holder.textViewSurname.setText("ÖZÇELİK");
            holder.textViewDeptName.setText("ME");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Participants class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Participants class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Participants class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LP extends RecyclerView.ViewHolder {
    public ViewHolder_LP(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Participants extends ViewHolder_LP {

    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Lecturer_Participants(View view) {
        super(view);
        try{
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Participants class' constructor method.");
        }

    }
}



