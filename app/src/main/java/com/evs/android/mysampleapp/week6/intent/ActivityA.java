package com.evs.android.mysampleapp.week6.intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

public class ActivityA extends AppCompatActivity {

    public final String TAG = ActivityA.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        // Implementing Submit button on click listener
        findViewById(R.id.btnSubmit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Getting on click listener callback
                        // Passing username via intent to ActivityB and starting it
                        String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
                        // Authenticating Password
                        if (password.equals("123456")) {
                            // Starting ActivityB via intent
                            Intent intent = new Intent(ActivityA.this, ActivityB.class);
                            // Passing username through getting it from etUsername
                            intent.putExtra("username",
                                    ((EditText) findViewById(R.id.etUsername)).getText().toString());
                            startActivity(intent);
                        } else {
                            String msg = "Password is incorrect";
                            Log.e(TAG, msg);
                            Toast.makeText(ActivityA.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
