package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
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
import com.emretopcu.schoolmanager.view.activities.Activity_Main_Admin_Dept_Admins;

import java.util.ArrayList;

public class RecyclerViewAdapter_Main_Admin_Dept_Admins extends RecyclerView.Adapter<ViewHolder_MADA> {

    private Activity_Main_Admin_Dept_Admins context;
    private ArrayList<String[]> deptAdminList;
    private boolean checkBoxActive;
    private boolean popupMenuActive;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Main_Admin_Dept_Admins(Activity_Main_Admin_Dept_Admins context, ArrayList<String[]> deptAdminList) {
        try{
            this.context = context;
            this.deptAdminList = deptAdminList;
            checkBoxActive = false;
            popupMenuActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' constructor method.");
        }
    }

    public void setDeptAdminList(ArrayList<String[]> deptAdminList){
        try{
            this.deptAdminList = deptAdminList;
            isChecked.clear();
            for(int i=0;i<deptAdminList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' setDeptAdminList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' isCheckBoxActive method.");
        }
    }

    public void setPopupMenuActive(boolean popupMenuActive){
        try{
            this.popupMenuActive = popupMenuActive;
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' setPopupMenuActive method.");
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' onItemClicked method.");
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
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' checkListener.setOnClickListener method.");
                }
            };
            final View.OnLongClickListener menuListener = v -> {
                try{
                    if(popupMenuActive){
                        PopupMenu popup = new PopupMenu(context, v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.menu_main_admin_edit_or_delete_dept_admin, popup.getMenu());
                        popup.show();
                        MenuItem edit = popup.getMenu().findItem(R.id.menu_main_admin_edit_dept_admin);
                        MenuItem delete = popup.getMenu().findItem(R.id.menu_main_admin_delete_dept_admin);
                        edit.setOnMenuItemClickListener(item -> {
                            try{
                                context.onEditRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' edit setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                        delete.setOnMenuItemClickListener(item -> {
                            try{
                                context.onDeleteRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' delete setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                    }
                    return true;
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' menuListener.setOnLongClickListener method.");
                    return false;
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
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Dept_Admins class' holder.checkBox.setOnClickListener method.");
                }
            });

            holder.textViewId.setOnLongClickListener(menuListener);
            holder.textViewName.setOnLongClickListener(menuListener);
            holder.textViewSurname.setOnLongClickListener(menuListener);
            holder.textViewDeptName.setOnLongClickListener(menuListener);

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

class ViewHolder_MADA extends RecyclerView.ViewHolder{
    public ViewHolder_MADA(View v) {
        super(v);
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