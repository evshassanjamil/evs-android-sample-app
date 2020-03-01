package com.evs.android.mysampleapp.week14;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.example.clearabletext.ClearableAutoCompleteTextView;

public class Week14Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week14);

        ClearableAutoCompleteTextView catvInput = findViewById(R.id.catvInput);
        catvInput.setSrcClear(this, R.drawable.ic_cancel);
        catvInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //String text = s.toString();
                //Toast.makeText(Week14Activity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
