package com.china.one.myroutertest.mvp.ui.video.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.china.one.common.base.BaseFragment;
import com.china.one.common.di.component.AppComponent;
import com.china.one.common.utils.LogUtils;
import com.china.one.common.utils.UiUtils;
import com.china.one.myroutertest.R;
import com.china.one.myroutertest.di.component.DaggerVideoComponent;
import com.china.one.myroutertest.di.module.VideoModule;
import com.china.one.myroutertest.mvp.contract.VideoContract;
import com.china.one.myroutertest.mvp.model.entity.VideoCateList;
import com.china.one.myroutertest.mvp.presenter.VideoPresenter;
import com.china.one.myroutertest.mvp.ui.fragment.Test2Fragment;
import com.china.one.myroutertest.mvp.ui.fragment.Test3Fragment;
import com.china.one.myroutertest.mvp.ui.fragment.Test4Fragment;
import com.china.one.myroutertest.mvp.ui.video.adapter.VideoAllListAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.china.one.common.utils.Preconditions.checkNotNull;


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

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoContract.View {
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private VideoAllListAdapter mAdapter;
    private String[] mTitles;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerVideoComponent
                .builder()
                .appComponent(appComponent)
                .videoModule(new VideoModule(this))//请将VideoModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =View.inflate(getContext(),R.layout.fragment_video,null);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mFragments.add(new VideoPhoneGameFragment());
        mFragments.add(new Test2Fragment());
        mFragments.add(new Test3Fragment());
        mFragments.add(new Test4Fragment());
        mPresenter.getHomeCateList();
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
    public void getHomeAllList(List<VideoCateList> cateLists) {
        /**
         *  默认数据
         */
        mTitles = new String[cateLists.size()+1];
        mTitles[0] = "推荐";
//        mFragments.add(RecommendHomeFragment.getInstance());
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i+1] = cateLists.get(i).getTitle();
            LogUtils.debugInfo(mTitles[i+1].toString());
//            Bundle bundle=new Bundle();
//             bundle.putSerializable("homecatelist",cateLists.get(i));
//            mFragments.add(OtherHomeFragment.getInstance(bundle));
        }
//        不摧毁Fragment
        viewpager.setOffscreenPageLimit(mTitles.length);
        mAdapter = new VideoAllListAdapter(getChildFragmentManager(), cateLists, mTitles,mFragments);
        viewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        slidingTab.setViewPager(viewpager, mTitles);
    }
}