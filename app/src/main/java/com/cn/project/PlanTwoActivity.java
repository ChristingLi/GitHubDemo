package com.cn.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cn.github.R;
import com.cn.model.DataGenerator;
import com.cn.viewmodel.CustomTabView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2017/10/16.
 * @author ld
 */
public class PlanTwoActivity extends AppCompatActivity implements CustomTabView.OnTabCheckListener{
    @BindView(R.id.custom_tab_container)
    CustomTabView mCustomTabView;
    private Fragment[] fragments;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);
        ButterKnife.bind(this);
        fragments = DataGenerator.getFragments("CustomTabView");
        initViews();
    }

    private void initViews() {
        CustomTabView.Tab tabHome = new CustomTabView.Tab()
                .setText("首页")
                .setCheckedColor(R.color.gray)
                .setNormalIcon(R.mipmap.index)
                .setPressedIcon(R.mipmap.index_select);
        mCustomTabView.addTab(tabHome);
        CustomTabView.Tab tabMap = new CustomTabView.Tab()
                .setText("发现")
                .setCheckedColor(R.color.gray)
                .setNormalIcon(R.mipmap.map)
                .setPressedIcon(R.mipmap.map_select);
        mCustomTabView.addTab(tabMap);
        CustomTabView.Tab tabMes = new CustomTabView.Tab()
                .setText("消息")
                .setCheckedColor(R.color.gray)
                .setNormalIcon(R.mipmap.message)
                .setPressedIcon(R.mipmap.message_select);
        mCustomTabView.addTab(tabMes);
        CustomTabView.Tab tabPre = new CustomTabView.Tab()
                .setText("我的")
                .setCheckedColor(R.color.gray)
                .setNormalIcon(R.mipmap.personal)
                .setPressedIcon(R.mipmap.personal_select);
        mCustomTabView.addTab(tabPre);
        mCustomTabView.setOnTabCheckListener(this);
        mCustomTabView.setCurrentItem(0);
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = fragments[0];
                break;
            case 1:
                fragment = fragments[1];
                break;

            case 2:
                fragment = fragments[2];
                break;
            case 3:
                fragment = fragments[3];
                break;
                default:
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }

    @Override
    public void onTabSelected(View v, int position) {
        onTabItemSelected(position);
    }
}
