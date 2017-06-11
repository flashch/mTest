package com.china.one.myroutertest.mvp.presenter.video;

import android.app.Application;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.common.integration.AppManager;
import com.china.one.common.mvp.BasePresenter;
import com.china.one.common.rxerrorhandler.core.RxErrorHandler;
import com.china.one.common.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.china.one.common.rxerrorhandler.handler.RetryWithDelay;
import com.china.one.common.utils.LogUtils;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.china.one.myroutertest.mvp.contract.VideoRecommendContract;
import com.china.one.myroutertest.mvp.model.api.ParamsMapUtils;
import com.china.one.myroutertest.mvp.model.entity.VideoCarousel;
import com.china.one.myroutertest.mvp.model.entity.VideoHotColumn;
import com.china.one.myroutertest.mvp.ui.video.fragment.VideoRecommendFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-05-27
 */

@FragmentScope
public class VideoRecommendPresenter extends BasePresenter<VideoRecommendContract.Model, VideoRecommendContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private VideoRecommendFragment videoFragment;

    @Inject
    public VideoRecommendPresenter(VideoRecommendContract.Model model, VideoRecommendContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        this.videoFragment= (VideoRecommendFragment) rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    //最热
    public void getPresenterHotColumn() {
        mModel.getModelHotColumn(ParamsMapUtils.getDefaultParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 3))
                .doOnSubscribe(disposable -> {
                    //显示刷新头
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    //隐藏刷新头
                })
                .subscribe(new ErrorHandleSubscriber<List<VideoHotColumn>>(mErrorHandler) {
                    @Override
                    public void onNext(List<VideoHotColumn> homeHotColumns) {
                        LogUtils.warnInfo("获取到最热数据");
                        videoFragment.getViewHotColumn(homeHotColumns);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        videoFragment.refreshError();
                    }
                });
    }

    //轮播条
    public void getPresenterCarousel() {
        mModel.getModelHomeCarousel(ParamsMapUtils.getDefaultParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 3))
                .doOnSubscribe(disposable ->{

                } )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {

                })
                .subscribe(new ErrorHandleSubscriber<List<VideoCarousel>>(mErrorHandler) {
                    @Override
                    public void onNext(List<VideoCarousel> homeCarousel) {
                        LogUtils.warnInfo("获取到轮播条数据");
                        videoFragment.getViewCarousel(homeCarousel);
                    }
                });
    }

}
