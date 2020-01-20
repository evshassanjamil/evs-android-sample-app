package com.evs.android.mysampleapp.week7.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.evs.android.mysampleapp.R;

public class CVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DECLARE CUSTOM VIEW IN XML
        setContentView(R.layout.activity_custom_view);
        ClearableAutoCompleteTextView catv = findViewById(R.id.catvType);
        catv.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {
                catv.setText("");
                Toast.makeText(CVActivity.this, "Clear", Toast.LENGTH_SHORT).show();
            }
        });

        // ADDING CUSTOM VIEW PROGRAMMATICALLY AT RUNTIME
        /*ClearableAutoCompleteTextView catv = new ClearableAutoCompleteTextView(this);
        catv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        catv.setHint(getString(R.string.type));
        catv.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {
            @Override
            public void onClear() {
                catv.setText("");
                Toast.makeText(CVActivity.this, "Clear", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout ll = new LinearLayout(this);
        ll.addView(catv);
        setContentView(ll);*/
    }
}
