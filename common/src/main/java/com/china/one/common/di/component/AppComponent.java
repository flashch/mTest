package com.china.one.common.di.component;

import android.app.Application;

import com.china.one.common.base.BaseApplication;
import com.china.one.common.di.module.AppModule;
import com.china.one.common.di.module.ClientModule;
import com.china.one.common.di.module.GlobeConfigModule;
import com.china.one.common.di.module.ImageModule;
import com.china.one.common.integration.AppManager;
import com.china.one.common.integration.IRepositoryManager;
import com.china.one.common.rxerrorhandler.core.RxErrorHandler;
import com.china.one.common.widget.imageloader.ImageLoader;
import com.google.gson.Gson;
import java.io.File;
import javax.inject.Singleton;
import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ImageModule.class, GlobeConfigModule.class})
public interface AppComponent {
    Application Application();

    //用于管理网络请求层,以及数据缓存层
    IRepositoryManager repositoryManager();

    //Rxjava错误处理管理类
    RxErrorHandler rxErrorHandler();


    OkHttpClient okHttpClient();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //gson
    Gson gson();

    //缓存文件根目录(RxCache和Glide的的缓存都已经作为子文件夹在这个目录里),应该将所有缓存放到这个根目录里,便于管理和清理,可在GlobeConfigModule里配置
    File cacheFile();

    //用于管理所有activity
    AppManager appManager();

    void inject(BaseApplication application);
}
