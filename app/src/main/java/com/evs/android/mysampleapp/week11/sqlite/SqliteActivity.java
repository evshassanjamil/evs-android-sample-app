package com.evs.android.mysampleapp.week11.sqlite;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;

public class SqliteActivity extends AppCompatActivity {

    DatabaseHandler.OnDbTransactionListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        listener = new DatabaseHandler.OnDbTransactionListener() {
            @Override
            public void onRecordInserted(long id, String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.showToastShort(SqliteActivity.this, msg);

                        TextView tvUsers = findViewById(R.id.tvUsers);
                        String message = "Inserted Record Id: " + id;
                        tvUsers.scrollTo(0, 0);
                        tvUsers.setText(message);
                    }
                });
            }

            @Override
            public void onRecordFetched(String msg) {
                if (!AppUtils.isValidString(msg))
                    return;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tvUsers = findViewById(R.id.tvUsers);
                        tvUsers.setMovementMethod(new ScrollingMovementMethod());
                        tvUsers.setText(msg);
                    }
                });
            }

            @Override
            public void onDbCleared(String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tvUsers = findViewById(R.id.tvUsers);
                        tvUsers.scrollTo(0, 0);
                        tvUsers.setText(msg);
                    }
                });
            }
        };

        findViewById(R.id.btnAddUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUserName = findViewById(R.id.etUserName);
                EditText etUserEmail = findViewById(R.id.etUserEmail);
                EditText etUserPhone = findViewById(R.id.etUserPhone);
                String name = etUserName.getText().toString();
                String email = etUserEmail.getText().toString();
                String phone = etUserPhone.getText().toString();

                etUserName.setError(!AppUtils.isValidString(name) ? "Incorrect Name" : null);
                etUserEmail.setError(!AppUtils.isValidEmail(email) ? "Incorrect Email" : null);
                etUserPhone.setError(!AppUtils.isValidString(phone) ? "Incorrect Phone" : null);

                if (!AppUtils.isValidString(name) || !AppUtils.isValidEmail(email) || !AppUtils.isValidString(phone))
                    return;

                MyDbBackgroundTask dbTask = new MyDbBackgroundTask(SqliteActivity.this, listener);
                dbTask.execute(DatabaseHandler.Commands.ADD.name(), name, email, phone);

                /*for (int i = 0; i < 1000; i++) {
                    MyDbBackgroundTask dbTask = new MyDbBackgroundTask(SqliteActivity.this, listener);
                    dbTask.execute(DatabaseHandler.Commands.ADD.name(), name, email, phone);
                }*/
            }
        });

        findViewById(R.id.btnGetUsers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbBackgroundTask task = new MyDbBackgroundTask(SqliteActivity.this, listener);
                task.execute(DatabaseHandler.Commands.GET.name());
            }
        });

        findViewById(R.id.btnClearDb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvUsers = findViewById(R.id.tvUsers);
                tvUsers.setText("");

                MyDbBackgroundTask task = new MyDbBackgroundTask(SqliteActivity.this, listener);
                task.execute(DatabaseHandler.Commands.CLEAR_DB.name());
            }
        });
    }
}
