package com.china.one.myroutertest.mvp.ui.video.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.china.one.common.base.BaseFragment;
import com.china.one.common.di.component.AppComponent;
import com.china.one.myroutertest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoPhoneGameFragment extends BaseFragment {

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }
    public static VideoPhoneGameFragment newInstance() {
        VideoPhoneGameFragment fragment = new VideoPhoneGameFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_video_phone_game,null);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setData(Object data) {

    }
}
