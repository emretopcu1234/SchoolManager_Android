package com.emretopcu.schoolmanager.view.recyclerviews;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.commonObjectTypes.mainAdmin.SemesterType;
import com.emretopcu.schoolmanager.view.activities.Activity_Main_Admin_Semesters;

import java.util.ArrayList;

public class RecyclerViewAdapter_Main_Admin_Semesters extends RecyclerView.Adapter<ViewHolder_MASe> {

    private Activity_Main_Admin_Semesters context;
    private ArrayList<SemesterType> detailedSemesterList;

    public RecyclerViewAdapter_Main_Admin_Semesters(Activity_Main_Admin_Semesters context, ArrayList<SemesterType> detailedSemesterList) {
        try{
            this.context = context;
            this.detailedSemesterList = detailedSemesterList;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' constructor method.");
        }
    }

    public void setDetailedSemesterList(ArrayList<SemesterType> detailedSemesterList){
        try{
            this.detailedSemesterList = detailedSemesterList;
            notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' setDetailedSemesterList method.");
        }
    }

    @Override
    public ViewHolder_MASe onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_main_admin_semesters, parent, false);
            ViewHolder_MASe holder = new ViewHolder_Main_Admin_Semesters(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' onCreateViewHolder method.");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder_MASe viewHolder, int position) {
        try{
            final ViewHolder_Main_Admin_Semesters holder = (ViewHolder_Main_Admin_Semesters) viewHolder;
            holder.textViewSemester.setText(detailedSemesterList.get(position).getSemester());
            holder.textViewStart.setText(detailedSemesterList.get(position).getStartDate());
            holder.textViewEnd.setText(detailedSemesterList.get(position).getEndDate());
            if(detailedSemesterList.get(position).getStatus().equals("PAST")){
                holder.buttonEdit.setVisibility(View.INVISIBLE);
            }
            else{
                holder.buttonEdit.setVisibility(View.VISIBLE);
            }
            holder.buttonEdit.setOnClickListener(v -> {
                try{
                    PopupMenu popup = new PopupMenu(context, v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_main_admin_edit_or_delete_semester, popup.getMenu());
                    popup.show();
                    MenuItem edit = popup.getMenu().findItem(R.id.menu_main_admin_edit_semester);
                    MenuItem delete = popup.getMenu().findItem(R.id.menu_main_admin_delete_semester);
                    if(!detailedSemesterList.get(position).getStatus().equals("ACTIVE")){
                        delete.setVisible(true);
                    }
                    else{
                        delete.setVisible(false);
                    }
                    edit.setOnMenuItemClickListener(item -> {
                        try{
                            context.onEditRequested(position);
                            return true;
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' edit setOnMenuItemClickListener method.");
                            return false;
                        }
                    });
                    delete.setOnMenuItemClickListener(item -> {
                        try{
                            context.onDeleteRequested(position);
                            return true;
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' delete setOnMenuItemClickListener method.");
                            return false;
                        }
                    });
                }
                catch (Exception e){
                    Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' holder.buttonEdit.setOnClickListener method.");
                }
            });
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return detailedSemesterList.size();
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_MASe extends RecyclerView.ViewHolder {
    public ViewHolder_MASe(View v) {
        super(v);
    }
}

class ViewHolder_Main_Admin_Semesters extends ViewHolder_MASe {

    public TextView textViewSemester;
    public TextView textViewStart;
    public TextView textViewEnd;
    public Button buttonEdit;

    public ViewHolder_Main_Admin_Semesters(View view) {
        super(view);
        try{
            textViewSemester = view.findViewById(R.id.textView_name);
            textViewStart = view.findViewById(R.id.textView_start);
            textViewEnd = view.findViewById(R.id.textView_end);
            buttonEdit = view.findViewById(R.id.button_edit);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Main_Admin_Semesters class' constructor method.");
        }
    }
}