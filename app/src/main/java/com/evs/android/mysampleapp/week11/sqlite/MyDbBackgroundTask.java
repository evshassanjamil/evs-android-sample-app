package com.evs.android.mysampleapp.week11.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

public class MyDbBackgroundTask extends AsyncTask<String, Void, String> {

    private DatabaseHandler dbHandler;

    MyDbBackgroundTask(Context context, DatabaseHandler.OnDbTransactionListener listener) {
        dbHandler = DatabaseHandler.getInstance(context);
        dbHandler.setOnDbTransactionListener(listener);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        if (params[0].equalsIgnoreCase(DatabaseHandler.Commands.ADD.name())) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHandler.COL_NAME, params[1]);
            values.put(DatabaseHandler.COL_EMAIL, params[2]);
            values.put(DatabaseHandler.COL_PHONE, params[3]);
            dbHandler.insert(DatabaseHandler.TABLE_USERS, values);
        } else if (params[0].equalsIgnoreCase(DatabaseHandler.Commands.GET.name())) {
            return dbHandler.get("SELECT * FROM " + DatabaseHandler.TABLE_USERS);
        } else if (params[0].equalsIgnoreCase(DatabaseHandler.Commands.CLEAR_DB.name())) {
            dbHandler.clear(new String[]{DatabaseHandler.TABLE_USERS});
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
    }
}
