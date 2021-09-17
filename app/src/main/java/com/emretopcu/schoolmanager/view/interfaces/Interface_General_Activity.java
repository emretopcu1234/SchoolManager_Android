package com.emretopcu.schoolmanager.view.interfaces;

public interface Interface_General_Activity {
    void onSemesterChanged(String selectedSemester, int position);
    void onChangePasswordClicked();
    void onLogoutClicked();
    void setAndShowWarningOnDialogChangePassword(int warning, int visibility);
    void onChangePasswordRequested(String oldPassword, String newPassword);
}
