package com.cn.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by ld on 2017/10/9.
 * 网络连接的判断：是否有网络、网络是否可用、判断是wifi还是3G、判断wifi是否可用、判断3G是否可用
 * 权限 android.permission.ACCESS_NETWORK_STATE
 */

public class NetWorkHelper {
    /**
     * 仅仅用来判断是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context){
        /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
            /*获取当前活动的NetWorkInfo对象*/
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断是否有可用的网络连接
     * 只打开3G连接的时候：
     * 0===类型===MOBILE
     * 0===状态===CONNECTED
     * 打开wifi连接和3G连接的时候：
     * 0===类型===MOBILE
     * 0===状态===DISCONNECTED
     * 1===类型===WIFI
     * 1===状态===CONNECTED
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context){
        boolean netState = false;
        /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo[] networkInfos = manager.getAllNetworkInfo();
            if(networkInfos != null){
                for(int i=0; i < networkInfos.length; i++){
                    if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
                        netState = true;
                        break;
                    }
                }
            }
        }
        return netState;
    }

    /**
     * 判断是否是wifi，
     * @param context
     * @return
     */
    public static boolean isWifi(Context context){
          /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }

    /**
     * 判断wifi是否可用
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) throws Exception{
        /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 判断是否是3g网络
     * @param context
     * @return
     */
    public static boolean is3rd(Context context){
        /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
            return true;
        }
        return false;
    }

    /**
     * 判断3g网络是否可用
     * @param context
     * @return
     */
    public static boolean isMobileDataEnable(Context context){
         /*获取手机所有连接的管理对象（包括wifi net）*/
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;
        isMobileDataEnable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        return isMobileDataEnable;
    }

    /**
     * 判断是否为漫游
     * @param context
     * @return
     */
    public static boolean isNetWorkRomaing(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                TelephonyManager telecomManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if(telecomManager != null && telecomManager.isNetworkRoaming()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断GPS是否打开
     * @param context
     * @return
     */
    public static boolean isGpsEnable(Context context){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = locationManager.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() >0;
    }
}
