package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Main_Admin_Semesters extends RecyclerView.Adapter<ViewHolder_MASe> {

    private Context context;


    public RecyclerViewAdapter_Main_Admin_Semesters(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Main_Admin_Semesters class' constructor method.");
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
            holder.textViewSemester.setText("2016-2017 Spring");
            holder.textViewStart.setText("23 Sep 2016");
            holder.textViewEnd.setText("25 Jan 2017");
            holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context, v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_main_admin_edit_or_delete_semester, popup.getMenu());
                    popup.show();
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
            return 10;
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


