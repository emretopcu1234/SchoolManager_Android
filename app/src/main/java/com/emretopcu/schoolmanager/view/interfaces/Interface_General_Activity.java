package com.emretopcu.schoolmanager.view.interfaces;

public interface Interface_General_Activity {
    void onSemesterChanged(String selectedSemester);
    void onChangePasswordClicked();
    void onLogoutClicked();
    void setAndShowWarningOnDialogChangePassword(int warning, int visibility);
}
