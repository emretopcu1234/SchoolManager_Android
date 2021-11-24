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
import com.emretopcu.schoolmanager.commonObjectTypes.CourseType;
import com.emretopcu.schoolmanager.view.activities.Activity_Dept_Admin_Courses;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Dept_Admin_Courses extends RecyclerView.Adapter<ViewHolder_DAC> {

    private Activity_Dept_Admin_Courses context;
    private ArrayList<CourseType> courseList;
    private boolean checkBoxActive;
    private boolean popupMenuActive;
    private ArrayList<Boolean> isChecked;
    private String deptId;


    public RecyclerViewAdapter_Dept_Admin_Courses(Activity_Dept_Admin_Courses context, ArrayList<CourseType> courseList, String deptId) {
        try{
            this.context = context;
            this.courseList = courseList;
            this.deptId = deptId;
            checkBoxActive = false;
            popupMenuActive = false;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' constructor method.");
        }
    }

    public void setCourseList(ArrayList<CourseType> courseList){
        try{
            this.courseList = courseList;
            isChecked.clear();
            for(int i=0;i<courseList.size();i++){
                isChecked.add(false);
            }
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' setCourseList method.");
        }
    }

    public void setCheckBoxActive(boolean checkBoxActive){
        try{
            this.checkBoxActive = checkBoxActive;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' isCheckBoxActive method.");
        }
    }

    public void setPopupMenuActive(boolean popupMenuActive){
        try{
            this.popupMenuActive = popupMenuActive;
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' setPopupMenuActive method.");
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' resetChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
            context.onListItemClicked(position,isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onItemClicked method.");
        }
    }

    @Override
    public ViewHolder_DAC onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_dept_admin_courses, parent, false);
            ViewHolder_DAC holder = new ViewHolder_Dept_Admin_Courses(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_DAC viewHolder, int position) {
        try{
            final ViewHolder_Dept_Admin_Courses holder = (ViewHolder_Dept_Admin_Courses) viewHolder;
            final View.OnClickListener checkListener = v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' checkListener.setOnClickListener method.");
                }
            };
            final View.OnLongClickListener menuListener = v -> {
                try{
                    if(popupMenuActive){
                        PopupMenu popup = new PopupMenu(context, v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.menu_dept_admin_edit_or_delete_course, popup.getMenu());
                        popup.show();
                        MenuItem edit = popup.getMenu().findItem(R.id.menu_dept_admin_edit_course);
                        MenuItem editSections = popup.getMenu().findItem(R.id.menu_dept_admin_edit_sections);
                        MenuItem delete = popup.getMenu().findItem(R.id.menu_dept_admin_delete_course);
                        edit.setOnMenuItemClickListener(item -> {
                            try{
                                context.onEditRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' edit setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                        editSections.setOnMenuItemClickListener(item -> {
                            try{
                                context.onEditSectionsRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' editSections setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                        delete.setOnMenuItemClickListener(item -> {
                            try{
                                context.onDeleteRequested(position);
                                return true;
                            }
                            catch (Exception e){
                                Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' delete setOnMenuItemClickListener method.");
                                return false;
                            }
                        });
                    }
                    return true;
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' menuListener.setOnLongClickListener method.");
                    return false;
                }
            };
            if(checkBoxActive){
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.checkBox.setChecked(isChecked.get(position));
                holder.textViewId.setOnClickListener(checkListener);
                holder.textViewName.setOnClickListener(checkListener);
                holder.textViewSections.setOnClickListener(checkListener);
            }
            else{
                holder.checkBox.setVisibility(View.INVISIBLE);
                holder.textViewId.setOnClickListener(null);
                holder.textViewName.setOnClickListener(null);
                holder.textViewSections.setOnClickListener(null);
            }
            holder.checkBox.setOnClickListener(v -> {
                try{
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' holder.checkBox.setOnClickListener method.");
                }
            });

            holder.textViewId.setOnLongClickListener(menuListener);
            holder.textViewName.setOnLongClickListener(menuListener);
            holder.textViewSections.setOnLongClickListener(menuListener);

            holder.textViewId.setText(deptId + " " + courseList.get(position).getCourseId());
            holder.textViewName.setText(courseList.get(position).getCourseName());
            holder.textViewSections.setText(courseList.get(position).getSections());
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return courseList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Dept_Admin_Courses class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_DAC extends RecyclerView.ViewHolder {
    public ViewHolder_DAC(View v) {
        super(v);
    }
}

class ViewHolder_Dept_Admin_Courses extends ViewHolder_DAC {
    public CheckBox checkBox;
    public TextView textViewId;
    public TextView textViewName;
    public TextView textViewSections;

    public ViewHolder_Dept_Admin_Courses(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewId = view.findViewById(R.id.textView_id);
            textViewName = view.findViewById(R.id.textView_name);
            textViewSections = view.findViewById(R.id.textView_sections);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Dept_Admin_Courses class' constructor method.");
        }
    }
}




