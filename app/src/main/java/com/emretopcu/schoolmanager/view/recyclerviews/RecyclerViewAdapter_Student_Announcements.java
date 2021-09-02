package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Student_Announcements extends RecyclerView.Adapter<ViewHolder_SAn> {

    private Context context;

    public RecyclerViewAdapter_Student_Announcements(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Announcements class' constructor method.");
        }
    }

    @Override
    public ViewHolder_SAn onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_student_announcements, parent, false);
            ViewHolder_SAn holder = new ViewHolder_Student_Announcements(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Announcements class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_SAn viewHolder, int position) {
        try{
            final ViewHolder_Student_Announcements holder = (ViewHolder_Student_Announcements) viewHolder;
            holder.textViewDateAndTime.setText("20 Jan 2021 14:32");
            holder.textViewTitle.setText("About the second quiz on Tuesday");
            holder.textViewContent.setText("About the second quiz on Friday sth went wtong am th sorumg asory the yofk wektn gkyypefl yhjeud oluymyut kfpuşglgg as 3565.3546 effw.");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Announcements class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 10;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Announcements class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Announcements class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_SAn extends RecyclerView.ViewHolder {
    public ViewHolder_SAn(View v) {
        super(v);
    }
}

class ViewHolder_Student_Announcements extends ViewHolder_SAn {

    public TextView textViewDateAndTime;
    public TextView textViewTitle;
    public TextView textViewContent;

    public ViewHolder_Student_Announcements(View view) {
        super(view);
        try{
            textViewDateAndTime = view.findViewById(R.id.textView_date_and_time);
            textViewTitle = view.findViewById(R.id.textView_title);
            textViewContent = view.findViewById(R.id.textView_content);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Student_Announcements class' constructor method.");
        }

    }
}





