package com.cn.utils;

import android.content.Context;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by ld on 2017/10/9.
 * 判断网络连接
 */

public class HttpUtils {
    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context){
        if(Configs.TEST_FLAG == 0){
            return true;
        }else {
            return NetWorkHelper.isNetWorkAvailable(context);
        }
    }

    /**
     * 判断是否有可用的网络连接
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context){
        if (Configs.TEST_FLAG == 0) {
            return true;
        } else {
            return NetWorkHelper.isNetWorkConnected(context);
        }
    }

    /**
     * 判断wifi网络是否可用
     * @param context
     * @return
     */
    public static boolean isMobileDataEnable(Context context){
        String TAG = "isMobileDataEnable";
        try {
            return NetWorkHelper.isMobileDataEnable(context);
        }catch (Exception e){
            Log.e(TAG,e.toString());
            return false;
        }
    }

    /**
     * 判断是否为漫游
     * @param context
     * @return
     */
    public static boolean isNetWorkRemoing(Context context){
        return NetWorkHelper.isNetWorkRomaing(context);
    }

    /**
     * 编码测试
     * @param datastr
     */
    public static void testCharset(String datastr) {
        try {
            String temp = new String(datastr.getBytes(), "GBK");
            Log.v("TestCharset", "****** getBytes() -> GBK ******/n" + temp);
            temp = new String(datastr.getBytes("GBK"), "UTF-8");
            Log.v("TestCharset", "****** GBK -> UTF-8 *******/n" + temp);
            temp = new String(datastr.getBytes("GBK"), "ISO-8859-1");
            Log.v("TestCharset", "****** GBK -> ISO-8859-1 *******/n" + temp);
            temp = new String(datastr.getBytes("ISO-8859-1"), "UTF-8");
            Log.v("TestCharset", "****** ISO-8859-1 -> UTF-8 *******/n" + temp);
            temp = new String(datastr.getBytes("ISO-8859-1"), "GBK");
            Log.v("TestCharset", "****** ISO-8859-1 -> GBK *******/n" + temp);
            temp = new String(datastr.getBytes("UTF-8"), "GBK");
            Log.v("TestCharset", "****** UTF-8 -> GBK *******/n" + temp);
            temp = new String(datastr.getBytes("UTF-8"), "ISO-8859-1");
            Log.v("TestCharset", "****** UTF-8 -> ISO-8859-1 *******/n" + temp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
