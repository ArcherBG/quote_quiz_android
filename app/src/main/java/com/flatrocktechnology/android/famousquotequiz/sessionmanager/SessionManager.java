package com.flatrocktechnology.android.famousquotequiz.sessionmanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Use this class to store user information and preferences.
 */
public class SessionManager {

    private static final String SESSION_NAME = "session";
    private static final String IS_USER_FIRST_TIME = "firstTimeUser";
    private static final String IS_BINARY_MODE_QUIZ_ENABLED = "binaryMode";
    private static SharedPreferences sp;

    private static SessionManager instance;


    private SessionManager() {
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager();
            sp = context
                    .getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);

        }

        return instance;
    }

    /* Get stored mode */
    public boolean isBinaryModeQuizEnabled() {
        return sp.getBoolean(IS_BINARY_MODE_QUIZ_ENABLED, true);
    }

    /* Save user choice for mode */
    public void setIsBinaryModeQuizEnabled(boolean isEnabled) {
        sp.edit().putBoolean(IS_BINARY_MODE_QUIZ_ENABLED, isEnabled).apply();
    }

    /* Get if the user is first time vising the app. */
    public  boolean getIsUserFirstTime() {
        return sp.getBoolean(IS_USER_FIRST_TIME, true);
    }

    /* Save if the user has visit the app more than once. */
    public void setIsUserFirstTime(boolean isFirstTime) {
        sp.edit().putBoolean(IS_USER_FIRST_TIME, isFirstTime).apply();
    }


}
