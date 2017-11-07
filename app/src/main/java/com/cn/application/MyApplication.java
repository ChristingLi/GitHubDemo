package com.cn.application;

import android.app.Application;
import android.content.Context;

import com.cn.callback.CoustomCallback;
import com.cn.callback.EmptyCallback;
import com.cn.callback.ErrorCallback;
import com.cn.callback.LoadingCallback;
import com.cn.callback.TimeoutCallback;
import com.cn.dbfile.DaoMaster;
import com.cn.dbfile.DaoSession;
import com.cn.dbfile.GreenDaoEntityDao;
import com.cn.github.R;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by ld on 2017/10/9.
 * 自定义Application
 * @author ld
 */

public class MyApplication extends Application{
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context).setTimeFormat(new  ma);//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
    }
    /**全局上下文*/
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        /*配置okhttp*/
        initOkhttp();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(3)
                .tag("tag")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        loadSir();
    }

    /**
     * 初始化sir配置
     */
    private void loadSir(){
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CoustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
    private void initGreenDao() {
    /*初始化数据库
    * */
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getAppContext(),"notes-db",null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        GreenDaoEntityDao greenDaoEntityDao = daoSession.getGreenDaoEntityDao();
    }

    /** 获取系统上下文 */
    public static Context getAppContext(){
        return mContext;
    }

    /**
     * 初始化okhttp
     */
    private void initOkhttp(){
        /*自带cookie持久化，解决程序崩溃返回*/
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        //ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        /*设置可以访问所有htps网站*/
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,null,null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6000L, TimeUnit.MILLISECONDS)
                .readTimeout(600L,TimeUnit.MILLISECONDS)
                /*配置log，通过拦截器实现，自行实现interceptor*/
                .addInterceptor(new LoggerInterceptor("TAG"))
                /*配置持久化cookie*/
                .cookieJar(cookieJar)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return false;
                    }
                })
                /*配置https*/
                .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
