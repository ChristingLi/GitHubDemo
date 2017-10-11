package com.cn.Model;

/**
 * Created by ld on 2017/10/10.
 */

public class MenuItem {
    public MenuItem(String text, boolean isSelected, int icon, int iconSelected) {
        this.text = text;
        this.isSelected = isSelected;
        this.icon = icon;
        this.iconSelected = iconSelected;
    }
    public boolean isSelected;
    public String text;
    public int icon;
    public int iconSelected;
}
