package com.china.one.login_module.service;

import com.china.one.login_module.entity.VerificationResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {
    @POST("/v1/plugin/sms/send")
    Observable<VerificationResponse> getVerification(@QueryMap Map<String, String> maps);
}
