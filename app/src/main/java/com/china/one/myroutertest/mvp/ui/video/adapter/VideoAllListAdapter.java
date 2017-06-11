package com.china.one.myroutertest.mvp.ui.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.china.one.myroutertest.mvp.model.entity.VideoCateList;
import com.china.one.myroutertest.mvp.ui.video.fragment.OtherVideoFragment;
import com.china.one.myroutertest.mvp.ui.video.fragment.VideoRecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/19 下午1:53
 **/
public class VideoAllListAdapter extends FragmentStatePagerAdapter {

    private List<VideoCateList> mHomeCateLists;
    private String[] mTiltle;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> fragments;

    public VideoAllListAdapter(FragmentManager fm, List<VideoCateList> homeCateLists, String[] title, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragmentManager = fm;
        this.mHomeCateLists = homeCateLists;
        this.mTiltle = title;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return mTiltle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTiltle[position];
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return VideoRecommendFragment.newInstance();
        }else {
            return OtherVideoFragment.getInstance(mHomeCateLists.get(position-1),position);
        }
    }
}
