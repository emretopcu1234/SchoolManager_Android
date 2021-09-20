package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_Main_Admin_Students extends RecyclerView.Adapter<ViewHolder_MASt> {

    private Context context;
    private ArrayList<String[]> studentList;
    private boolean checkBoxActive;

    public RecyclerViewAdapter_Main_Admin_Students(Context context, ArrayList<String[]> studentList) {
        try{
            this.context = context;
            this.studentList = studentList;
            checkBoxActive = false;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' constructor method.");
        }
    }

    public void setStudentList(ArrayList<String[]> studentList){
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    public void isCheckBoxActive(boolean checkBoxActive){
        this.checkBoxActive = checkBoxActive;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder_MASt onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_main_admin_students, parent, false);
            ViewHolder_MASt holder = new ViewHolder_Main_Admin_Students(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_MASt viewHolder, int position) {
        try{
            final ViewHolder_Main_Admin_Students holder = (ViewHolder_Main_Admin_Students) viewHolder;
            holder.checkBox.setChecked(checkBoxActive);
            holder.textViewId.setText(studentList.get(position)[0]);
            holder.textViewName.setText(studentList.get(position)[1]);
            holder.textViewSurname.setText(studentList.get(position)[2]);
            holder.textViewDeptName.setText(studentList.get(position)[3]);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return studentList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_MASt extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public ViewHolder_MASt(View v) {
        super(v);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem edit = menu.add(0, v.getId(), 0, R.string.menu_main_admin_edit_student);
        MenuItem delete = menu.add(0, v.getId(), 0, R.string.menu_main_admin_delete_student);
        edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return true;
            }
        });
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return true;
            }
        });
    }
}

class ViewHolder_Main_Admin_Students extends ViewHolder_MASt {

    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Main_Admin_Students(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Students class' constructor method.");
        }
    }
}