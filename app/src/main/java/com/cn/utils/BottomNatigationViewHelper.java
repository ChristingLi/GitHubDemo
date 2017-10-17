package com.cn.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by ld on 2017/10/16.
 */

public class BottomNatigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView navigationView){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);

        try {
            Field field = menuView.getClass().getDeclaredField("mShiftingMode");
            field.setAccessible(true);
            field.setBoolean(menuView,false);
            field.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
