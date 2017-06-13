package com.china.one.login_module.model;

import android.app.Application;

import com.china.one.common.di.scope.ActivityScope;
import com.china.one.common.integration.IRepositoryManager;
import com.china.one.common.mvp.BaseModel;
import com.china.one.login_module.contract.LoginContract;
import com.china.one.login_module.entity.VerificationResponse;
import com.china.one.login_module.service.CommonService;
import com.google.gson.Gson;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-12
 */

@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;
    private IRepositoryManager mRepositoryManager;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
        this.mRepositoryManager = repositoryManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<VerificationResponse> getVerification(Map maps) {
        Observable verification = mRepositoryManager.obtainRetrofitService(CommonService.class).getVerification(maps);
        return verification;
    }
}