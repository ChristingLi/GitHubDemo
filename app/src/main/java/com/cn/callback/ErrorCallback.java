package com.cn.callback;


import com.cn.github.R;
import com.kingja.loadsir.callback.Callback;



public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }
}
