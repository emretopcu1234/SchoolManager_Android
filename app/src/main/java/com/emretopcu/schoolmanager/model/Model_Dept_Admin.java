package com.emretopcu.schoolmanager.model;

import android.util.Log;

import com.emretopcu.schoolmanager.viewmodel.VM_Dept_Admin;

public class Model_Dept_Admin {

    private static Model_Dept_Admin INSTANCE;
    private VM_Dept_Admin vmDeptAdmin;

    private String deptAdminId;

    private Model_Dept_Admin(){
        try{

        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' constructor method.");
        }
    }

    public static Model_Dept_Admin getInstance(){
        try{
            if(INSTANCE == null){
                INSTANCE = new Model_Dept_Admin();
            }
            return INSTANCE;
        }
        catch (Exception e){
            Log.d("Exception", "Exception on Model_Dept_Admin class' getInstance method.");
            return null;
        }
    }

    public void setVmDeptAdmin(VM_Dept_Admin vmDeptAdmin) {
        this.vmDeptAdmin = vmDeptAdmin;
    }

    public String getDeptAdminId() {
        return deptAdminId;
    }

    public void setDeptAdminId(String deptAdminId) {
        this.deptAdminId = deptAdminId;
    }
}

