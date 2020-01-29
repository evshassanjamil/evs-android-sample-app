package com.evs.android.mysampleapp.week6.intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;
import com.evs.android.mysampleapp.week9.preference.AppPreferences;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class ActivityA extends AppCompatActivity {

    public final String TAG = ActivityA.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppPreferences appPreferences = AppPreferences.getInstance(this);

        if (appPreferences.isRememberLogin() && appPreferences.isUserLoggedIn()) {
            moveToNext(appPreferences.getUserName());
        }

        setContentView(R.layout.activity_a);

        // Implementing Submit button on click listener
        findViewById(R.id.btnSubmit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText etName = findViewById(R.id.etName);
                        EditText etEmail = findViewById(R.id.etEmail);
                        EditText etPassword = findViewById(R.id.etPassword);
                        CheckBox cbRemember = findViewById(R.id.cbRemember);
                        // Getting on click listener callback
                        // Passing username via intent to ActivityB and starting it
                        String name = etName.getText().toString();
                        String email = etEmail.getText().toString();
                        String password = etPassword.getText().toString();
                        boolean remember = cbRemember.isChecked();

                        // Data Validation
                        if (!AppUtils.isValidString(name)) {
                            etName.setError(getString(R.string.name_invalid));
                            return;
                        } else {
                            etName.setError(null);
                        }

                        if (!AppUtils.isValidEmail(email)) {
                            etEmail.setError(getString(R.string.email_invalid));
                            return;
                        } else {
                            etEmail.setError(null);
                        }

                        if (!AppUtils.isValidString(password) || !password.equals("123456")) {
                            etPassword.setError(getString(R.string.wrong_password));

                            String msg = getString(R.string.wrong_password);
                            Log.e(TAG, msg);
                            Toast.makeText(ActivityA.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            etPassword.setError(null);
                            // Saving Preferences
                            appPreferences.saveLoginSession(name, email, remember);
                            // Move to next activity
                            moveToNext(name);
                        }
                    }
                });
    }

    private void moveToNext(String name) {
        // Starting ActivityB via intent
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        // Passing username through getting it from etUsername
        intent.putExtra(AppPreferences.PREF_KEY_USER_NAME, name);
        startActivity(intent);

        finish();
    }
}
