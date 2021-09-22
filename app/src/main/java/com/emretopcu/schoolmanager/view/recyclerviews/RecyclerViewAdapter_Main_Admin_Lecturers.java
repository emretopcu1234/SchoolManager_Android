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

public class RecyclerViewAdapter_Main_Admin_Lecturers extends RecyclerView.Adapter<ViewHolder_MAL> {

    private Context context;
    private ArrayList<String[]> lecturerList;
    private boolean checkBoxActive;

    public RecyclerViewAdapter_Main_Admin_Lecturers(Context context, ArrayList<String[]> lecturerList) {
        try{
            this.context = context;
            this.lecturerList = lecturerList;
            checkBoxActive = false;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' constructor method.");
        }
    }

    public void setLecturerList(ArrayList<String[]> lecturerList){
        try{
            this.lecturerList = lecturerList;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' setLecturerList method.");
        }
    }

    public void isCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' isCheckBoxActive method.");
        }
    }

    @Override
    public ViewHolder_MAL onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_main_admin_lecturers, parent, false);
            ViewHolder_MAL holder = new ViewHolder_Main_Admin_Lecturers(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_MAL viewHolder, int position) {
        try{
            final ViewHolder_Main_Admin_Lecturers holder = (ViewHolder_Main_Admin_Lecturers) viewHolder;
            holder.checkBox.setChecked(checkBoxActive);
            holder.textViewId.setText(lecturerList.get(position)[0]);
            holder.textViewName.setText(lecturerList.get(position)[1]);
            holder.textViewSurname.setText(lecturerList.get(position)[2]);
            holder.textViewDeptName.setText(lecturerList.get(position)[3]);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return lecturerList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_MAL extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public ViewHolder_MAL(View v) {
        super(v);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        try{
            MenuItem edit = menu.add(0, v.getId(), 0, R.string.menu_main_admin_edit_lecturer);
            MenuItem delete = menu.add(0, v.getId(), 0, R.string.menu_main_admin_delete_lecturer);
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
            Log.d("Exception", "Exception on ViewHolder_MAL class' onCreateContextMenu method.");
        }
    }
}

class ViewHolder_Main_Admin_Lecturers extends ViewHolder_MAL {

    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Main_Admin_Lecturers(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Lecturers class' constructor method.");
        }
    }
}