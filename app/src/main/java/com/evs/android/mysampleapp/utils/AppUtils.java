package com.evs.android.mysampleapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class AppUtils {

    /**
     * Function returns the ellipsized string only if outLength is greater than text length.<br/>
     * e.g. abcdefghijklmnop AS abcdefghij... IF outLength=10
     */
    public static String getEllipsizedSubstring(String text, int outLength) {
        if (text.length() < outLength)
            return text;
        else
            return text.substring(0, outLength - 1) + "...";
    }


    /**
     * Method is used for checking valid email format.
     *
     * @return boolean true for valid, false for invalid
     */
    @SuppressWarnings("unused")
    public static boolean isValidEmail(String email) {
        boolean isValid = false;
        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Method is used for checking valid email format.
     *
     * @return boolean true for valid, false for invalid
     */
    public static boolean isValidEmail2(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Method is used for checking web url format.
     *
     * @return boolean true for valid, false for invalid
     */
    public static boolean isValidWebURL(String text) {
        return isValidString(text) || Patterns.WEB_URL.matcher(text).matches()
                && (text.trim().startsWith("http://") || text.trim().startsWith("https://"));
    }

    /**
     * Method is used for checking valid String.
     *
     * @return boolean true for valid, false for invalid
     */
    public static boolean isValidString(String str) {
        return str != null && !(TextUtils.isEmpty(str.trim()) || str.trim().equals("")) && !str.trim().equalsIgnoreCase("null");
    }

    /**
     * Used to hide/show keyboard by getting focused view.
     */
    @SuppressWarnings("SameParameterValue")
    public static void hideShowSoftKeyboardFromFocusedView(Activity activity, boolean shouldHide) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null)
                return;
            if (shouldHide)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            else
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /*
     * TOAST
     */
    private static Toast toast;

    public static void showToastShort(Context context, String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    /*
     *
     */
    public static void createAndShowAlertDialog(Context context, @DrawableRes int resId,
                                                String title, String message, boolean cancelable,
                                                boolean showDialog, String[] buttonTexts,
                                                DialogInterface.OnClickListener listener) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        if (resId != 0)
            alertDialogBuilder.setIcon(resId);

        alertDialogBuilder.setCancelable(cancelable);

        if (isValidString(title))
            alertDialogBuilder.setTitle(title);

        if (isValidString(message))
            alertDialogBuilder.setMessage(message);

        if (buttonTexts != null && buttonTexts.length > 0 && listener != null)
            alertDialogBuilder.setPositiveButton(buttonTexts[0], listener);

        if (buttonTexts != null && buttonTexts.length > 1 && listener != null)
            alertDialogBuilder.setNegativeButton(buttonTexts[1], listener);

        if (buttonTexts != null && buttonTexts.length > 2 && listener != null)
            alertDialogBuilder.setNeutralButton(buttonTexts[2], listener);

        AlertDialog alertDialog = alertDialogBuilder.create();

        if (showDialog)
            alertDialog.show();

    }
}
