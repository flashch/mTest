package com.china.one.myroutertest.mvp.ui.video.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.china.one.common.base.BaseFragment;
import com.china.one.common.di.component.AppComponent;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.app.refreshview.XRefreshView;
import com.china.one.myroutertest.di.component.DaggerOtherVideoComponent;
import com.china.one.myroutertest.di.module.OtherVideoModule;
import com.china.one.myroutertest.mvp.contract.OtherVideoContract;
import com.china.one.myroutertest.mvp.model.entity.VideoCateList;
import com.china.one.myroutertest.mvp.model.entity.VideoRecommendHotCate;
import com.china.one.myroutertest.mvp.presenter.video.OtherVideoPresenter;
import com.china.one.myroutertest.mvp.ui.video.adapter.VideoNgBarAdapter;
import com.china.one.myroutertest.mvp.ui.video.adapter.VideoNgBarViewPagerAdapter;
import com.china.one.myroutertest.mvp.ui.video.adapter.VideoOtherAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：首页 列表页  显示 手游,娱乐,游戏,趣玩等!
 * 修改时间：2016/12/14 下午8:17
 **/
public class OtherVideoFragment extends BaseFragment<OtherVideoPresenter> implements OtherVideoContract.View ,ViewPager.OnPageChangeListener{
    /**
     * 导航栏 分页
     */
////    小圆点指示器
//    protected ViewGroup mPoints;
    //    小圆点图片集合
    private ImageView[] mIvpoints;
    //添加HaderView
    private View haderView;
    //    导航栏
    ViewPager ngbarViewpager;

    private VideoCateList mHomeCate;

    @BindView(R.id.other_content_recyclerview)
    RecyclerView other_content_recyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private VideoOtherAdapter adapter;

    private static List<OtherVideoFragment> mOtherHomeFraments = new ArrayList<OtherVideoFragment>();

    private VideoNgBarViewPagerAdapter homeNgBarViewPagerAdapter;
    private VideoNgBarAdapter homeNgBarAdapter;

    public static OtherVideoFragment getInstance(VideoCateList args, int position) {
        OtherVideoFragment mInstance = new OtherVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("homecatelist", args);
        bundle.putString("type", args.getShow_order());
        bundle.putInt("position", position - 1);
        mInstance.setArguments(bundle);
        mOtherHomeFraments.add(position - 1, mInstance);
        return mInstance;
    }


