package com.cn.viewdarg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cn.utils.LogUtil;
import com.cn.github.R;

/**
 * Created by ld on 2017/10/10.
 */

public class DragViewHelperActivity extends AppCompatActivity{
    private static final String TAG = "DragViewHelperActivity";
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view_layout);
        DragViewLayout dragViewLayout = findViewById(R.id.drag_view_layout);
        dragViewLayout.isDrawOpened();
        
        LogUtil.d(TAG,dragViewLayout.isDrawOpened()+"");
    }
}
