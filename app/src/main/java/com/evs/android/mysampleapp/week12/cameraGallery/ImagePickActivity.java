package com.evs.android.mysampleapp.week12.cameraGallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esafirm.imagepicker.model.Image;
import com.evs.android.mysampleapp.R;
import com.example.himagepickerlibrary.hImagePicker.ClassIImagesPick;
import com.example.himagepickerlibrary.hImagePicker.HImagePicker;

import java.io.File;
import java.util.ArrayList;

import models.ConfigIPicker;

public class ImagePickActivity extends AppCompatActivity implements ClassIImagesPick.ImagePick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);

        findViewById(R.id.btnPickImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigIPicker config = new ConfigIPicker(ImagePickActivity.this)
                        .setDialogTitle("Pick Image via")   // Set picker dialog title
                        .setDialogStrCamera("Camera")       // Set picker dialog camera option string
                        .setDialogStrGallery("Gallery")     // Set picker dialog's gallery option string
                        .setSingleTrue()                    // Set single for single image selection or limit = 1
                        .setCropModeTrue()       // Set crop mode true after single image selection from camera or gallery, default value is false
                        .setFolderModeTrue()                // Set Folder mode of images for gallery, default value is false
                        .setListener(ImagePickActivity.this)                  // Set callback listener
                        .setShowCamera(false)
                        .setDirPath(getCustomDirectoryPath(ImagePickActivity.this));  // Pass custom directory path
                HImagePicker.getInstance().config(config).load();   // Trigger picker dialog or start functionality
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        HImagePicker.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImagesPicked(int requestCode, int resultCode, ArrayList<Image> images,
                               boolean isRequestFromGallery) {
        if (images == null || images.size() < 1)
            return;

        File imgFile = new File(images.get(0).getPath());
        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ((ImageView) findViewById(R.id.ivPickedImage)).setImageBitmap(bitmap);
        }
    }

    private String getCustomDirectoryPath(Context context) {
        String externalStoragePath = ((android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)))
                ? android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
                : context.getFilesDir().getAbsolutePath();
        return externalStoragePath + "/" + context.getString(R.string.app_name);
    }
}
