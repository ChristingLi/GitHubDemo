package com.cn.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.adapter.WaterFallAdapter;
import com.cn.application.MyApplication;
import com.cn.base.LoginLogic;
import com.cn.bean.ImageBean;
import com.cn.github.MainActivity;
import com.cn.github.R;
import com.cn.utils.PostUtil;
import com.cn.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by ld on 2017/10/17.
 *
 * @author ld
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private WaterFallAdapter mAdapter;
    private List<ImageBean> beforeBean = new ArrayList<>();
    private List<ImageBean> newBena;

    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.RecycledViewPool mPool;

    @Override
    public void initView() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                try {
                    LoginLogic.Instance(mContext).getJson(new GetJsonStringCallback());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new WaterFallAdapter(mContext);
        mPool = mRecyclerView.getRecycledViewPool();
        try {
            LoginLogic.Instance(mContext).getJson(new GetJsonStringCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PostUtil.postSuccessDelayed(mBaseLoadService);
        mRecyclerView.setAdapter(mAdapter);
//        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
        Logger.d(TAG + "is initData ing");
    }

    @Override
    protected void onNetReload(View v) {
//        ToastUtil.showShortToast("reload in HomeFragment");
//        mBaseLoadService.showCallback(LoadingCallback.class);
//        PostUtil.postSuccessDelayed(mBaseLoadService);
        Logger.d(TAG + "is onNetReload ing");
    }
    /**
     * get接口的自定义回调函数*/
    public class GetJsonStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {//showProgressDialog("");//显示进度加载框
        }

        @Override
        public void onAfter(int id) {//dismissProgressDialog();//隐藏进度加载框
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_again));
            Log.w(TAG,"{onError}e="+e.toString());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：response="+response);
            switch (id) {
                case 100://http
                    try {
                        if (response != null && !"".equals(response)){
                            //解析
                            JSONObject jsonObject = JSONObject.parseObject(response);
                            JSONArray array = jsonObject.getJSONArray("美女");
                            List<ImageBean> data = new ArrayList<>();
                            data = JSON.parseArray(array.toJSONString(), ImageBean.class);
                            if(beforeBean == null){
                                mAdapter.getList().addAll(data);
                            }else{
                                for(int i=0;i<data.size();i++){
                                    String boardid = data.get(i).getDocid();
                                    for(int j=0;j < beforeBean.size();j++){
                                        if(beforeBean.get(j).getBoardid().equals(boardid)){
                                            data.remove(i);
                                        }
                                    }
                                }
                                mAdapter.getList().addAll(0,data);

                            }
                            beforeBean.addAll(data);
                            Logger.d(beforeBean.size());
                        } else {
                            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_null_exception));
                        }
                    }catch (Exception e) {
                        Logger.d(e.toString());
                        ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
                    } finally {
                    }
                    break;
                case 101://https
                    break;
                default:
            }
        }
        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }
}


