package com.cn.ViewDarg;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by ld on 2017/10/10.
 * ViewDragHelper 手势滑动辅助工具
 */
public class DragViewLayout extends ViewGroup{
    public ViewDragHelper mViewDragHelper;
    private boolean isOpen = true;
    private View mMenuView;
    private View mContentView;
    private int mCurrentTop = 0;
    public DragViewLayout(Context context){
        super(context);
        init();
    }
    public DragViewLayout(Context context, AttributeSet atts ){
        super(context,atts);
        init();
    }
    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mMenuView.layout(0,0,mMenuView.getMeasuredWidth(),
                mMenuView.getMeasuredHeight());
        mContentView.layout(0,mCurrentTop + mMenuView.getHeight(),
                mContentView.getMeasuredWidth(),
                mCurrentTop + mContentView.getMeasuredHeight() + mMenuView.getHeight());
    }
    private void init() {
        /*ViewDragHelper 静态方法传入 ViewDragHelperCallback 创建*/
        mViewDragHelper = ViewDragHelper.create(this,1.0f,new ViewDragHelperCallBack());
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
    }
    private class ViewDragHelperCallBack extends ViewDragHelper.Callback{
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            /*返回true表示可以捕捉到 改View*/
            return child == mContentView;
        }
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
            /*setEdgeTrackingEnable设置的边界滑动触发*/
            /*通过captureChildView对其进行捕捉，该方法可以绕过tryCaptureView*/
//            mViewDragHelper.captureChildView(mContentView,pointerId);
        }
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            /*手指触摸移动时回调，left表示 要到达的 x坐标*/
            return super.clampViewPositionHorizontal(child, left, dx);
        }
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            /*手指触摸移动时回调 top表示 要到达的 y坐标*/
//            return super.clampViewPositionVertical(child, top, dy);
            return Math.max(Math.min(top,mMenuView.getHeight()),0);
        }
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            /*手指抬起释放时回调*/
            int finalTop = mMenuView.getHeight();
            if(yvel <= 0){
                if(releasedChild.getTop() < mMenuView.getHeight()/2){
                    finalTop = 0;
                }else {
                    finalTop = mMenuView.getHeight();
                }
            }else {
                if(releasedChild.getTop() > mMenuView.getHeight()/2){
                    finalTop = mMenuView.getHeight();
                }else {
                    finalTop = 0;
                }
            }
            mViewDragHelper.settleCapturedViewAt(releasedChild.getLeft(),finalTop);
            invalidate();
        }
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            /*mDrawViewHelper 完全覆盖屏幕则防止过度绘制*/
            mMenuView.setVisibility((changedView.getHeight() - top == getHeight())? View.GONE : View.VISIBLE);
            mCurrentTop += dy;
            requestLayout();
        }
        @Override
        public int getViewVerticalDragRange(View child) {
            if(mMenuView == null) return 0;
            return (mContentView == child)? mMenuView.getHeight() : 0;
        }
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if(state == ViewDragHelper.STATE_IDLE){
                isOpen = (mContentView.getTop() == mMenuView.getHeight());
            }
        }
    }
    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }
    public boolean isDrawOpened(){
        return  isOpen;
    }
    /*onInterceptTouchEvent 方法调用mViewDragHelper.shouldInterceptTouchEven */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }
    /*onTouchEvent 方法中调用mViewDragHelper.processTouchEvent(event)并返回true*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth,measureHeight);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
    }
}
