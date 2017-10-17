package com.cn.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cn.adapter.ViewPagerAdapter;
import com.cn.github.R;
import com.cn.utils.BottomNatigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2017/10/16.
 * @author ld
 */
public class HomeActivity extends AppCompatActivity{
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_navigationview)
    BottomNavigationView mBottomNavigationView;
    private MenuItem mMenuItem;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initSet();
    }

    private void initSet() {
        BottomNatigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home_first_page :
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_home_found:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_home_message:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_home_mine:
                        mViewPager.setCurrentItem(3);
                        break;
                        default:
                }
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(mMenuItem != null){
                    mMenuItem.setChecked(false);
                }else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(mViewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("1"));
        adapter.addFragment(BaseFragment.newInstance("2"));
        adapter.addFragment(BaseFragment.newInstance("3"));
        adapter.addFragment(BaseFragment.newInstance("4"));
        mViewPager.setAdapter(adapter);

    }
}
