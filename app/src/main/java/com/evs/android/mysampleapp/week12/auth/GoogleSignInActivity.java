package com.evs.android.mysampleapp.week12.auth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class GoogleSignInActivity extends AppCompatActivity {

    GoogleSignInHelper gsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        gsh = new GoogleSignInHelper(this);
        gsh.onCreate(findViewById(R.id.btnGoogleSignIn));
    }

    @Override
    protected void onStart() {
        super.onStart();
        gsh.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GoogleSignInAccount account = gsh.onActivityResult(requestCode, resultCode, data);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account == null)
            return;

        findViewById(R.id.btnGoogleSignIn).setVisibility(View.GONE);

        findViewById(R.id.cardUser).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.tvUserName)).setText(account.getDisplayName());
        ((TextView) findViewById(R.id.tvUserEmail)).setText(account.getEmail());

        //Button Signout
        findViewById(R.id.btnSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsh.signout(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        findViewById(R.id.btnGoogleSignIn).setVisibility(View.VISIBLE);
                        findViewById(R.id.cardUser).setVisibility(View.GONE);
                    }
                });
            }
        });

        // Loading User Image
        ImageView ivUser = findViewById(R.id.ivUser);
        Picasso.get()
                .load(account.getPhotoUrl())
                .centerInside()
                .resize(500, 500)
                .error(R.drawable.ic_broken_image)
                .into(ivUser, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) ivUser.getDrawable()).getBitmap();
                        ivUser.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
