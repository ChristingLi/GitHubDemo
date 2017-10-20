package com.cn.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MesFragment extends BaseFragment{
    private static final String TAG = "MesFragment";

    @Override
    public void initView() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mes;
    }

    @Override
    protected void initData() {
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
        Logger.e("TAG", "Mes加载数据了");
    }

    @Override
    protected void onNetReload(View v) {
        ToastUtil.showShortToast("reload in MesFragment");
        mBaseLoadService.showCallback(LoadingCallback.class);
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d(TAG+"");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.d(TAG+"");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG+"");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
