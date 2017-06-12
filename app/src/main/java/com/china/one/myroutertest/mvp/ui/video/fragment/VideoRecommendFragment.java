package com.china.one.myroutertest.mvp.ui.video.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.china.one.common.base.BaseFragment;
import com.china.one.common.di.component.AppComponent;
import com.china.one.common.utils.UiUtils;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.app.GlideImageLoader;
import com.china.one.myroutertest.app.widgetutils.refreshview.XRefreshView;
import com.china.one.myroutertest.di.component.DaggerVideoRecommendComponent;
import com.china.one.myroutertest.di.module.VideoRecommendModule;
import com.china.one.myroutertest.mvp.contract.VideoRecommendContract;
import com.china.one.myroutertest.mvp.model.entity.VideoCarousel;
import com.china.one.myroutertest.mvp.model.entity.VideoHotColumn;
import com.china.one.myroutertest.mvp.presenter.video.VideoRecommendPresenter;
import com.china.one.myroutertest.mvp.ui.video.adapter.RecommendLivingAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.china.one.common.utils.Preconditions.checkNotNull;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-05-27
 */

public class VideoRecommendFragment extends BaseFragment<VideoRecommendPresenter> implements VideoRecommendContract.View, OnBannerListener {

    @BindView(R.id.recommend_content_recyclerview)
    RecyclerView recommendContentRecyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private RecommendLivingAdapter adapter;
    private View haderView;
    private List<VideoCarousel> mHomeCarousel = new ArrayList<>();
    private List images = new ArrayList();
    private Banner banner;
    private ArrayList<String> pic_url;
    private View view;

    public static VideoRecommendFragment newInstance() {
        VideoRecommendFragment fragment = new VideoRecommendFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerVideoRecommendComponent
                .builder()
                .appComponent(appComponent)
                .videoRecommendModule(new VideoRecommendModule(this))//请将videoModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_recommend, null);
        initViewDate(view);
       //refresh();
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        refresh();
    }

    private void initViewDate(View view) {
        recommendContentRecyclerview = (RecyclerView) view.findViewById(R.id.recommend_content_recyclerview);
        rtefreshContent = (XRefreshView) view.findViewById(R.id.rtefresh_content);
        setXrefeshViewConfig();
        recommendContentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendLivingAdapter(getContext());
        //轮播条
        haderView = adapter.setHeaderView(R.layout.item_living_recommend_banner, recommendContentRecyclerview);
        recommendContentRecyclerview.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recommendContentRecyclerview.setAdapter(adapter);
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 500);
            }
        });
    }

    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(false);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
    }
    private void refresh() {
        mPresenter.getPresenterCarousel();
        mPresenter.getPresenterHotColumn();
    }

    //获取热门资源传给adapter
    @Override
    public void getViewHotColumn(List<VideoHotColumn> mHomeHotColumn) {
        adapter.getHomeHotColumn(mHomeHotColumn);
    }

    @Override
    public void getViewCarousel(List<VideoCarousel> mHomeCarousel) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        this.mHomeCarousel.clear();
        this.mHomeCarousel.addAll(mHomeCarousel);
//        recommed_banner.setDelegate(this);
        pic_url = new ArrayList<String>();
        for (int i = 0; i < mHomeCarousel.size(); i++) {
            pic_url.add(mHomeCarousel.get(i).getPic_url());
        }
        images = new ArrayList(pic_url);
        banner = (Banner) haderView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //切换动画
        banner.setBannerAnimation(AccordionTransformer.class);
        //点击事件
        banner.setOnBannerListener(this);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        adapter.notifyDataSetChanged();
    }

    public void refreshError() {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh(false);
        }
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}