package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.activities.Activity_Dept_Admin_Students;

import java.util.ArrayList;

public class RecyclerViewAdapter_Dept_Admin_Students extends RecyclerView.Adapter<ViewHolder_DASt> {

    private Activity_Dept_Admin_Students context;
    private ArrayList<String[]> studentList;


    public RecyclerViewAdapter_Dept_Admin_Students(Activity_Dept_Admin_Students context, ArrayList<String[]> studentList) {
        try{
            this.context = context;
            this.studentList = studentList;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' constructor method.");
        }
    }

    public void setStudentList(ArrayList<String[]> studentList){
        try{
            this.studentList = studentList;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' setStudentList method.");
        }
    }

    @Override
    public ViewHolder_DASt onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_dept_admin_students, parent, false);
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
            holder.textViewId.setText(studentList.get(position)[0]);
            holder.textViewName.setText(studentList.get(position)[1]);
            holder.textViewSurname.setText(studentList.get(position)[2]);
            holder.textViewDeptName.setText(studentList.get(position)[3]);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return studentList.size();
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