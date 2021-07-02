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

public class RecyclerViewAdapter_Dept_Admin_Specific_Course_Students extends RecyclerView.Adapter<ViewHolder_DASCS> {

    private Context context;


    public RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' constructor method.");
        }
    }

    @Override
    public ViewHolder_DASCS onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dept_admin_specific_course_students, parent, false);
            ViewHolder_DASCS holder = new ViewHolder_Dept_Admin_Specific_Course_Students(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DASCS viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Specific_Course_Students holder = (ViewHolder_Dept_Admin_Specific_Course_Students) viewHolder;
            holder.textViewId.setText("30016");
            holder.textViewName.setText("Ahmet Mehmet");
            holder.textViewSurname.setText("KAYIŞOĞLU");
            holder.textViewDeptName.setText("ME");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 20;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DASCS extends RecyclerView.ViewHolder {
    public ViewHolder_DASCS(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Specific_Course_Students extends ViewHolder_DASCS {

    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Dept_Admin_Specific_Course_Students(View view) {
        super(view);
        try{
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Specific_Course_Students class' constructor method.");
        }

    }
}



