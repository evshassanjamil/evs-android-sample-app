package com.evs.android.mysampleapp.week11.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hassanjamil on 2020-02-04.
 *
 * @author hassanjamil
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // DB CONSTANTS
    private static final String DATABASE_NAME = "main.db";
    private static final int DATABASE_VERSION = 1;
    // TABLES
    static final String TABLE_USERS = "Users";
    private static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_EMAIL = "email";
    static final String COL_PHONE = "phone";

    // COMMANDS
    public enum Commands {
        ADD, GET, CLEAR_DB
    }

    // CREATE USER TABLE
    private String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_EMAIL + " TEXT, "
            + COL_PHONE + " TEXT );";

    private static final String TAG = DatabaseHandler.class.getSimpleName();

    private static DatabaseHandler mInstance;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DatabaseHandler(context);

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            enableForeignKeysConstraint(db);
            createTableTasks(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTableTasks(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    /**
     * We need to enable foreign keys before executing any query creating a table, also on database
     * configuration changes. For it, this method helps.
     *
     * @param db SQLiteDatabase
     */
    private void enableForeignKeysConstraint(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO WRITE DATABASE DB UPGRADE OPERATIONS HERE
    }

    void insert(String tableName, ContentValues contentValues) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            long id = db.insert(tableName, null, contentValues);
            if (id != -1) {
                if (mListener != null)
                    mListener.onRecordInserted(id, "Insertion success");
            } else {
                if (mListener != null)
                    mListener.onRecordInserted(id, "Insertion failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String get(String select) {
        StringBuilder results = new StringBuilder();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                results.append(cursor.getString(cursor.getColumnIndex(COL_ID))).append(",");
                results.append(cursor.getString(cursor.getColumnIndex(COL_NAME))).append(",");
                results.append(cursor.getString(cursor.getColumnIndex(COL_EMAIL))).append(",");
                results.append(cursor.getString(cursor.getColumnIndex(COL_PHONE))).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (mListener != null)
            mListener.onRecordFetched(results.toString());
        return results.toString();
    }

    private boolean hasData(String tableName, SQLiteDatabase db) {
        boolean flag;
        try {
            if (db == null)
                db = getReadableDatabase();
            String query = "SELECT  * FROM " + tableName + ";";
            Cursor cursor = db.rawQuery(query, null);
            flag = cursor.getCount() > 0;
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public void clear(String[] tables) {
        // NOTE: DROP CHILD TABLES FIRST and THEN PARENT TABLES.
        SQLiteDatabase db = getWritableDatabase();
        // Dropping Tables
        dropTable(db, tables);
        // Creating Tables
        onCreate(db);

        if (mListener != null)
            mListener.onDbCleared("Database Cleared");
    }

    private void dropTable(SQLiteDatabase db, String[] tableNames) {
        try {
            for (String tableName : tableNames) {
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
                Log.i(TAG, "dropTable(): TABLE NAMED" + tableName + " has been DROPPED");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * INTERFACES
     */

    public interface OnDbTransactionListener {
        void onRecordInserted(long id, String msg);

        void onRecordFetched(String msg);

        void onDbCleared(String msg);
    }

    private OnDbTransactionListener mListener;

    public void setOnDbTransactionListener(OnDbTransactionListener listener) {
        mListener = listener;
    }
}
