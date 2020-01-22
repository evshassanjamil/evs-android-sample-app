package com.evs.android.mysampleapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

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
        return str != null && !(TextUtils.isEmpty(str.trim()) || str.trim().equals(""));
    }
}
