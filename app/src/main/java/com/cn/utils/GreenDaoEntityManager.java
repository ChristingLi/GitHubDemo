package com.cn.utils;


import com.cn.dbFile.GreenDaoEntityDao;

/**
 * Created by ld on 2017/10/13.
 */

public class GreenDaoEntityManager {
    public static GreenDaoEntityManager entityManager;
    public GreenDaoEntityDao greenDaoEntityDao;

    /**
     * 创建user表实例
     * @return
     */
    public GreenDaoEntityDao getGreenDaoEntity(){
        greenDaoEntityDao = GreenDaoManager.getInstance().getGreedDaoSession().getGreenDaoEntityDao();
        return  greenDaoEntityDao;
    }
    public static GreenDaoEntityManager getInstance(){
        if(entityManager == null){
            entityManager = new GreenDaoEntityManager();
        }
        return entityManager;
    }
}
