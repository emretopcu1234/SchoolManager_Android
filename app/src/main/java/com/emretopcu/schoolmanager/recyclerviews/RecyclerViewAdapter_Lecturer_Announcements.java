package com.emretopcu.schoolmanager.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Lecturer_Announcements extends RecyclerView.Adapter<ViewHolder_LAn> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Announcements(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Announcements class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LAn onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_announcements, parent, false);
            ViewHolder_LAn holder = new ViewHolder_Lecturer_Announcements(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Announcements class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LAn viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Announcements holder = (ViewHolder_Lecturer_Announcements) viewHolder;
            holder.checkBox.setChecked(false);
            holder.textViewDateAndTime.setText("20 Jan 2021 14:32");
            holder.textViewTitle.setText("About the second quiz on Sunday");
            holder.textViewContent.setText("About the second quiz on Monday sth went wtong am th sorumg asory the yofk wektn gkyypefl yhjeud oluymyut kfpuşglgg as 3565.3546 effw.");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Announcements class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Announcements class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Announcements class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LAn extends RecyclerView.ViewHolder {
    public ViewHolder_LAn(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Announcements extends ViewHolder_LAn {

    public CheckBox checkBox;
    public TextView textViewDateAndTime;
    public TextView textViewTitle;
    public TextView textViewContent;

    public ViewHolder_Lecturer_Announcements(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            textViewDateAndTime = view.findViewById(R.id.textView_date_and_time);
            textViewTitle = view.findViewById(R.id.textView_title);
            textViewContent = view.findViewById(R.id.textView_content);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Announcements class' constructor method.");
        }

    }
}




