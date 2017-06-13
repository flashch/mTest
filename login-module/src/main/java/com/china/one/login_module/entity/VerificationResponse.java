package com.china.one.login_module.entity;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-01-17
 */

public class VerificationResponse {

    /**
     * errorCode : 000000
     * errorDesc : 成功
     * data : []
     */

    private String errorCode;
    private String errorDesc;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
