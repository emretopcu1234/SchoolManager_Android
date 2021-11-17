package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.DepartmentType;

import java.util.ArrayList;

public class RecyclerViewAdapter_Filter_Department extends RecyclerView.Adapter<ViewHolder_FD> {

    private Context context;
    private ArrayList<DepartmentType> departmentList;
    private ArrayList<Boolean> isChecked;

    public RecyclerViewAdapter_Filter_Department(Context context, ArrayList<DepartmentType> departmentList) {
        try{
            this.context = context;
            this.departmentList = departmentList;
            isChecked = new ArrayList<>();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' constructor method.");
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
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' setDepartmentList method.");
        }
    }

    public ArrayList<String> getFilteredDepartmentList(){
        try{
            ArrayList<String> filteredDepartmentList = new ArrayList<>();
            for(int i=0;i<isChecked.size();i++){
                if(isChecked.get(i)){
                    filteredDepartmentList.add(departmentList.get(i).getDeptName());
                }
            }
            return filteredDepartmentList;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' getFilteredDepartmentList method.");
            return null;
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
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' resetChecks method.");
        }
    }

    public ArrayList<Boolean> getChecks(){
        return isChecked;
    }

    public void setChecks(ArrayList<Boolean> checks){
        try{
            isChecked.clear();
            for(int i=0;i<checks.size();i++){
                isChecked.add(checks.get(i));
            }
            notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' setChecks method.");
        }
    }

    private void onItemClicked(int position){
        try{
            isChecked.set(position,!isChecked.get(position));
        }
        catch (Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onItemClicked method.");
        }
    }

    @Override
    public ViewHolder_FD onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_filter_department, parent, false);
            ViewHolder_FD holder = new ViewHolder_Filter_Department(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_FD viewHolder, int position) {
        try{
            final ViewHolder_Filter_Department holder = (ViewHolder_Filter_Department) viewHolder;
            holder.checkBox.setChecked(isChecked.get(position));
            holder.textViewDeptName.setText(departmentList.get(position).getDeptName());

            holder.checkBox.setOnClickListener(v -> {
                try{
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' holder.checkBox.setOnClickListener method.");
                }
            });
            holder.textViewDeptName.setOnClickListener(v -> {
                try{
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    onItemClicked(position);
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' holder.textViewDeptName.setOnClickListener method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return departmentList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Filter_Department class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_FD extends RecyclerView.ViewHolder {

    public ViewHolder_FD(View v) {
        super(v);
    }
}

class ViewHolder_Filter_Department extends ViewHolder_FD{

    public CheckBox checkBox;
    public TextView textViewDeptName;

    public ViewHolder_Filter_Department(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewDeptName = view.findViewById(R.id.textView_dept_name);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Filter_Department class' constructor method.");
        }
    }
}