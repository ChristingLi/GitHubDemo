package com.cn.ViewDarg;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cn.Utils.LogUtil;


/**
 * Created by ld on 2017/10/10.
 */

public class HorizontalDragView extends ViewGroup{
    private static final String TAG = "horizontaldragview";
    private static final int MIN_DRAWER_MARGIN = 64;//dp
    /**
     * Minimum velocity that will be detected as filing
     */
    private static final int MIN_FLING_VELOCITY = 400;// dips per second
    /*drawer离父容器的最小外边距*/
    private int mMinDrawerMargin;

    private View mLeftMenuView;
    private View mContentView;
    /*drawer 显示出来占的百分比*/
    private float mLeftMenuOnScreen;
    private ViewDragHelper mViewDragHelper;

    public HorizontalDragView(Context context, AttributeSet attrs){
        super(context,attrs);
        /*setup drawer's minMargin*/
        final float density = getResources().getDisplayMetrics().density;
        final float minVel = MIN_FLING_VELOCITY * density;
        /*距离父容器右边边距*/
        mMinDrawerMargin = (int) (MIN_DRAWER_MARGIN * density + 0.5f);
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            /*移动时控制范围*/
            /**
             * 获得水平方向的滑动距离, 默认为0, 不滑动
             * left: 水平方向滑动距离
             * dx: 滑动增量, 距上次滑动距离
             * @param child
             * @param left
             * @param dx
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int newLeft = Math.max(-child.getWidth(),Math.min(left,0));
                return newLeft;
            }
            /**
             * 获得垂直方向的滑动距离, 默认为0, 不滑动
             * top: 垂直方向滑动距离
             * dy: 滑动增量, 距上次滑动距离
             * @param child
             * @param top
             * @param dy
             * @return
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }
            /*Drawer拉出时 我们通过拖拽drawer也能进行滑动菜单*/
            /**
             *何时开始检测触摸事件, 返回true, 一直检测触摸事件, 返回false, 不检测.
             * @param child
             * @param pointerId
             * @return
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                LogUtil.d(TAG,"tryCaptureView");
//                return child == mLeftMenuView;
                return true;
            }
            /*设置边界检测 因为View不可见，无法通过触摸到它来把menuView设置称captureView*/
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                LogUtil.d(TAG,"onEdgeDragStarted");
                mViewDragHelper.captureChildView(mLeftMenuView,pointerId);
            }
            /*释放时触发
            当xvel > 0 || xvel == 0 && offset > 0.5f显示我们的菜单，其他情况隐藏。
            这里注意一点xvel的值只有大于我们设置的minVelocity才会出现大于0，如果小于我们设置的值则一直是0。*/
            /**
             * 滑动结束后,回调此方法
             * @param releasedChild
             * @param xvel
             * @param yvel
             */
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                LogUtil.d(TAG,"onViewReleased");
                final int childWidth = releasedChild.getWidth();
                float offset = (childWidth + releasedChild.getLeft()) * 1.0f / childWidth;
                mViewDragHelper.settleCapturedViewAt(xvel > 0 || xvel == 0 && offset > 0.5f ? 0 : -childWidth,releasedChild.getTop());
                invalidate();
            }
            /*整个pos变化的过程中，我们计算offset保存，这里可以使用接口将offset回调出去，方便做动画。*/
            /**
             * 当view的位置发生变化时回调
             * changedView: 位置改变的控件
             * left: 水平方向移动的距离
             * top: 垂直方向移动距离
             * dx: 水平方向增量
             * dy: 垂直方向增量
             * @param changedView
             * @param left
             * @param top
             * @param dx
             * @param dy
             */
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                LogUtil.d(TAG,"onViewPositionChanged"+left);
                final int childWidth = changedView.getWidth();
                float offset = (float)(childWidth + left) / childWidth;
                mLeftMenuOnScreen = offset;
                /*offset can callback here*/
                changedView.setVisibility(offset == 0 ? View.INVISIBLE : View .VISIBLE);
                invalidate();
            }
            /*返回captureView的移动范围*/
            /**
             * 设置水平方向可以滑动的范围, 对应设置垂直方向滑动范围接口getViewVerticalDragRange
             * @param child
             * @return
             */
            @Override
            public int getViewHorizontalDragRange(View child) {
                return mLeftMenuView == child ? child.getWidth() : 0;
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }
        });
        /*设置edge_left track */
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        /*设置minVelocity*/
        mViewDragHelper.setMinVelocity(minVel);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize,heightSize);
        View leftMenuView = getChildAt(1);
        MarginLayoutParams lp = (MarginLayoutParams) leftMenuView.getLayoutParams();
        final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,mMinDrawerMargin + lp.leftMargin + lp.rightMargin,lp.width);
        final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,lp.topMargin + lp.bottomMargin, lp.height);
        leftMenuView.measure(drawerWidthSpec,drawerHeightSpec);
        View contentView = getChildAt(0);
        lp = (MarginLayoutParams) contentView.getLayoutParams();
        final int contentWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - lp.leftMargin - lp.rightMargin,MeasureSpec.EXACTLY);
        final int contentHeightSpec = MeasureSpec.makeMeasureSpec(heightSize - lp.topMargin - lp.bottomMargin,MeasureSpec.EXACTLY);
        contentView.measure(contentWidthSpec,contentHeightSpec);
        mLeftMenuView = leftMenuView;
        mContentView = contentView;
    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        View menuView = mLeftMenuView;
        View contentView = mContentView;
        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
        contentView.layout(lp.leftMargin,
                lp.topMargin,
                lp.leftMargin + contentView.getMeasuredWidth(),
                lp.rightMargin + contentView.getMeasuredHeight());
        lp = (MarginLayoutParams) menuView.getLayoutParams();

        final int menuWidth = menuView.getMeasuredWidth();

        int childLeft = (int) (- menuWidth + (menuWidth * mLeftMenuOnScreen));
        menuView.layout(childLeft,
                lp.topMargin,
                childLeft + menuWidth,
                lp.topMargin + menuView.getMeasuredHeight());
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean shouldInterceptTouchEvent = mViewDragHelper.shouldInterceptTouchEvent(ev);
        return shouldInterceptTouchEvent;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }
    public void closeDrawer(){
        View menuView = mLeftMenuView;
        mLeftMenuOnScreen = 0.f;
        mViewDragHelper.smoothSlideViewTo(menuView,-menuView.getWidth(),menuView.getTop());
    }
    public void openDrawer(){
        View menuView = mLeftMenuView;
        mLeftMenuOnScreen = 1.0f;
        mViewDragHelper.smoothSlideViewTo(menuView,0,menuView.getTop());
    }
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
