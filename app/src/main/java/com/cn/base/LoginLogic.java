package com.cn.base;

import android.content.Context;

import com.cn.github.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by ld on 2017/10/9.
 * 登陆相关接口
 */

public class LoginLogic extends  BaseLogic{
    private static LoginLogic _Instance = null;
    public static LoginLogic Instance(Context context){
        if(_Instance == null){
            _Instance = new LoginLogic(context);
        }
        return _Instance;
    }
    private LoginLogic(Context context){
        this.context = context;
    }

    /**
     * get请求
     * @param callback
     * @return
     * @throws Exception
     */
    public String getJson(StringCallback callback) throws Exception{
        String result = "";
        OkHttpUtils.get()
                .url(getSpcyUrl(context.getString(R.string.get_url)))
                .id(100)
                .tag(context)
                .build()
                .execute(callback);
        result = callback.toString();
        return result;
    }
}
