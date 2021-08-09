package com.emretopcu.schoolmanager.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Dept_Admin_Courses extends RecyclerView.Adapter<ViewHolder_DAC> {

    private Context context;


    public RecyclerViewAdapter_Dept_Admin_Courses(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' constructor method.");
        }
    }

    @Override
    public ViewHolder_DAC onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dept_admin_courses, parent, false);
            ViewHolder_DAC holder = new ViewHolder_Dept_Admin_Courses(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DAC viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Courses holder = (ViewHolder_Dept_Admin_Courses) viewHolder;
            holder.textViewId.setText("IE 265");
            holder.textViewName.setText("Introduction to Economics");
            holder.textViewSections.setText("3");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DAC extends RecyclerView.ViewHolder {
    public ViewHolder_DAC(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Courses extends ViewHolder_DAC {

    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSections;

    public ViewHolder_Dept_Admin_Courses(View view) {
        super(view);
        try{
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSections = view.findViewById(R.id.textView_sections);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Courses class' constructor method.");
        }

    }
}




