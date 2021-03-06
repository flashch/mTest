package com.china.one.common.rxerrorhandler.handler;

import android.content.Context;
import com.china.one.common.rxerrorhandler.handler.listener.ResponseErrorListener;

public class ErrorHandlerFactory {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
        this.mContext = mContext;
    }

    /**
     *  处理错误
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErrorListener.handleResponseError(mContext, throwable);
    }
}
