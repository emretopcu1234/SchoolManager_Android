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


public class RecyclerViewAdapter_Main_Admin_Departments extends RecyclerView.Adapter<ViewHolder_MAD> {

    private Context context;
    private ArrayList<String[]> departmentList;
    private boolean checkBoxActive;

    public RecyclerViewAdapter_Main_Admin_Departments(Context context, ArrayList<String[]> departmentList) {
        try{
            this.context = context;
            this.departmentList = departmentList;
            checkBoxActive = false;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' constructor method.");
        }
    }

    public void setDepartmentList(ArrayList<String[]> departmentList){
        this.departmentList = departmentList;
        notifyDataSetChanged();
    }

    public void isCheckBoxActive(boolean checkBoxActive){
        this.checkBoxActive = checkBoxActive;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder_MAD onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_main_admin_departments, parent, false);
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
            holder.checkBox.setChecked(checkBoxActive);
            holder.textViewDeptName.setText(departmentList.get(position)[0]);
            holder.textViewDeptId.setText(departmentList.get(position)[1]);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return departmentList.size();
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

class ViewHolder_MAD extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public ViewHolder_MAD(View v) {
        super(v);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem edit = menu.add(0, v.getId(), 0, R.string.menu_main_admin_edit_department);
        MenuItem delete = menu.add(0, v.getId(), 0, R.string.menu_main_admin_delete_department);
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

class ViewHolder_Main_Admin_Departments extends ViewHolder_MAD {

    public CheckBox checkBox;
    public TextView textViewDeptName;
    public TextView textViewDeptId;

    public ViewHolder_Main_Admin_Departments(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
            textViewDeptId = view.findViewById(R.id.textView_dept_id);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Departments class' constructor method.");
        }
    }
}

