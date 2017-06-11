package com.china.one.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.china.one.login.user.LoginUser;
import com.google.gson.Gson;


/**
 * 用户缓存信息管理
 */
public class UserSessionManager {

    /**
     * This static method can be called to get the logged in user.
     * It reads from the shared preferences and builds a LoginUser object and returns it.
     * If no user is logged in it returns null
     */
    public static LoginUser getCurrentUser(Context context) {
        LoginUser smartUser = null;
        SharedPreferences preferences = context.getSharedPreferences(Constants.USER_MSG, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String sessionUser = preferences.getString(Constants.USER_SESSION, Constants.DEFAULT_SESSION_VALUE);
        String user_type = preferences.getString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG);
        if (!sessionUser.equals(Constants.DEFAULT_SESSION_VALUE)) {
            try {
                switch (user_type) {
                    case Constants.FACEBOOKFLAG:
                        //smartUser = gson.fromJson(sessionUser,  QqUser.class);
                        break;
                    case Constants.GOOGLEFLAG:
                        //smartUser = gson.fromJson(sessionUser, WbUser.class);
                        break;
                    default:
                        smartUser = gson.fromJson(sessionUser, LoginUser.class);
                        break;
                }
            } catch (Exception e) {
                Log.e("GSON", e.getMessage());
            }
        }
        return smartUser;
    }

    /**
     * This method sets the session object for the current logged in user.
     * This is called from inside the SmartLoginActivity to save the
     * current logged in user to the shared preferences.
     */
    static boolean setUserSession(Context context, LoginUser smartUser) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        if (smartUser != null) {
            try {
                preferences = context.getSharedPreferences(Constants.USER_MSG, Context.MODE_PRIVATE);
                editor = preferences.edit();
                editor.putString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG);
                Gson gson = new Gson();
                String sessionUser = gson.toJson(smartUser);
                Log.d("GSON", sessionUser);
                editor.putString(Constants.USER_SESSION, sessionUser);
                editor.apply();
                return true;
            } catch (Exception e) {
                Log.e("Session Error", e.getMessage());
                return false;
            }
        } else {
            Log.e("Session Error", "LoginUser is null");
            return false;
        }
    }
}
