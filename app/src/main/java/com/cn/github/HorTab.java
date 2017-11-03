package com.cn.github;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cn.adapter.ViewPagerAdapter;
import com.cn.alphatab.AlphaActivity;
import com.cn.fragments.HomeFragment;
import com.cn.fragments.MapFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2017/10/30.
 *
 * @author ld
 * @date 2017/10/30
 */

public class HorTab extends AppCompatActivity{
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.alpha_tab)
    AlphaTabsIndicator mAlphaTabsIndicator;
    @BindView(R.id.btn_page_two)
    Button mPageOne;
    @BindView(R.id.btn_pager_one)
    Button mPageTwo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_tab);
        ButterKnife.bind(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragments(new HomeFragment());
        mainAdapter.setFragments(new MapFragment());

        mViewPager.setAdapter(mainAdapter);
        mAlphaTabsIndicator.setViewPager(mViewPager);
        mAlphaTabsIndicator.getTabView(0).showNumber(6);
        mAlphaTabsIndicator.getTabView(1).showNumber(888);
        mPageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
        mPageTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
    }

private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{
    private List<Fragment> fragments = new ArrayList<>();
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(Fragment fragment){
        this.fragments.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 用来监控页面是否滑动完毕，只有滑动完毕后才能加载内容，解决滑动时卡顿的问题
        if (position == 1 || position == 0) {
            //添加逻辑代码
        }
    }

    @Override
    public void onPageSelected(int position) {
//        if(0 == position){
//            mAlphaTabsIndicator.getTabView(0).showNumber(mAlphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
//        }else if(2 == position){
//            mAlphaTabsIndicator.getCurrentItemView().removeShow();
//        }else if(3 == position){
//            mAlphaTabsIndicator.removeAllBadge();
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
}
