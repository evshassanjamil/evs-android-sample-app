package com.evs.android.mysampleapp.week9.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class AppPreferences {

    private static AppPreferences mInstance;
    private final SharedPreferences mPreference;
    private final Editor mEditor;

    private final String PREF_FILE_KEY_APP = "com.evs.android.mysampleapp.APP_PREFERENCE";
    public static final String PREF_KEY_USER_NAME = "username";
    private final String PREF_KEY_USER_EMAIL = "email";
    private final String PREF_KEY_REMEMBER_LOGIN = "remember-login";

    private AppPreferences(Context context) {
        mPreference = context.getSharedPreferences(PREF_FILE_KEY_APP, Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
        mEditor.apply();
    }

    public static AppPreferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppPreferences(context);
        }

        return mInstance;
    }

    ///////////////////////////////// USER LOGIN SESSION /////////////////////////////////
    public void putString(@NonNull String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putBoolean(@NonNull String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getBoolean(@NonNull String key, boolean defValue) {
        return mPreference.getBoolean(key, defValue);
    }

    public String getString(@NonNull String key, String defValue) {
        return mPreference.getString(key, defValue);
    }

    public void clearAll() {
        mEditor.remove(PREF_KEY_REMEMBER_LOGIN);
        mEditor.clear();
        mEditor.commit();
    }
}
