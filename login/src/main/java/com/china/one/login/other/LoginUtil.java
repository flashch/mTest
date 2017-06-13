package com.china.one.login.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.china.one.login.other.otherlogin.LoginListener;
import com.china.one.login.other.otherlogin.LoginPlatform;
import com.china.one.login.other.otherlogin.LoginResult;
import com.china.one.login.other.otherlogin.instance.LoginInstance;
import com.china.one.login.other.otherlogin.instance.QQLoginInstance;
import com.china.one.login.other.otherlogin.instance.WeiboLoginInstance;
import com.china.one.login.other.otherlogin.instance.WxLoginInstance;
import com.china.one.login.other.otherlogin.result.BaseToken;


/**
 * Created by shaohui on 2016/12/3.
 */

public class LoginUtil {

    private static LoginInstance mLoginInstance;

    private static LoginListener mLoginListener;

    private static int mPlatform;

    private static boolean isFetchUserInfo;

    static final int TYPE = 799;

    public static void login(Context context, @LoginPlatform.Platform int platform,
            LoginListener listener) {
        login(context, platform, listener, true);
    }

    public static void login(Context context, @LoginPlatform.Platform int platform,
            LoginListener listener, boolean fetchUserInfo) {
        mPlatform = platform;
        mLoginListener = new LoginListenerProxy(listener);
        isFetchUserInfo = fetchUserInfo;
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    static void action(Activity activity) {
        switch (mPlatform) {
            case LoginPlatform.QQ:
                mLoginInstance = new QQLoginInstance(activity, mLoginListener, isFetchUserInfo);
                break;
            case LoginPlatform.WEIBO:
                mLoginInstance = new WeiboLoginInstance(activity, mLoginListener, isFetchUserInfo);
                break;
            case LoginPlatform.WX:
                mLoginInstance = new WxLoginInstance(activity, mLoginListener, isFetchUserInfo);
                break;
            default:
                mLoginListener.loginFailure(new Exception(ShareLogger.INFO.UNKNOW_PLATFORM));
                activity.finish();
        }
        mLoginInstance.doLogin(activity, mLoginListener, isFetchUserInfo);
    }

    static void handleResult(int requestCode, int resultCode, Intent data) {
        if (mLoginInstance != null) {
            mLoginInstance.handleResult(requestCode, resultCode, data);
        }
    }

    public static void recycle() {
        if (mLoginInstance != null) {
            mLoginInstance.recycle();
        }
        mLoginInstance = null;
        mLoginListener = null;
        mPlatform = 0;
        isFetchUserInfo = false;
    }

    private static class LoginListenerProxy extends LoginListener {

        private LoginListener mListener;

        LoginListenerProxy(LoginListener listener) {
            mListener = listener;
        }

        @Override
        public void loginSuccess(LoginResult result) {
            ShareLogger.i(ShareLogger.INFO.LOGIN_SUCCESS);
            mListener.loginSuccess(result);
            recycle();
        }

        @Override
        public void loginFailure(Exception e) {
            ShareLogger.i(ShareLogger.INFO.LOGIN_FAIl);
            mListener.loginFailure(e);
            recycle();
        }

        @Override
        public void loginCancel() {
            ShareLogger.i(ShareLogger.INFO.LOGIN_CANCEL);
            mListener.loginCancel();
            recycle();
        }

        @Override
        public void beforeFetchUserInfo(BaseToken token) {
            ShareLogger.i(ShareLogger.INFO.LOGIN_AUTH_SUCCESS);
            mListener.beforeFetchUserInfo(token);
        }
    }
}
