package com.china.one.myroutertest.mvp.contract;

import com.china.one.common.mvp.IModel;
import com.china.one.common.mvp.IView;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.QueryMap;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-09
 */

public interface OtherVideoContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getOtherList(List<VideoRecommendHotCate> homeCates);
        void getOtherListRefresh(List<VideoRecommendHotCate> homeCates);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<List<VideoRecommendHotCate>> getHomeCate(@QueryMap Map<String, String> maps);
    }
}