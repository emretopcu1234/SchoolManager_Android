package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Lecturer_Course_Notes extends RecyclerView.Adapter<ViewHolder_LCN> {

    private Context context;


    public RecyclerViewAdapter_Lecturer_Course_Notes(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Course_Notes class' constructor method.");
        }
    }

    @Override
    public ViewHolder_LCN onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_lecturer_course_notes, parent, false);
            ViewHolder_LCN holder = new ViewHolder_Lecturer_Course_Notes(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Course_Notes class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_LCN viewHolder, int position) {
        try{
            final ViewHolder_Lecturer_Course_Notes holder = (ViewHolder_Lecturer_Course_Notes) viewHolder;
            holder.checkBox.setChecked(false);
            holder.buttonFilename.setText("Lecture_1_Introduction.pdf");
            holder.buttonFilename.setPaintFlags(holder.buttonFilename.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.textViewDate.setText("17/11/2020");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Course_Notes class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 5;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Course_Notes class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Lecturer_Course_Notes class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_LCN extends RecyclerView.ViewHolder {
    public ViewHolder_LCN(View v) {
        super(v);
    }
}

class ViewHolder_Lecturer_Course_Notes extends ViewHolder_LCN {

    public CheckBox checkBox;
    public Button buttonFilename;
    public TextView textViewDate;

    public ViewHolder_Lecturer_Course_Notes(View view) {
        super(view);
        try{
            checkBox = view.findViewById(R.id.checkBox);
            buttonFilename = view.findViewById(R.id.button_filename);
            textViewDate = view.findViewById(R.id.textView_date);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Lecturer_Course_Notes class' constructor method.");
        }

    }
}




