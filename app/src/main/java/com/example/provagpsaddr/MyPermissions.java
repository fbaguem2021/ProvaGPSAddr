package com.example.provagpsaddr;

import android.app.Activity;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;


public class MyPermissions {

    public static boolean checkPermissions(Activity activity, String [] permissions) {
        boolean checked = false;

        if (Build.VERSION.SDK_INT >= 23) {
            if (check(activity, permissions)) {
                checked = true;
            } else {
                requestPermissions(activity, permissions);
            }
        } else {
            checked = true;
        }

        return checked;
    }

    private static boolean check(Activity activity, String [] permissions) {
        boolean checked = true;
        int i = 0;

        if (permissions[i].equals())

        return checked;
    }

    private static void requestPermissions(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 1);
    }

}
