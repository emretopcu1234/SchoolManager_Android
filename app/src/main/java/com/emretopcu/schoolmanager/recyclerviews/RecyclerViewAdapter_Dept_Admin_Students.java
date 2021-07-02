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

public class RecyclerViewAdapter_Dept_Admin_Students extends RecyclerView.Adapter<ViewHolder_DASt> {

    private Context context;


    public RecyclerViewAdapter_Dept_Admin_Students(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' constructor method.");
        }
    }

    @Override
    public ViewHolder_DASt onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dept_admin_students, parent, false);
            ViewHolder_DASt holder = new ViewHolder_Dept_Admin_Students(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DASt viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Students holder = (ViewHolder_Dept_Admin_Students) viewHolder;
            holder.textViewId.setText("30001");
            holder.textViewName.setText("Ahmet Mehmet");
            holder.textViewSurname.setText("KAYIŞOĞLU");
            holder.textViewDeptName.setText("IE");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DASt extends RecyclerView.ViewHolder {
    public ViewHolder_DASt(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Students extends ViewHolder_DASt {

    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Dept_Admin_Students(View view) {
        super(view);
        try{
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Students class' constructor method.");
        }

    }
}



