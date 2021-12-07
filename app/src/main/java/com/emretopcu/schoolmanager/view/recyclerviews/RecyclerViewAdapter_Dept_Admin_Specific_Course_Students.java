package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.PersonType;
import com.emretopcu.schoolmanager.view.activities.Activity_Dept_Admin_Specific_Course;

import java.util.ArrayList;

public class RecyclerViewAdapter_Dept_Admin_Specific_Course_Students extends RecyclerView.Adapter<ViewHolder_DASCS> {

    private Activity_Dept_Admin_Specific_Course context;
    private ArrayList<PersonType> studentList;
    private boolean checkBoxActive;
    private boolean popupMenuActive;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Dept_Admin_Specific_Course_Students(Activity_Dept_Admin_Specific_Course context, ArrayList<PersonType> studentList) {
        try{
            this.context = context;
            this.studentList = studentList;
            checkBoxActive = false;
            popupMenuActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' constructor method.");
        }
    }

    public void setSpecificStudentList(ArrayList<PersonType> studentList){
        try{
            this.studentList = studentList;
            isChecked.clear();
            for(int i=0;i<studentList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' setSpecificStudentList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' isCheckBoxActive method.");
        }
    }

    public void setPopupMenuActive(boolean popupMenuActive){
        try{
            this.popupMenuActive = popupMenuActive;
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' setPopupMenuActive method.");
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onItemClicked method.");
        }
    }

    @Override
    public ViewHolder_DASCS onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_dept_admin_specific_course_students, parent, false);
            ViewHolder_DASCS holder = new ViewHolder_Dept_Admin_Specific_Course_Students(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_DASCS viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Specific_Course_Students holder = (ViewHolder_Dept_Admin_Specific_Course_Students) viewHolder;
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' checkListener.setOnClickListener method.");
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
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' holder.checkBox.setOnClickListener method.");
                }
            });

            holder.textViewId.setText(studentList.get(position).getId());
            holder.textViewName.setText(studentList.get(position).getName());
            holder.textViewSurname.setText(studentList.get(position).getSurname());
            holder.textViewDeptName.setText(studentList.get(position).getDeptId());
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return studentList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Specific_Course_Students class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DASCS extends RecyclerView.ViewHolder {
    public ViewHolder_DASCS(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Specific_Course_Students extends ViewHolder_DASCS {

    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSurname;
    public TextView textViewDeptName;

    public ViewHolder_Dept_Admin_Specific_Course_Students(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSurname = view.findViewById(R.id.textView_surname);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Specific_Course_Students class' constructor method.");
        }

    }
}