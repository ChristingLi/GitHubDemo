package com.cn.fragments;

import android.view.View;

import com.cn.callback.ErrorCallback;
import com.cn.callback.LoadingCallback;
import com.cn.github.R;
import com.cn.utils.PostUtil;
import com.cn.utils.ToastUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by ld on 2017/10/17.
 * @author ld
 */

public class HomeFragment extends BaseFragment{
    private static final String TAG = "HomeFragment";


    @Override
    public void initView() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
        Logger.d(TAG+"is initData ing");
    }

    @Override
    protected void onNetReload(View v) {
        ToastUtil.showShortToast("reload in HomeFragment");
        mBaseLoadService.showCallback(LoadingCallback.class);
        PostUtil.postSuccessDelayed(mBaseLoadService);
        Logger.d(TAG+"is onNetReload ing");
    }
}
