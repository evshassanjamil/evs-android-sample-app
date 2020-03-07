package com.evs.android.mysampleapp.week15;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Week15Activity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week15);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        findViewById(R.id.btnLogEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Button");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "LogEvent");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });

    }
}
