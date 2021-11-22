package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.view.activities.Activity_Dept_Admin_Lecturers;

import java.util.ArrayList;

public class RecyclerViewAdapter_Dept_Admin_Lecturers extends RecyclerView.Adapter<ViewHolder_DAL> {

    private Activity_Dept_Admin_Lecturers context;
    private ArrayList<PersonType> lecturerList;


    public RecyclerViewAdapter_Dept_Admin_Lecturers(Activity_Dept_Admin_Lecturers context, ArrayList<PersonType> lecturerList) {
        try{
            this.context = context;
            this.lecturerList = lecturerList;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' constructor method.");
        }
    }

    public void setLecturerList(ArrayList<PersonType> lecturerList){
        try{
            this.lecturerList = lecturerList;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' setLecturerList method.");
        }
    }

    @Override
    public ViewHolder_DAL onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_dept_admin_lecturers, parent, false);
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
            holder.textViewId.setText(lecturerList.get(position).getId());
            holder.textViewName.setText(lecturerList.get(position).getName());
            holder.textViewSurname.setText(lecturerList.get(position).getSurname());
            holder.textViewDeptName.setText(lecturerList.get(position).getDeptId());
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Lecturers class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return lecturerList.size();
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