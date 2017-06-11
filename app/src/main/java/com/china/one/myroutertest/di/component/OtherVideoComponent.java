package com.china.one.myroutertest.di.component;

import com.china.one.common.di.component.AppComponent;
import com.china.one.common.di.scope.FragmentScope;
import com.china.one.myroutertest.di.module.OtherVideoModule;
import com.china.one.myroutertest.mvp.ui.video.fragment.OtherVideoFragment;

import dagger.Component;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-09
 */

@FragmentScope
@Component(modules = OtherVideoModule.class, dependencies = AppComponent.class)
public interface OtherVideoComponent {
    void inject(OtherVideoFragment fragment);
}