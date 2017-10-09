package com.cn.base;

import android.content.Context;

import com.cn.github.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.CookieStore;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;

/**
 * Created by ld on 2017/10/9.
 */

public class BaseLogic {
    protected Context context;
    public final String charset = "GBK";

    /*拼接完成的URL地址*/
    protected String getSpcyUrl(String serverUrl){
        String url = context.getString(R.string.server_url) + serverUrl;
        return url;
    }
    /*获取cookies*/
    public static String getCookiesStr(){
        String cookiesInfo = "";
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if(cookieJar instanceof CookieJarImpl){
            CookieStore cookieStore = ((CookieJarImpl)cookieJar).getCookieStore();
            List<Cookie> cookies = cookieStore.getCookies();
            for(Cookie cookie : cookies){
                cookiesInfo = cookiesInfo + cookie.name() + ":" + cookie.value() + ";";
            }
        }
        return cookiesInfo;
    }

    /*清除cookie所有缓存*/
    public static void clearCookies(){
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if(cookieJar instanceof CookieJarImpl){
            ((CookieJarImpl)cookieJar).getCookieStore().removeAll();
        }
    }
}
