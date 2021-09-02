package com.emretopcu.schoolmanager.view.recyclerviews;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emretopcu.schoolmanager.R;

public class RecyclerViewAdapter_Student_Course_Notes extends RecyclerView.Adapter<ViewHolder_SCN> {

    private Context context;


    public RecyclerViewAdapter_Student_Course_Notes(Context context) {
        try{
            this.context = context;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Course_Notes class' constructor method.");
        }
    }

    @Override
    public ViewHolder_SCN onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View v;
            v = LayoutInflater.from(context).inflate(R.layout.recyclerview_student_course_notes, parent, false);
            ViewHolder_SCN holder = new ViewHolder_Student_Course_Notes(v);
            return holder;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Course_Notes class' onCreateViewHolder method.");
            return null;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder_SCN viewHolder, int position) {
        try{
            final ViewHolder_Student_Course_Notes holder = (ViewHolder_Student_Course_Notes) viewHolder;
            holder.buttonFilename.setText("Lecture_1_Introduction.pdf");
            holder.buttonFilename.setPaintFlags(holder.buttonFilename.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.textViewDate.setText("17/11/2020");
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Course_Notes class' onBindViewHolder method.");
        }
    }

    @Override
    public int getItemCount() {
        try{
            return 5;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Course_Notes class' getItemCount method.");
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try{
            super.onAttachedToRecyclerView(recyclerView);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on RecyclerViewAdapter_Student_Course_Notes class' onAttachedToRecyclerView method.");
        }
    }
}

class ViewHolder_SCN extends RecyclerView.ViewHolder {
    public ViewHolder_SCN(View v) {
        super(v);
    }
}

class ViewHolder_Student_Course_Notes extends ViewHolder_SCN {

    public Button buttonFilename;
    public TextView textViewDate;

    public ViewHolder_Student_Course_Notes(View view) {
        super(view);
        try{
            buttonFilename = view.findViewById(R.id.button_filename);
            textViewDate = view.findViewById(R.id.textView_date);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on ViewHolder_Student_Course_Notes class' constructor method.");
        }

    }
}





