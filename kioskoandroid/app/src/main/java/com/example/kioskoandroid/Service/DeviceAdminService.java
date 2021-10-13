package com.example.kioskoandroid.Service;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * run ADB Shell command:
 *
 * adb shell dpm set-device-owner com.example.kioskoelepos/.Service.DeviceAdminService
 *
 */

public class DeviceAdminService extends DeviceAdminReceiver {
    private static final String TAG = "DebugAdminServiceMSG";


    void showLog(String msg) {
        String status =  msg;
        Log.d(TAG,status);
    }

    public static ComponentName getComponentName(Context context) {

        return new ComponentName(context.getApplicationContext(), DeviceAdminReceiver.class);
    }


    public void onEnabled(Context context, Intent intent) {
        showLog("Device Admin Enabled");
    }


    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Do you want to disable device admin?";
    }


    public void onDisabled(Context context, Intent intent) {
        showLog("Device Admin Disabled");
    }


    public void onLockTaskModeEntering(Context context, Intent intent, String pkg) {
        showLog("KIOSK mode enabled");
    }


    public void onLockTaskModeExiting(Context context, Intent intent) {
        showLog("KIOSK mode disabled");
    }


}
