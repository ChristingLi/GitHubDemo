package com.cn.HorizontalScroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cn.github.R;

/**
 * Created by ld on 2017/10/10.
 */

public class SlidingActivity extends AppCompatActivity{
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_main);
        SlidingMenu slidingMenu = (SlidingMenu)findViewById(R.id.slide_menu);
        slidingMenu.toggleMenu();
    }
}
