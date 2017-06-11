package com.china.one.login;
import com.china.one.login.user.LoginUser;

/**
 * Copyright (c) 2017 Codelight Studios
 * Created by kalyandechiraju on 22/04/17.
 */

public interface SmartLoginCallbacks {

    void onLoginSuccess(LoginUser user);

    void onLoginFailure(SmartLoginException e);

    LoginUser doCustomLogin();

    LoginUser doCustomSignup();
}
