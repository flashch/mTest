package com.china.one.myroutertest.mvp.model;

import android.app.Application;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.common.integration.IRepositoryManager;
import com.china.one.common.mvp.BaseModel;
import com.china.one.myroutertest.mvp.contract.VideoContract;
import com.china.one.myroutertest.mvp.model.api.service.LivingService;
import com.china.one.myroutertest.mvp.model.entity.BaseJson;
import com.china.one.myroutertest.mvp.model.entity.VideoCateList;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

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
public class VideoModel extends BaseModel implements VideoContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public VideoModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

    public Observable<List<VideoCateList>> getHomeCateList(Map maps) {
        Observable<BaseJson<List<VideoCateList>>> homeCateList = mRepositoryManager.obtainRetrofitService(LivingService.class).getHomeCateList(maps);
     return homeCateList.flatMap(new Function<BaseJson<List<VideoCateList>>, ObservableSource<List<VideoCateList>>>() {
         @Override
         public ObservableSource<List<VideoCateList>> apply(@NonNull BaseJson<List<VideoCateList>> listBaseJson) throws Exception {
             return Observable.just(listBaseJson.getData());
         }
     });
    }
}