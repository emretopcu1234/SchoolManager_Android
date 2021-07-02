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

public class RecyclerViewAdapter_Dept_Admin_Lecturers extends RecyclerView.Adapter<ViewHolder_DAL> {

    private Context context;


    public RecyclerViewAdapter_Dept_Admin_Lecturers(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' constructor method.");
        }
    }

    @Override
    public ViewHolder_DAL onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dept_admin_lecturers, parent, false);
            ViewHolder_DAL holder = new ViewHolder_Dept_Admin_Lecturers(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DAL viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Lecturers holder = (ViewHolder_Dept_Admin_Lecturers) viewHolder;
            holder.textViewId.setText("20001");
            holder.textViewName.setText("Ahmet Mehmet");
            holder.textViewSurname.setText("KAYIŞOĞLU");
            holder.textViewDeptName.setText("MED");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DAL extends RecyclerView.ViewHolder {
    public ViewHolder_DAL(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Lecturers extends ViewHolder_DAL {

    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Dept_Admin_Lecturers(View view) {
        super(view);
        try{
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Lecturers class' constructor method.");
        }

    }
}



