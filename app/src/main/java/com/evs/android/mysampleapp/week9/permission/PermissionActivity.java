package com.evs.android.mysampleapp.week9.permission;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class PermissionActivity extends AppCompatActivity {

    private final int RC_PERMISSION = 11;
    private final int RC_APP_SETTING = 12;
    public final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        permissionHelper = new PermissionHelper(this);

        updateUI();

        findViewById(R.id.btnAskPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permissionHelper.isPermissionGranted(PERMISSION_STORAGE))
                    permissionHelper.requestPermission(new String[]{PERMISSION_STORAGE}, RC_PERMISSION);
            }
        });
    }

    private void updateUI() {
        ((ImageView) findViewById(R.id.ivFlag))
                .setImageResource(
                        (permissionHelper.isPermissionGranted(PERMISSION_STORAGE))
                                ? R.drawable.ic_check_circle
                                : R.drawable.ic_cancel);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RC_PERMISSION) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                return;
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    if (permissionHelper.isPermissionGranted(PERMISSION_STORAGE))
                        return;

                    boolean shouldShowRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!shouldShowRationale) {
                        // user denied flagging NEVER ASK AGAIN, you can either enable some fall back,
                        // disable features of your app or open another dialog explaining again the permission and directing to
                        // the app setting
                        dialogReasonPermissionSettings(
                                getString(R.string.reason_storage_permission),
                                new String[]{getString(R.string.go_to_settings),
                                        getString(R.string.dismiss)});
                    } else if (PERMISSION_STORAGE.equals(permissions[0])) {
                        // user denied WITHOUT never ask again, this is a good place to explain the user
                        // why you need the permission and ask if he want to accept it (the rationale)
                        dialogReasonPermission();
                    }
                } else {
                    // TODO You can do work on permission granted event from here
                    updateUI();
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_APP_SETTING) {
            updateUI();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //updateUI();
    }

    private void dialogReasonPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.reason_storage_permission));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.str_retry),
                (dialog, id) -> permissionHelper.requestPermission(
                        new String[]{PERMISSION_STORAGE}, RC_PERMISSION));
        builder.setNegativeButton(getString(R.string.dismiss),
                (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void dialogReasonPermissionSettings(String message, String[] buttons) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(buttons[0],
                (dialog, id) -> goToAppDetailsForPermissionSettings());
        builder.setNegativeButton(buttons[1],
                (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void goToAppDetailsForPermissionSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, RC_APP_SETTING);
    }
}
