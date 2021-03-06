package com.evs.android.mysampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.week10.Week10Activity;
import com.evs.android.mysampleapp.week11.Week11Activity;
import com.evs.android.mysampleapp.week12.Week12Activity;
import com.evs.android.mysampleapp.week13.Week13Activity;
import com.evs.android.mysampleapp.week14.Week14Activity;
import com.evs.android.mysampleapp.week15.Week15Activity;
import com.evs.android.mysampleapp.week6.Week6Activity;
import com.evs.android.mysampleapp.week7.Week7Activity;
import com.evs.android.mysampleapp.week8.Week8Activity;
import com.evs.android.mysampleapp.week9.Week9Activity;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWeek6).setOnClickListener(this);
        findViewById(R.id.btnWeek7).setOnClickListener(this);
        findViewById(R.id.btnWeek8).setOnClickListener(this);
        findViewById(R.id.btnWeek9).setOnClickListener(this);
        findViewById(R.id.btnWeek10).setOnClickListener(this);
        findViewById(R.id.btnWeek11).setOnClickListener(this);
        findViewById(R.id.btnWeek12).setOnClickListener(this);
        findViewById(R.id.btnWeek13).setOnClickListener(this);
        findViewById(R.id.btnWeek14).setOnClickListener(this);
        findViewById(R.id.btnWeek15).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnWeek6:
                startActivity(new Intent(MainActivity.this, Week6Activity.class));
                break;
            case R.id.btnWeek7:
                startActivity(new Intent(MainActivity.this, Week7Activity.class));
                break;
            case R.id.btnWeek8:
                startActivity(new Intent(MainActivity.this, Week8Activity.class));
                break;
            case R.id.btnWeek9:
                startActivity(new Intent(MainActivity.this, Week9Activity.class));
                break;
            case R.id.btnWeek10:
                startActivity(new Intent(MainActivity.this, Week10Activity.class));
                break;
            case R.id.btnWeek11:
                startActivity(new Intent(MainActivity.this, Week11Activity.class));
                break;
            case R.id.btnWeek12:
                startActivity(new Intent(MainActivity.this, Week12Activity.class));
                break;
            case R.id.btnWeek13:
                startActivity(new Intent(MainActivity.this, Week13Activity.class));
                break;
            case R.id.btnWeek14:
                startActivity(new Intent(MainActivity.this, Week14Activity.class));
                break;
            case R.id.btnWeek15:
                startActivity(new Intent(MainActivity.this, Week15Activity.class));
                break;
        }
    }
}
