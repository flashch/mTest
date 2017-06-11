package com.china.one.myroutertest.mvp.presenter;

import android.app.Application;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.common.integration.AppManager;
import com.china.one.common.mvp.BasePresenter;
import com.china.one.common.rxerrorhandler.core.RxErrorHandler;
import com.china.one.common.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.china.one.common.rxerrorhandler.handler.RetryWithDelay;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.china.one.myroutertest.mvp.contract.VideoContract;
import com.china.one.myroutertest.mvp.model.api.ParamsMapUtils;
import com.china.one.myroutertest.mvp.model.entity.VideoCateList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-07
 */

@FragmentScope
public class VideoPresenter extends BasePresenter<VideoContract.Model, VideoContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public VideoPresenter(VideoContract.Model model, VideoContract.View rootView
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

    public void getHomeCateList() {
        mModel.getHomeCateList(ParamsMapUtils.getDefaultParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3,2))
                .doOnSubscribe(disposable -> {
                    //请求数据时的操作，比如显示正在刷新
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    //执行请求数据完成的操作，比如隐藏刷新布局
                })
                .subscribe(new ErrorHandleSubscriber<List<VideoCateList>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull List<VideoCateList> homeCateList) {
                        mRootView.getHomeAllList(homeCateList);
                    }
                });
    }
}
