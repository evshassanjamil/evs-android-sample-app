package com.evs.android.mysampleapp.week9.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
class PermissionHelper {

    private Activity mActivity;

    public PermissionHelper(Activity activity) {
        this.mActivity = activity;
    }

    public void requestPermission(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;

        // Fire off an async request to actually get the permission
        // This will show the standard permission request dialog UI
        mActivity.requestPermissions(permissions, requestCode);
    }

    public boolean isPermissionGranted(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        return (mActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
    }

}
