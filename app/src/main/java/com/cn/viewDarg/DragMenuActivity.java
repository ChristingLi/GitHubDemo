package com.cn.viewDarg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cn.utils.Engine;
import com.cn.utils.ToastUtil;
import com.cn.github.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by ld on 2017/10/13.
 */

public class DragMenuActivity extends AppCompatActivity{
    @BindViews({R.id.lv_left,R.id.lv_main})
    List<ListView> views;
    @BindView(R.id.iv_header)
    ImageView imageView;
    @BindView(R.id.d1)
    DragMenuLayout dragMenuLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_darg_menu);
        ButterKnife.bind(this);
        initSet();
    }

    private void initSet() {
        dragMenuLayout.setDragStatusListener(new DragMenuLayout.OnDragStatusChangeListener() {
            @Override
            public void onClose() {
                Logger.d("DragView is close");
            }

            @Override
            public void onOpen() {
                Logger.d("DragView is open");
            }

            @Override
            public void onDragging(float percent) {
                Logger.d("DragView is dragging");
                imageView.setAlpha(1 - percent);
            }
        });
        views.get(0).setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Engine.leftStrings){
            @Override
            public View getView(int position,View convertView,ViewGroup parent) {
                View view = super.getView(position,convertView,parent);
                TextView tv = (TextView) view;
                tv.setTextColor(Color.WHITE);
                return view;
            }
        });
        views.get(1).setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Engine.rightStrings));
    }
    @OnItemClick(R.id.lv_left)
    public void leftClick(int position){
        ToastUtil.showShortToastCenter(Engine.leftStrings[position]);
    }
    @OnItemClick(R.id.lv_main)
    public void menuClick(int position){
        ToastUtil.showShortToastCenter(Engine.rightStrings[position]);
    }
}
