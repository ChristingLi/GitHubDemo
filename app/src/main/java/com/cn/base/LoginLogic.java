package com.cn.base;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.cn.github.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.RequestBody;

/**
 * Created by ld on 2017/10/9.
 * 登陆相关接口
 */

public class LoginLogic extends  BaseLogic{
    private static LoginLogic Instance = null;
    public static LoginLogic Instance(Context context){
        if(Instance == null){
            Instance = new LoginLogic(context);
        }
        return Instance;
    }
    private LoginLogic(Context context){
        this.context = context;
    }
//    String url = "http://c.3g.163.com/recommend/getChanListNews?" +
//            "channel=T1456112189138&size=20&passport=&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D" +
//            "&lat=%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D" +
//            "&version=9.0&net=wifi&ts=1464769308" +
//            "&sign=bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I" +
//            "&encryption=1&canal=meizu_store2014_news" +
//            "&mac=sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D";
    String url = "https://gank.io/api/add2gank";
    /**
     * get请求
     * @param callback
     * @return
     * @throws Exception
     */
    public String getJson(StringCallback callback) throws Exception{
        String result = "";
//        OkHttpUtils.get()
////                .url(getSpcyUrl(context.getString(R.string.get_url)))
//                .url(url)
//                .id(100)
//                .tag(context)
//                .build()
//                .execute(callback);
//        result = callback.toString();
        return result;
    }
}
