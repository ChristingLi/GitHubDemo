package com.cn.ViewDarg;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cn.github.R;

/**
 * Created by ld on 2017/10/11.
 */

@SuppressLint("ValidFragment")
public class DragMenuLayout extends FrameLayout{
    private Context mContext;
    private int mScreenWidth;
    private int mScreenHeight;
    /*范围*/
    private int mDragRange;
    private ViewDragHelper mViewDragHelper;
    private int mStyle;
    
    private ViewGroup mLeftContent;
    private ViewGroup mRightContent;
    private Status mStatus = Status.Close;
    
    private OnDragStatusChangeListener mListener;

    public enum Status{
        Close,Open,Dragging;
    }
    
    public DragMenuLayout(Context context){
        this(context,null);
    }
    public DragMenuLayout(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public DragMenuLayout(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        mContext = context;
        init();
        initAttributeSet(attrs);
    }

    private void initAttributeSet(AttributeSet attrs) {
        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.DragMenuLayout);
        mDragRange = (int) arr.getFloat(R.styleable.DragMenuLayout_dragRange,mDragRange);
        mStyle = arr.getInt(R.styleable.DragMenuLayout_style,0);
        /*回收*/
        arr.recycle();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this,new DragCallBack());
    }
    public Status getStatus(){
        return mStatus;
    }
    public void setStatus(Status status){
        this.mStatus = status;
    }
    public void setDragStatusListener(OnDragStatusChangeListener mListener){
        this.mListener = mListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount() < 2){
            throw new IllegalArgumentException("Your ViewGroup must have two child at least");
        }
        if(!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)){
            throw new IllegalArgumentException("Your Child must be an instance if ViewGroup");
        }
        mLeftContent = (ViewGroup) getChildAt(0);
        mRightContent = (ViewGroup) getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenWidth = getMeasuredWidth();
        mScreenHeight = getMeasuredHeight();
        mDragRange = (int) (mScreenWidth * 0.6f);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mViewDragHelper != null){
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mViewDragHelper.processTouchEvent(event);
        }catch (Exception e){

        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
    /**
     * Determine drag range
     * @param left drag value on screen
     * @return drag range
     */
    private int fitLeft(int left){
        if(left < 0){
            return 0;
        }else if(left > mDragRange){
            return mDragRange;
        }
        return left;
    }
    public void close(boolean isSmooth){
        int finalLeft = 0;
        if(mViewDragHelper.smoothSlideViewTo(mRightContent,finalLeft,0)){
            // return true show not been moved to the specified location
            // and need to refresh the page.
            ViewCompat.postInvalidateOnAnimation(this);
        }else {
            mRightContent.layout(finalLeft, 0, finalLeft + mScreenWidth, mScreenHeight);
        }
    }
    public void open(){
        open(true);
    }
    public void open(boolean isSmooth){
        int finalLeft = mDragRange;
        if(isSmooth){
            if(mViewDragHelper.smoothSlideViewTo(mRightContent,finalLeft,0)){
                ViewCompat.postInvalidateOnAnimation(this);
            }else {
                mRightContent.layout(finalLeft,0,finalLeft+mScreenWidth,mScreenHeight);
            }
        }
    }
    private void dispatchDragEvent(int newLeft){
        float percent = newLeft * 1.0f / mDragRange;
        if(mListener != null){
            mListener.onDragging(percent);
        }
        Status preStatus = updateStatus(percent);
        if(mStatus != preStatus){
            if(mStatus == Status.Close){
                if(mListener != null){
                    mListener.onClose();
                }
            }else if(mStatus == Status.Open){
                if(mListener != null){
                    mListener.onOpen();
                }
            }
        }
        //start animation
        if(mStyle == 0){
            animTranslateViews(percent);
        }else {
            animScaleViews(percent);
        }
    }
    private void animScaleViews(float percent){
        mLeftContent.setScaleX(evaluate(percent, 0.5f, 1.0f));
        mLeftContent.setScaleY(evaluate(percent, 0.5f, 1.0f));
        mLeftContent.setTranslationX(evaluate(percent, -mScreenWidth / 2, 0));
        mLeftContent.setAlpha(evaluate(percent, 0.5f, 1.0f));

        mRightContent.setScaleX(evaluate(percent, 1.0f, 0.85f));
        mRightContent.setScaleY(evaluate(percent, 1.0f, 0.85f));
    }
    private void animTranslateViews(float percent) {
        mLeftContent.setTranslationX(evaluate(percent, -mDragRange / 2, 0));
    }
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }
    private Status updateStatus(float percent){
        if(percent == 0f){
            return Status.Close;
        }else if (percent == 1.0f) {
            return Status.Open;
        }
        return Status.Dragging;
    }
    public interface OnDragStatusChangeListener {
        void onClose();
        void onOpen();
        void onDragging(float percent);
    }
    private class DragCallBack extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mDragRange;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(child == mRightContent){
                left = fitLeft(left);
            }
            return left;
        }
    }
}
