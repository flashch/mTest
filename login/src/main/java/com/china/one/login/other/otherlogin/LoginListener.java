package com.china.one.login.other.otherlogin;
import com.china.one.login.other.otherlogin.result.BaseToken;

/**
 * Created by shaohui on 2016/12/2.
 */

public abstract class LoginListener {

    public abstract void loginSuccess(LoginResult result);

    public void beforeFetchUserInfo(BaseToken token) {
    }

    public abstract void loginFailure(Exception e);

    public abstract void loginCancel();
}
