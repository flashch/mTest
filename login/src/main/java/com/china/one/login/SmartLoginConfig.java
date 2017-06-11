package com.china.one.login;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Copyright (c) 2016 Codelight Studios
 * Created by Kalyan on 9/9/2015.
 */
public class SmartLoginConfig {
    private Activity activity;
    private SmartLoginCallbacks callback;


    public SmartLoginConfig(@NonNull Activity activity, SmartLoginCallbacks callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public Activity getActivity() {
        return activity;
    }

    public SmartLoginCallbacks getCallback() {
        return callback;
    }

    public static ArrayList<String> getDefaultFacebookPermissions() {
        ArrayList<String> defaultPermissions = new ArrayList<>();
        defaultPermissions.add("public_profile");
        defaultPermissions.add("email");
        defaultPermissions.add("user_birthday");
        return defaultPermissions;
    }

}
