package com.china.one.myroutertest.mvp.model.api;

/**
* @Name: Api
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:06
*/

public interface Api {

    String RequestSuccess = "0";

    //    Base地址
    String baseUrl = "http://capi.douyucdn.cn";

    /**
     */
    //****************************推荐模块***************************************

    //    首页轮播
    String getCarousel = "/api/v1/slide/6";
    //    首页---推荐---热栏目
    String getHomeHotColumn = "/api/v1/getbigDataRoom";
    //    首页---颜值栏目
    String getHomeFaceScoreColumn = "/api/v1/getVerticalRoom";


    //****************************其他***************************************
//     首页列表
    String getHomeCateList = "/api/homeCate/getCateList";
    //     列表详情
    String getHomeCate = "/api/homeCate/getHotRoom";
}
