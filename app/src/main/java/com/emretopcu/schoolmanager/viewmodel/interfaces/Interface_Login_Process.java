package com.emretopcu.schoolmanager.viewmodel.interfaces;

public interface Interface_Login_Process {
    void onCreateNewUserResulted(boolean isCreateNewUserSuccessful);
    void onLoginResultedWithMainAdmin();
    void onLoginResultedWithDeptAdmin();
    void onLoginResultedWithLecturer();
    void onLoginResultedWithStudent();
    void onLoginResultedUnsuccessful();
    void onReloginForMainAdminResulted(boolean isReloginForMainAdminSuccessful);
    void onChangePasswordResulted(boolean isChangePasswordAuthSuccessful, boolean isChangePasswordSuccessful);
}
