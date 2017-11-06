package com.cntestviewpager;

import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ld on 2017/11/3.
 *
 * @author ld
 * @date 2017/11/3
 */

public class FragmentOne extends BaseFragment{
   private static final String TAG = "FragmentOne";

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG+"-onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG+"-onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG+"-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG+"-onStop");
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d(TAG+"-onViewCreated");
    }
    private View view;
    private ArrayList<Parcelable> travelInfoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG+"-onCreateView");
        if (savedInstanceState != null) {
            travelInfoList = savedInstanceState.getParcelableArrayList("savedInstanceState");
            if (travelInfoList != null && travelInfoList.size() > 0)
                //这里表示数据已经加载无需去请求
                mHasLoadData = true;
        }
        if (rootView == null) {
            view = inflater.inflate(R.layout.fragment_one,null,false);
//            binding = DataBindingUtil.inflate(inflater, R.layout.fm_hot_travel, container, false);
//            rootView = binding.getRoot();
            view.getRootView();
            initViews();
        }
        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        if (adapter.getTravelInfoList() != null) {
//            //保存当前fragment的数据
//            outState.putParcelableArrayList("savedInstanceState", (ArrayList<? extends Parcelable>) adapter.getTravelInfoList());
//        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        //不可见 或不是第一次加载数据就返回
        if (!isVisible || mHasLoadedOnce) {
            return;
        }
        //页面是否被销毁过 没有销毁过就去加载数据
        if (!mHasLoadData) {
//            binding.swipeLayout.setRefreshing(true);
//            binding.swipeLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    presenter.getHotTravelList(page, pageSize);
//                }
//            }, 800);
        }
        mHasLoadedOnce = true;
    }
}
