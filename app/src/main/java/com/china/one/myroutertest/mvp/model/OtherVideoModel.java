package com.china.one.myroutertest.mvp.model;

import android.app.Application;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.common.integration.IRepositoryManager;
import com.china.one.common.mvp.BaseModel;
import com.china.one.myroutertest.mvp.contract.OtherVideoContract;
import com.china.one.myroutertest.mvp.model.api.service.LivingService;
import com.china.one.myroutertest.mvp.model.entity.BaseJson;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.http.QueryMap;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-09
 */

@FragmentScope
public class OtherVideoModel extends BaseModel implements OtherVideoContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public OtherVideoModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<List<VideoRecommendHotCate>> getHomeCate(@QueryMap Map<String, String> maps) {
        Observable<BaseJson<List<VideoRecommendHotCate>>> homeCate = mRepositoryManager.obtainRetrofitService(LivingService.class).getHomeCate(maps);
        return homeCate.flatMap(new Function<BaseJson<List<VideoRecommendHotCate>>, ObservableSource<List<VideoRecommendHotCate>>>() {
            @Override
            public ObservableSource<List<VideoRecommendHotCate>> apply(@NonNull BaseJson<List<VideoRecommendHotCate>> listBaseJson) throws Exception {
                return Observable.just(listBaseJson.getData());
            }
        });

    }
}