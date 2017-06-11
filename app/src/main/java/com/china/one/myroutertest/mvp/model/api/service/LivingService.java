package com.china.one.myroutertest.mvp.model.api.service;

import com.china.one.myroutertest.mvp.model.entity.BaseJson;
import com.china.one.myroutertest.mvp.model.entity.VideoCarousel;
import com.china.one.myroutertest.mvp.model.entity.VideoCateList;
import com.china.one.myroutertest.mvp.model.entity.VideoFaceScoreColumn;
import com.china.one.myroutertest.mvp.model.entity.VideoHotColumn;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.china.one.myroutertest.mvp.model.api.Api.getCarousel;
import static com.china.one.myroutertest.mvp.model.api.Api.getHomeCate;
import static com.china.one.myroutertest.mvp.model.api.Api.getHomeCateList;
import static com.china.one.myroutertest.mvp.model.api.Api.getHomeFaceScoreColumn;
import static com.china.one.myroutertest.mvp.model.api.Api.getHomeHotColumn;


/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface LivingService {
    //直播
    /**
     * 首页分类列表
     *
     * @return
     */
    @GET(getHomeCateList)
    Observable<BaseJson<List<VideoCateList>>> getHomeCateList(@QueryMap Map<String, String> params);

    /**
     * 首页 列表详情页
     *
     * @return
     */
    @GET(getHomeCate)
    Observable<BaseJson<List<VideoRecommendHotCate>>> getHomeCate(@QueryMap Map<String, String> params);
    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET(getCarousel)
    Observable<BaseJson<List<VideoCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getHomeHotColumn)
    Observable<BaseJson <List<VideoHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---颜值
     *
     * @return
     */
    @GET(getHomeFaceScoreColumn)
    Observable<List<VideoFaceScoreColumn>> getFaceScoreColumn(@QueryMap Map<String, String> params);

}
