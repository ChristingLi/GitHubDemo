package com.cntestviewpager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;

import com.orhanobut.logger.Logger;


/**
 * Created by ld on 2017/11/3.
 *
 * @author ld
 * @date 2017/11/3
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean mHasLoadedOnce;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;
    //使用属性保存当前状态FragmentStatePagerAdapter 跨好几个页面切换时getUserVisibleHint()在  onViewCreated中获取到的值不准确
    private boolean isVisibleToUser = false;

    protected boolean mHasLoadData = false;//在页面被销毁后是否保持了之前加载的数据，此字段决定是否重新加载数据这样就可以达到FragmentPagerAdapter的效果

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;
    protected int mScreenWidth;
    protected int mScreenHeight;
    public Context context;
    public Activity activity;

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d(TAG + "--------------");
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        Logger.d(TAG + "--------------onAttach");
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
        if (this.activity == null) {
            this.activity = activity;
        }
    }

    protected void onAttachToContext(Context context) {

        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        if (this.activity == null) {
            this.activity = getActivity();
        }
        initPresenter();
        Logger.d(TAG + "--------------onCreate");
    }


    protected void initEvents() {

    }

    protected void initViews() {

    }

    protected void initPresenter() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG + "--------------onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && isVisibleToUser) {//网上这里是getUserVisibleHint()这里跨多个fragment切换时取到的值不对所以直接使用刚才在setUserVisibleHint赋值的属性
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        Logger.d(TAG + "--------------onFragmentVisibleChange");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Logger.d(TAG + "--------------setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;//跨好几个fragment切换此次的isVisibleToUser正常的所以在这里赋值
        if (rootView == null) {
            return;
        }

        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }
}
