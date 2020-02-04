package com.evs.android.mysampleapp.week11.actionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;

public class ActionbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mi_change_lang:
                AppUtils.showToastShort(ActionbarActivity.this, "Change Language Clicked");
                return true;
            case R.id.mi_cart:
                AppUtils.showToastShort(ActionbarActivity.this, "Cart Clicked");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
