package com.cn.alphatab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.cn.bean.MessEvent;
import com.cn.fragments.HomeFragment;
import com.cn.fragments.MapFragment;
import com.cn.fragments.MesFragment;
import com.cn.fragments.PerFragment;
import com.cn.github.R;
import com.cn.project.HomeActivity;
import com.orhanobut.logger.Logger;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2017/10/17.
 * @author ld
 */

public class AlphaActivity extends AppCompatActivity{
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaTabsIndicator;

    @Override
    public void onCreate( Bundle savedInstanceState  ) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_alpha_tab);
        ButterKnife.bind(this);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragments(new HomeFragment());
        mainAdapter.setFragments(new MapFragment());
        mainAdapter.setFragments(new MesFragment());
        mainAdapter.setFragments(new PerFragment());

        mViewPager.setAdapter(mainAdapter);
        mAlphaTabsIndicator.setViewPager(mViewPager);
        mAlphaTabsIndicator.getTabView(0).showNumber(6);
        mAlphaTabsIndicator.getTabView(1).showNumber(888);
        mAlphaTabsIndicator.getTabView(2).showNumber(88);
        mAlphaTabsIndicator.getTabView(3).showPoint();

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessEvent event){
        Logger.d(event.message+"[Receive Mes]");

    }
    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{
        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {"index","map","mes","person"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
//            fragments.add(TextFragment.newInstance(titles[0]));
//            fragments.add(TextFragment.newInstance(titles[1]));
//            fragments.add(TextFragment.newInstance(titles[2]));
//            fragments.add(TextFragment.newInstance(titles[3]));
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
            if(0 == position){
                mAlphaTabsIndicator.getTabView(0).showNumber(mAlphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
            }else if(2 == position){
                mAlphaTabsIndicator.getCurrentItemView().removeShow();
            }else if(3 == position){
                mAlphaTabsIndicator.removeAllBadge();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
