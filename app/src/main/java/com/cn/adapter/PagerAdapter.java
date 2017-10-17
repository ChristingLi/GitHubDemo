package com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;


/**
 * Created by ld on 2017/10/17.
 * @author ld
 */

public class PagerAdapter extends FragmentPagerAdapter{
    private static final String TAG = "pageadapter";

    SparseArray<Fragment> fragmentSparseArray;


    public PagerAdapter(FragmentManager fm,SparseArray<Fragment> fragment) {
        super(fm);
        this.fragmentSparseArray = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        Logger.d(TAG+"getItem"+position);
        Fragment fragment = fragmentSparseArray.valueAt(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentSparseArray.size();
    }
    /**
     * 返回每一个item所有对应的key，在instantiateItem中 会根据这个item去查找是否已经存在这个item
     * 如果不存在则调用getItem创建
     * 存在则在FragmentManager中去find
     * 详情查看instantiateItem源码
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        Logger.d(TAG, "position: "+position+"  getItemId:"+fragmentSparseArray.keyAt(position));
        return fragmentSparseArray.keyAt(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Logger.d(TAG+"instantiateItem"+position);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;


    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}
