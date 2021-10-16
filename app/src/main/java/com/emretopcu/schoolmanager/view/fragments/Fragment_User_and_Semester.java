package com.emretopcu.schoolmanager.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.emretopcu.schoolmanager.R;
import com.emretopcu.schoolmanager.view.interfaces.Interface_Fragment_User_and_Semester;
import com.emretopcu.schoolmanager.view.interfaces.Interface_General_Activity;

import java.util.ArrayList;

public class Fragment_User_and_Semester extends Fragment implements Interface_Fragment_User_and_Semester {

    private View view;
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private TextView textView;
    private Button button;

    private ArrayList<String> spinnerList = new ArrayList<>();

    private Interface_General_Activity interfaceUserAndSemester;

    public Fragment_User_and_Semester(Interface_General_Activity interfaceUserAndSemester) {
        super(R.layout.fragment_user_and_semester);
        try{
            this.interfaceUserAndSemester = interfaceUserAndSemester;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' constructor method.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' onCreate method.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            view = inflater.inflate(R.layout.fragment_user_and_semester, container, false);

            spinner = view.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        interfaceUserAndSemester.onSemesterChanged(spinner.getSelectedItem().toString(), position);
                    }
                    catch(Exception e){
                        Log.d("Exception", "Exception on Fragment_User_and_Semester class' spinner's onItemSelected method.");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // nothing to do
                }
            });

            textView = view.findViewById(R.id.textView);
            button = view.findViewById(R.id.button);

            button.setOnClickListener(v -> {
                try{
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    MenuInflater inflater1 = popup.getMenuInflater();
                    inflater1.inflate(R.menu.menu_user, popup.getMenu());
                    popup.show();
                    MenuItem changePassword = popup.getMenu().findItem(R.id.menu_user_change_password);
                    MenuItem logout = popup.getMenu().findItem(R.id.menu_user_logout);
                    changePassword.setOnMenuItemClickListener(item -> {
                        try{
                            interfaceUserAndSemester.onChangePasswordClicked();
                            return true;
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Fragment_User_and_Semester class' changePassword setOnMenuItemClickListener method.");
                            return false;
                        }
                    });
                    logout.setOnMenuItemClickListener(item -> {
                        try{
                            interfaceUserAndSemester.onLogoutClicked();
                            return true;
                        }
                        catch (Exception e){
                            Log.d("Exception", "Exception on Fragment_User_and_Semester class' logout setOnMenuItemClickListener method.");
                            return false;
                        }
                    });
                }
                catch(Exception e){
                    Log.d("Exception", "Exception on Fragment_User_and_Semester class' buttonXXX setOnClickListener method.");
                }
            });
            return view;
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' onCreateView method.");
            return null;
        }
    }

    @Override
    public void setName(String name) {
        try{
            textView.setText(name);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' setName method.");
        }
    }

    @Override
    public void setSpinnerList(ArrayList<String> spinnerList) {
        try{
            this.spinnerList = spinnerList;
            arrayAdapter = new ArrayAdapter(getActivity(),R.layout.spinner_type_fragment_user_and_semester, spinnerList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' setSpinnerList method.");
        }
    }

    @Override
    public void setSpinnerItem(int position) {
        try{
            spinner.setSelection(position);
            interfaceUserAndSemester.onSemesterChanged(spinner.getSelectedItem().toString(), position);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' setSpinnerItem method.");
        }
    }

    @Override
    public void setSpinnerVisibility(int visibility) {
        try{
            spinner.setVisibility(visibility);
        }
        catch(Exception e){
            Log.d("Exception", "Exception on Fragment_User_and_Semester class' setSpinnerVisibility method.");
        }
    }
}