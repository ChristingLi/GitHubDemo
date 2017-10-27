package com.cn.viewdarg;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cn.github.R;


/**
 * Created by ld on 2017/10/10.
 */

public class HorizontalDragActivity extends AppCompatActivity{
    private HorizontalLeftFragment fragment;
    private HorizontalDragView dragView;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_drag);
        dragView = findViewById(R.id.horizontal_draw_layout);
        textView = findViewById(R.id.id_content_tv);
       FragmentManager fragmentManager = getSupportFragmentManager();
       fragment = (HorizontalLeftFragment) fragmentManager.findFragmentById(R.id.id_container_menu);
       if(fragment == null){
            fragmentManager.beginTransaction().add(R.id.id_container_menu,fragment = new HorizontalLeftFragment()).commit();
       }
       fragment.setOnMenuItemSelectedListener(new HorizontalLeftFragment.OnMenuItemSelectedListener() {
           @Override
           public void menuItemSeletced(String title) {
               dragView.closeDrawer();
               textView.setText(title);
           }
       });
    }
}
