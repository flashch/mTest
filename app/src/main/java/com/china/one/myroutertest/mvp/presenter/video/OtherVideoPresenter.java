package com.china.one.myroutertest.mvp.presenter.video;

import android.app.Application;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.common.integration.AppManager;
import com.china.one.common.mvp.BasePresenter;
import com.china.one.common.rxerrorhandler.core.RxErrorHandler;
import com.china.one.common.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.china.one.common.rxerrorhandler.handler.RetryWithDelay;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.china.one.myroutertest.mvp.contract.OtherVideoContract;
import com.china.one.myroutertest.mvp.model.api.ParamsMapUtils;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-09
 */

@FragmentScope
public class OtherVideoPresenter extends BasePresenter<OtherVideoContract.Model, OtherVideoContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public OtherVideoPresenter(OtherVideoContract.Model model, OtherVideoContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getHomeCate(String identification) {

        mModel.getHomeCate(ParamsMapUtils.getHomeCate(identification))
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2,3))
                .doOnSubscribe(disposable -> {
                    //执行访问网络同步操作
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    //执行网络访问完成的操作
                })
                .subscribe(new ErrorHandleSubscriber<List<VideoRecommendHotCate>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull List<VideoRecommendHotCate> homeRecommendHotCates) {
                        mRootView.getOtherList(homeRecommendHotCates);
                    }
                });

    }

    public void getHomeCateRefresh(String identification) {
        mModel.getHomeCate(ParamsMapUtils.getHomeCate(identification))
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2,3))
                .doOnSubscribe(disposable -> {
                    //执行访问网络同步操作
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    //执行网络访问完成的操作
                })
                .subscribe(new ErrorHandleSubscriber<List<VideoRecommendHotCate>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull List<VideoRecommendHotCate> homeRecommendHotCates) {
                        mRootView.getOtherListRefresh(homeRecommendHotCates);
                    }
                });
    }
}
