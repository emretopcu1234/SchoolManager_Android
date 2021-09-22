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

public class RecyclerViewAdapter_Main_Admin_Dept_Admins extends RecyclerView.Adapter<ViewHolder_MADA> {

    private Context context;
    private ArrayList<String[]> deptAdminList;
    private boolean checkBoxActive;

    public RecyclerViewAdapter_Main_Admin_Dept_Admins(Context context, ArrayList<String[]> deptAdminList) {
        try{
            this.context = context;
            this.deptAdminList = deptAdminList;
            checkBoxActive = false;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' constructor method.");
        }
    }

    public void setDeptAdminList(ArrayList<String[]> deptAdminList){
        try{
            this.deptAdminList = deptAdminList;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' setDeptAdminList method.");
        }
    }

    public void isCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' isCheckBoxActive method.");
        }
    }

    @Override
    public ViewHolder_MADA onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_main_admin_dept_admins, parent, false);
            ViewHolder_MADA holder = new ViewHolder_Main_Admin_Dept_Admins(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_MADA viewHolder, int position) {
        try{
            final ViewHolder_Main_Admin_Dept_Admins holder = (ViewHolder_Main_Admin_Dept_Admins) viewHolder;
            holder.checkBox.setChecked(checkBoxActive);
            holder.textViewId.setText(deptAdminList.get(position)[0]);
            holder.textViewName.setText(deptAdminList.get(position)[1]);
            holder.textViewSurname.setText(deptAdminList.get(position)[2]);
            holder.textViewDeptName.setText(deptAdminList.get(position)[3]);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return deptAdminList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_MADA extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public ViewHolder_MADA(View v) {
        super(v);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        try{
            MenuItem edit = menu.add(0, v.getId(), 0, R.string.menu_main_admin_edit_dept_admin);
            MenuItem delete = menu.add(0, v.getId(), 0, R.string.menu_main_admin_delete_dept_admin);
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
        catch (Exception e){
            Log.d("Exception", "Exception on ViewHolder_MADA class' onCreateContextMenu method.");
        }
    }
}

class ViewHolder_Main_Admin_Dept_Admins extends ViewHolder_MADA {
    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Main_Admin_Dept_Admins(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Dept_Admins class' constructor method.");
        }
    }
}


