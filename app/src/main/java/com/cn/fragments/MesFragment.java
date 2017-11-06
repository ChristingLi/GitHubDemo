package com.cn.fragments;

import android.view.View;
import android.widget.Button;

import com.cn.bean.MessEvent;
import com.cn.callback.ErrorCallback;
import com.cn.callback.LoadingCallback;
import com.cn.github.R;
import com.cn.utils.PostUtil;
import com.cn.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ld on 2017/10/17.
 * @author ld
 */

public class MesFragment extends BaseFragment{
    private static final String TAG = "MesFragment";
    @BindView(R.id.btn_mes_test)
    Button mBtTest;

    @Override
    public void initView() {

    }
    @OnClick(R.id.btn_mes_test)
    public void testClick(View view){
        EventBus.getDefault().post(new MessEvent("ll"));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mes;
    }

    @Override
    protected void initData() {
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
        Logger.d(TAG+"is initData ing");
    }

    @Override
    protected void onNetReload(View v) {
        ToastUtil.showShortToast("reload in MesFragment");
        mBaseLoadService.showCallback(LoadingCallback.class);
        PostUtil.postSuccessDelayed(mBaseLoadService);
        Logger.d(TAG+"is onNetReload ing");
    }
}
