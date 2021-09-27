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
import com.emretopcu.schoolmanager.view.activities.Activity_Main_Admin_Lecturers;

import java.util.ArrayList;

public class RecyclerViewAdapter_Main_Admin_Lecturers extends RecyclerView.Adapter<ViewHolder_MAL> {

    private Activity_Main_Admin_Lecturers context;
    private ArrayList<String[]> lecturerList;
    private boolean checkBoxActive;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Main_Admin_Lecturers(Activity_Main_Admin_Lecturers context, ArrayList<String[]> lecturerList) {
        try{
            this.context = context;
            this.lecturerList = lecturerList;
            checkBoxActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' constructor method.");
        }
    }

    public void setLecturerList(ArrayList<String[]> lecturerList){
        try{
            this.lecturerList = lecturerList;
            isChecked.clear();
            for(int i=0;i<lecturerList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' setLecturerList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' isCheckBoxActive method.");
        }
    }

    public void resetChecks(){
        try{
            for(int i=0;i<isChecked.size();i++){
                isChecked.set(i,false);
            }
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' onItemClicked method.");
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
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' checkListener.setOnClickListener method.");
                }
            };

            if(checkBoxActive){
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.checkBox.setChecked(isChecked.get(position));
                holder.textViewId.setOnClickListener(checkListener);
                holder.textViewName.setOnClickListener(checkListener);
                holder.textViewSurname.setOnClickListener(checkListener);
                holder.textViewDeptName.setOnClickListener(checkListener);
            }
            else{
                holder.checkBox.setVisibility(View.INVISIBLE);
                holder.textViewId.setOnClickListener(null);
                holder.textViewName.setOnClickListener(null);
                holder.textViewSurname.setOnClickListener(null);
                holder.textViewDeptName.setOnClickListener(null);
            }

            holder.checkBox.setOnClickListener(v -> {
                try{
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Lecturers class' holder.checkBox.setOnClickListener method.");
                }
            });

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