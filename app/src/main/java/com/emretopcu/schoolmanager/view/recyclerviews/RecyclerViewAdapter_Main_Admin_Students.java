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
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.PersonType;
import com.emretopcu.schoolmanager.view.activities.Activity_Main_Admin_Students;

import java.util.ArrayList;

public class RecyclerViewAdapter_Main_Admin_Students extends RecyclerView.Adapter<ViewHolder_MASt> {

    private Activity_Main_Admin_Students context;
    private ArrayList<PersonType> studentList;
    private boolean checkBoxActive;
    private boolean popupMenuActive;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Main_Admin_Students(Activity_Main_Admin_Students context, ArrayList<PersonType> studentList) {
        try{
            this.context = context;
            this.studentList = studentList;
            checkBoxActive = false;
            popupMenuActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' constructor method.");
        }
    }

    public void setStudentList(ArrayList<PersonType> studentList){
        try{
            this.studentList = studentList;
            isChecked.clear();
            for(int i=0;i<studentList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' setStudentList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' isCheckBoxActive method.");
        }
    }

    public void setPopupMenuActive(boolean popupMenuActive){
        try{
            this.popupMenuActive = popupMenuActive;
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' setPopupMenuActive method.");
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' onItemClicked method.");
        }
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
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' checkListener.setOnClickListener method.");
                }
            };
            final View.OnLongClickListener menuListener = v -> {
                try{
                    if(popupMenuActive){
                        PopupMenu popup = new PopupMenu(context, v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.menu_main_admin_edit_or_delete_student, popup.getMenu());
                        popup.show();
                        MenuItem edit = popup.getMenu().findItem(R.id.menu_main_admin_edit_student);
                        MenuItem delete = popup.getMenu().findItem(R.id.menu_main_admin_delete_student);
                        edit.setOnMenuItemClickListener(item -> {
                            try{
                                context.onEditRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' edit setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                        delete.setOnMenuItemClickListener(item -> {
                            try{
                                context.onDeleteRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' delete setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                    }
                    return true;
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' menuListener.setOnLongClickListener method.");
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
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Students class' holder.checkBox.setOnClickListener method.");
                }
            });

            holder.textViewId.setOnLongClickListener(menuListener);
            holder.textViewName.setOnLongClickListener(menuListener);
            holder.textViewSurname.setOnLongClickListener(menuListener);
            holder.textViewDeptName.setOnLongClickListener(menuListener);

            holder.textViewId.setText(studentList.get(position).getId());
            holder.textViewName.setText(studentList.get(position).getName());
            holder.textViewSurname.setText(studentList.get(position).getSurname());
            holder.textViewDeptName.setText(studentList.get(position).getDeptId());
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

class ViewHolder_MASt extends RecyclerView.ViewHolder {
    public ViewHolder_MASt(View v) {
        super(v);
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