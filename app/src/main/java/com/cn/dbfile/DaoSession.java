package com.cn.dbFile;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.cn.dbfile.GreenDaoEntity;

import com.cn.dbFile.GreenDaoEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenDaoEntityDaoConfig;

    private final GreenDaoEntityDao greenDaoEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenDaoEntityDaoConfig = daoConfigMap.get(GreenDaoEntityDao.class).clone();
        greenDaoEntityDaoConfig.initIdentityScope(type);

        greenDaoEntityDao = new GreenDaoEntityDao(greenDaoEntityDaoConfig, this);

        registerDao(GreenDaoEntity.class, greenDaoEntityDao);
    }
    
    public void clear() {
        greenDaoEntityDaoConfig.getIdentityScope().clear();
    }

    public GreenDaoEntityDao getGreenDaoEntityDao() {
        return greenDaoEntityDao;
    }

}