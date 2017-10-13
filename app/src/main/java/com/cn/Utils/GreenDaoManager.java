package com.cn.Utils;

import com.cn.Application.MyApplication;
import com.cn.DbFile.DaoMaster;
import com.cn.DbFile.DaoSession;

/**
 * Created by ld on 2017/10/13.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getAppContext(),"my-db",null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();
    }
    public DaoMaster getGreenDaoMaster(){
        return mDaoMaster;
    }
    public DaoSession getGreedDaoSession(){
        return mDaoSession;
    }
    public static GreenDaoManager getInstance(){
        if(mInstance == null){
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }
}
