package com.china.one.login;

/**
 * Copyright (c) 2017 Codelight Studios
 * Created by kalyandechiraju on 22/04/17.
 */

public class SmartLoginFactory {
    public static SmartLogin build(LoginType loginType) {
        switch (loginType) {
            case Facebook:
                //return new qqLogin();
            case Google:
                //return new wbLogin();
            case CustomLogin:
                return new CustomLogin();
            default:
                // To avoid null pointers
                return new CustomLogin();
        }
    }
}
