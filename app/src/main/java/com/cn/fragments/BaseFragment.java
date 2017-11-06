package com.cn.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ld on 2017/10/17.
 *
 * @author ld
 */

public abstract class BaseFragment extends Fragment {
    public Unbinder unbinder;
    public Context mContext;
    protected View mRootView;
    protected LoadService mBaseLoadService;
    /**
     * 是否为可见状态
     */
    private boolean isVisible;

    /**
     * 是否视图加载完成(第一次加载)
     */
    private boolean isPrepared;

    /**
     * Fragment生命周期中，在执行完onAttach之后就可以获取到上下文了
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayoutId(), container, false);
        unbinder = ButterKnife.bind(this,mRootView);
        initView();
        mBaseLoadService = LoadSir.getDefault().register(mRootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {

                onNetReload(v);

            }
        });
        return mBaseLoadService.getLoadLayout();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //如果可见
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见做懒加载
     */
    private void onVisible() {
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        initData();
        isPrepared = false;
    }

    /**
     * 不可见时做一些销毁操作
     */
    protected void onInvisible() {
    }

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 绑定布局
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 重新加载
     *
     * @param v
     */
    protected abstract void onNetReload(View v);
}

