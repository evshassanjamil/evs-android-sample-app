package com.evs.android.mysampleapp.week9.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;

import com.evs.android.mysampleapp.utils.AppUtils;

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
    public void saveLoginSession(@NonNull String username, @NonNull String email, boolean remember) {
        mEditor.putString(PREF_KEY_USER_NAME, username);
        mEditor.putString(PREF_KEY_USER_EMAIL, email);
        mEditor.putBoolean(PREF_KEY_REMEMBER_LOGIN, remember);
        mEditor.commit();
    }

    public void rememberLogin(boolean remember) {
        mEditor.putBoolean(PREF_KEY_REMEMBER_LOGIN, remember);
        mEditor.commit();
    }

    public boolean isUserLoggedIn() {
        String email = mPreference.getString(PREF_KEY_USER_EMAIL, null);
        return AppUtils.isValidString(email);
    }

    public boolean isRememberLogin() {
        return mPreference.getBoolean(PREF_KEY_REMEMBER_LOGIN, false);
    }

    private String getUserEmail() {
        return mPreference.getString(PREF_KEY_USER_EMAIL, null);
    }

    public String getUserName() {
        return mPreference.getString(PREF_KEY_USER_NAME, "");
    }

    public void logoutUser() {
        mEditor.remove(PREF_KEY_USER_NAME);
        mEditor.remove(PREF_KEY_USER_EMAIL);
        mEditor.remove(PREF_KEY_REMEMBER_LOGIN);
        mEditor.commit();
    }

    public void clearAll() {
        mEditor.clear();
        mEditor.commit();
    }
}
