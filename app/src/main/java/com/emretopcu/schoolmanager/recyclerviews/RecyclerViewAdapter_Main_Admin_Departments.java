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


public class RecyclerViewAdapter_Main_Admin_Departments extends RecyclerView.Adapter<ViewHolder_MAD> {

    private Context context;


    public RecyclerViewAdapter_Main_Admin_Departments(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' constructor method.");
        }
    }

    @Override
    public ViewHolder_MAD onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_admin_departments, parent, false);
            ViewHolder_MAD holder = new ViewHolder_Main_Admin_Departments(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_MAD viewHolder, int position) {
        try{
            final ViewHolder_Main_Admin_Departments holder = (ViewHolder_Main_Admin_Departments) viewHolder;
            holder.checkBox.setChecked(false);
            holder.textViewDeptName.setText("Electrical and Electronics Engineering");
            holder.textViewDeptId.setText("EEE");
            holder.textViewOpened.setText("2017-2018 Summer");
            holder.textViewClosed.setText("2020-2021 Summer");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_MAD extends RecyclerView.ViewHolder {
    public ViewHolder_MAD(View v) {
        super(v);
    }
}

class ViewHolder_Main_Admin_Departments extends ViewHolder_MAD {

    public CheckBox checkBox;
    public TextView textViewDeptName;
    public TextView textViewDeptId;
    public TextView textViewOpened;
    public TextView textViewClosed;

    public ViewHolder_Main_Admin_Departments(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
            textViewDeptId = view.findViewById(R.id.textView_dept_id);
            textViewOpened = view.findViewById(R.id.textView_opened);
            textViewClosed = view.findViewById(R.id.textView_closed);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Departments class' constructor method.");
        }

    }
}

