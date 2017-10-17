package com.cn.model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.github.R;
import com.cn.project.AttentionFragment;
import com.cn.project.DiscoveryFragment;
import com.cn.project.HomeFragment;
import com.cn.project.ProfileFragment;

/**
 * Created by ld on 2017/10/16.
 */

public class DataGenerator {
    public static final int []mTabRes = new int[]{R.mipmap.index,R.mipmap.map,R.mipmap.message,R.mipmap.personal};
    public static final int []mTabResPressed = new int[]{R.mipmap.index_select,R.mipmap.map_select,R.mipmap.message_select,R.mipmap.personal_select};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DiscoveryFragment.newInstance(from);
        fragments[2] = AttentionFragment.newInstance(from);
        fragments[3] = ProfileFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
