package com.drawer.navigation.clipboard.util;

import android.util.Log;

import com.drawer.navigation.clipboard.BuildConfig;

/**
 * Manages efficient logs
 * 
 * @author Thana
 * @since 0.1
 * 
 */
public class Logger {


    public static final String DEFAULT_TAG = "Cura";
    public static final String LOG_ERROR = "e";
    public static final String LOG_INFO = "i";
    public static final String LOG_WARNING = "w";
    public static final String LOG_VERBOSE = "v";
    public static final String LOG_DEBUG = "d";

    /**
     * 
     * @param str
     *            the string to display on the logcat
     * @param mode
     *            the mode for catecorise the type of printing strings
     */
    public static void showLog(String message, String mode) {

        if (mode.equals(LOG_DEBUG)) {
            Log.d(DEFAULT_TAG, message);
        }
        else if (mode.equals(LOG_INFO)) {
            Log.i(DEFAULT_TAG, message);
        }
        else if (mode.equals(LOG_ERROR)) {
            Log.e(DEFAULT_TAG, message);
        }
        else if (mode.equals(LOG_VERBOSE)) {
            Log.v(DEFAULT_TAG, message);
        }
        else if (mode.equals(LOG_WARNING)) {
            Log.w(DEFAULT_TAG, message);
        }
        else {
            Log.d(DEFAULT_TAG, message);
        }
    }

    /**
     * 
     * @param mode
     *            the mode for cater the type of printing strings
     * @param TAG
     *            the specific tag for filter
     * @param message
     *            the message to print
     */

    public static void showLog(String mode, String TAG, String message) {

        if (BuildConfig.DEBUG) {

            if (mode.equals(LOG_DEBUG)) {
                Log.d(TAG, message);
            }
            else if (mode.equals(LOG_INFO)) {
                Log.i(TAG, message);
            }
            else if (mode.equals(LOG_ERROR)) {
                Log.e(TAG, message);
            }
            else if (mode.equals(LOG_VERBOSE)) {
                Log.v(TAG, message);
            }
            else if (mode.equals(LOG_WARNING)) {
                Log.w(TAG, message);
            }
            else {
                Log.d(TAG, message);
            }
        }
    }

    /**
     * print logs with tag
     * 
     * @param TAG
     *            the filter tag
     * @param message
     *            the log message
     */
    public static void showFilteredLog(String TAG, String message) {
        showLog(LOG_DEBUG, TAG, message);

    }

    /**
     * To print with debug mode by default
     * 
     * @param str
     *            the string print
     */
    public static void showLog(String str) {

        showLog(str, LOG_DEBUG);
    }

}