    private void refresh() {
        Bundle arguments = getArguments();
        mHomeCate = (VideoCateList) arguments.getSerializable("homecatelist");
        String show_order = arguments.getString("type");
        if (show_order.equals(mHomeCate.getShow_order())) {
            mPresenter.getHomeCate(mHomeCate.getIdentification());
        }
    }


    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }
    };

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

    @Override
    public void getOtherList(List<VideoRecommendHotCate> homeCates) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        getOtherColumnView(homeCates);
        /**
         * 分页 导航栏+栏目列表
         *
         * @param homeCates
         */
        getNgBarView(homeCates);
    }

    @Override
    public void getOtherListRefresh(List<VideoRecommendHotCate> homeCates) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        List<VideoRecommendHotCate> homeRecommendHotCates = new ArrayList<VideoRecommendHotCate>();
        homeRecommendHotCates.addAll(homeCates);
        for (int i = 0; i < homeRecommendHotCates.size(); i++) {
            if (homeRecommendHotCates.get(i).getRoom_list().size() < 4) {
                homeRecommendHotCates.remove(i);
            }
        }
        if (adapter != null) {
            adapter.getAllColumn(homeCates);
        }
    }

    private void getOtherColumnView(List<VideoRecommendHotCate> homeCates) {
        List<VideoRecommendHotCate> homeRecommendHotCates = new ArrayList<VideoRecommendHotCate>();
        homeRecommendHotCates.addAll(homeCates);
        for (int i = 0; i < homeRecommendHotCates.size(); i++) {
            if (homeRecommendHotCates.get(i).getRoom_list().size() < 4) {
                homeRecommendHotCates.remove(i);
            }
        }
        /**
         *  栏目 列表
         */
        adapter = new VideoOtherAdapter(getContext(), homeRecommendHotCates);
        other_content_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        pool.setMaxRecycledViews(adapter.getItemViewType(0), 500);
        other_content_recyclerview.setRecycledViewPool(pool);
        other_content_recyclerview.setAdapter(adapter);
    }

    public void getNgBarView(List<VideoRecommendHotCate> homeCates) {

//    总共多少页
        int mTotalPage;
//    每页显示的最大数量  默认为8
        int mPageSize = 8;
// GridView 作为一个View对象添加到ViewPager集合中
        List<View> mViewPageList;
//    当前页
        int mCurrentPage;
        //     小圆点
        ViewGroup mPoints;

//        导航栏
        haderView = adapter.setHeaderView(R.layout.view_viewpager, other_content_recyclerview);
        ngbarViewpager = (ViewPager) haderView.findViewById(R.id.ngbar_viewpager);
        Bundle arguments = getArguments();
        ngbarViewpager.removeOnPageChangeListener(mOtherHomeFraments.get(arguments.getInt("position")));
        ngbarViewpager.addOnPageChangeListener(mOtherHomeFraments.get(arguments.getInt("position")));
        mPoints = (ViewGroup) haderView.findViewById(R.id.points);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        显示总的页数  Math.ceil 先上取整
        mTotalPage = (int) Math.ceil(homeCates.size() * 1.0 / mPageSize);
        mViewPageList = new ArrayList<>();
//        移除最热栏目
        homeCates.remove(0);
        /**
         *  创建 多个GredView
         */
        for (int i = 0; i < mTotalPage; i++) {
            if (i <= 1) {
                GridView gridView = (GridView) inflater.inflate(R.layout.view_layout_gridview, null);
                homeNgBarAdapter = new VideoNgBarAdapter(getContext(), homeCates, i, mPageSize);
                gridView.setAdapter(homeNgBarAdapter);
                homeNgBarAdapter.notifyDataSetChanged();
//            每一个GridView 作为一个View 对象添加到ViewPage集合中
                mViewPageList.add(gridView);
            }
        }
        homeNgBarViewPagerAdapter = new VideoNgBarViewPagerAdapter(mViewPageList);
        ngbarViewpager.setAdapter(homeNgBarViewPagerAdapter);
        homeNgBarViewPagerAdapter.notifyDataSetChanged();

        /**
         *  处理小圆点 指示器
         */
//        创建小圆点
        mIvpoints = null;
        mIvpoints = new ImageView[2];
        for (int i = 0; i < mIvpoints.length; i++) {
            if (i <= 1) {
                ImageView imgView = new ImageView(getActivity());
//            设置小圆点宽和高
                imgView.setLayoutParams(new ViewGroup.LayoutParams(5, 5));
//            默认设置
                if (i == 0) {
                    imgView.setBackgroundResource(R.drawable.page__selected_indicator);
                } else {
                    imgView.setBackgroundResource(R.drawable.page__normal_indicator);
                }
                mIvpoints[i] = imgView;
//            设置边距
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 10;
                mPoints.addView(imgView, layoutParams);
            }
        }
        if (mTotalPage == 1) {
            mPoints.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerOtherVideoComponent
                .builder()
                .appComponent(appComponent)
                .otherVideoModule(new OtherVideoModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_otherlist, null);

        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        refresh();
        setXrefeshViewConfig();
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle arguments = getArguments();
                        mHomeCate = (VideoCateList) arguments.getSerializable("homecatelist");
                        String show_order = arguments.getString("type");
                        if (show_order.equals(mHomeCate.getShow_order())) {
                            mPresenter.getHomeCateRefresh(mHomeCate.getIdentification());
                        }
                    }
                }, 500);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mIvpoints.length; i++) {
            if (i == position) {
                mIvpoints[i].setBackgroundResource(R.drawable.page__selected_indicator);
            } else {
                mIvpoints[i].setBackgroundResource(R.drawable.page__normal_indicator);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
