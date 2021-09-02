package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Filter_Department extends RecyclerView.Adapter<ViewHolder_FD> {

    private Context context;


    public RecyclerViewAdapter_Filter_Department(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' constructor method.");
        }
    }

    @Override
    public ViewHolder_FD onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_filter_department, parent, false);
            ViewHolder_FD holder = new ViewHolder_Filter_Department(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_FD viewHolder, int position) {
        try{
            final ViewHolder_Filter_Department holder = (ViewHolder_Filter_Department) viewHolder;
            holder.checkBox.setChecked(false);
            holder.textViewDeptName.setText("Business Administration");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_FD extends RecyclerView.ViewHolder {
    public ViewHolder_FD(View v) {
        super(v);
    }
}

class ViewHolder_Filter_Department extends ViewHolder_FD {

    public CheckBox checkBox;
    public TextView textViewDeptName;

    public ViewHolder_Filter_Department(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Filter_Department class' constructor method.");
        }

    }
}





