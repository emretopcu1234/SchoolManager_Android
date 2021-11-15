package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.DepartmentType;
import com.emretopcu.schoolmanager.view.activities.Activity_Main_Admin_Departments;

import java.util.ArrayList;


public class RecyclerViewAdapter_Main_Admin_Departments extends RecyclerView.Adapter<ViewHolder_MAD> {

    private Activity_Main_Admin_Departments context;
    private ArrayList<DepartmentType> departmentList;
    private boolean checkBoxActive;
    private boolean popupMenuActive;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Main_Admin_Departments(Activity_Main_Admin_Departments context, ArrayList<DepartmentType> departmentList) {
        try{
            this.context = context;
            this.departmentList = departmentList;
            checkBoxActive = false;
            popupMenuActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' constructor method.");
        }
    }

    public void setDepartmentList(ArrayList<DepartmentType> departmentList){
        try{
            this.departmentList = departmentList;
            isChecked.clear();
            for(int i=0;i<departmentList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' setDepartmentList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' isCheckBoxActive method.");
        }
    }

    public void setPopupMenuActive(boolean popupMenuActive){
        try{
            this.popupMenuActive = popupMenuActive;
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' setPopupMenuActive method.");
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' onItemClicked method.");
        }
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
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' checkListener.setOnClickListener method.");
                }
            };
            final View.OnLongClickListener menuListener = v -> {
                try{
                    if(popupMenuActive){
                        PopupMenu popup = new PopupMenu(context, v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.menu_main_admin_edit_or_delete_department, popup.getMenu());
                        popup.show();
                        MenuItem edit = popup.getMenu().findItem(R.id.menu_main_admin_edit_department);
                        MenuItem delete = popup.getMenu().findItem(R.id.menu_main_admin_delete_department);
                        edit.setOnMenuItemClickListener(item -> {
                            try{
                                context.onEditRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' edit setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                        delete.setOnMenuItemClickListener(item -> {
                            try{
                                context.onDeleteRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' delete setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                    }
                    return true;
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' menuListener.setOnLongClickListener method.");
                    return false;
                }
            };

            if(checkBoxActive){
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.checkBox.setChecked(isChecked.get(position));
                holder.textViewDeptName.setOnClickListener(checkListener);
                holder.textViewDeptId.setOnClickListener(checkListener);
            }
            else{
                holder.checkBox.setVisibility(View.INVISIBLE);
                holder.textViewDeptName.setOnClickListener(null);
                holder.textViewDeptId.setOnClickListener(null);
            }

            holder.checkBox.setOnClickListener(v -> {
                try{
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Departments class' holder.checkBox.setOnClickListener method.");
                }
            });

            holder.textViewDeptName.setOnLongClickListener(menuListener);
            holder.textViewDeptId.setOnLongClickListener(menuListener);

            holder.textViewDeptName.setText(departmentList.get(position).getDeptName());
            holder.textViewDeptId.setText(departmentList.get(position).getDeptId());
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
        try{
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
        catch (Exception e){
            Log.d("Exception", "Exception on ViewHolder_MAD class' onCreateContextMenu method.");
        }
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

