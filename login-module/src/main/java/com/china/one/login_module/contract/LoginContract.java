package com.china.one.login_module.contract;

import com.china.one.common.mvp.IModel;
import com.china.one.common.mvp.IView;
import com.china.one.login_module.entity.VerificationResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-06-12
 */

public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<VerificationResponse> getVerification(Map maps);
    }
}