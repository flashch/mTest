package com.china.one.myroutertest.di.module;

import com.china.one.common.di.scope.FragmentScope;
import com.china.one.myroutertest.mvp.contract.OtherVideoContract;
import com.china.one.myroutertest.mvp.model.OtherVideoModel;

import dagger.Module;
import dagger.Provides;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-09
 */

@Module
public class OtherVideoModule {
    private OtherVideoContract.View view;

    /**
     * 构建OtherVideoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public OtherVideoModule(OtherVideoContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    OtherVideoContract.View provideOtherVideoView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    OtherVideoContract.Model provideOtherVideoModel(OtherVideoModel model) {
        return model;
    }
}