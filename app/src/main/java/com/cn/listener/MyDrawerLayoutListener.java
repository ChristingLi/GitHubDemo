package com.cn.listener;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.cn.github.R;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;

import java.time.chrono.MinguoChronology;


/**
 * Created by ld on 2017/10/16.
 */

public class MyDrawerLayoutListener implements DrawerLayout.DrawerListener{
    private ImmersionBar mImmersionBar;
    private int mStatusSign;
    private int mSlideSigm;
    public MyDrawerLayoutListener(ImmersionBar immersionBar){
        this.mImmersionBar = immersionBar;
    }
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Logger.d("onDrawerSlide");
        if(mSlideSigm == 0){
            if(mStatusSign == 1){
                mImmersionBar.reset().statusBarColor(R.color.orange).init();
            }
            if(mStatusSign == 0){
                mImmersionBar.reset().statusBarColor(R.color.colorAccent).init();
            }
        }
     mSlideSigm += 1;
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Logger.d("onDrawerOpened");
        mStatusSign = 1;
        mSlideSigm = 0;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Logger.d("onDrawerClosed");
        mStatusSign = 0;
        mSlideSigm = 0;
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        Logger.d("onDrawerStateChanged");
        mSlideSigm = 0;
    }
}
