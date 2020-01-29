package com.evs.android.mysampleapp.week6.intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week9.preference.AppPreferences;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        // Getting User name from previous activity and setting to the Welcome TextView
        String name = "???";
        if (getIntent().getExtras() != null && getIntent().hasExtra(AppPreferences.PREF_KEY_USER_NAME)) {
            name = getIntent().getExtras().getString(AppPreferences.PREF_KEY_USER_NAME);
        }
        name = "Welcome, " + name + "!";
        ((TextView) findViewById(R.id.tvUsername)).setText(name);

        // Logging user out on logout button click
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppPreferences.getInstance(ActivityB.this).logoutUser();
                finish();
            }
        });
    }
}
