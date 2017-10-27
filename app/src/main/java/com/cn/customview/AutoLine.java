package com.cn.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ld on 2017/10/27.
 *
 * @author ld
 * @date 2017/10/27
 */

public class AutoLine extends ViewGroup{
    private int mViewHeignt = -1;
    private int mViewWidth = -1;

    public AutoLine(Context context) {
        super(context);
    }

    public AutoLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;
        for(int index = 0;index < childCount; index ++){
            final View child = getChildAt(index);
            if(child.getVisibility() != View.GONE){
                child.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
                //换行判断 用于计算所需高度
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                x += width;
                y += height;
                if(x > maxWidth){
                    x = width;
                    row ++;
                    y = row * height + height;
                }
            }
        }
        //容器需要的高度
        setMeasuredDimension(maxWidth,y);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        int maxWidth = r - 1;
        int x = 0;
        int y = 0;
        int row = 0;
        for(int i=0;i<childCount;i++){
            final View child = this.getChildAt(i);
            if(child.getVisibility() != View.GONE){
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                x += width;
                y = row * height + height;
                if(x > maxWidth){
                    x = width;
                    row ++;
                    y = row * height + height;
                }
                child.layout(x - width,y -height,x,y);
                child.measure(width,height);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
