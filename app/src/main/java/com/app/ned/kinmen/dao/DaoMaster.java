package com.app.ned.kinmen.dao;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/**
 * Created by Ned on 16/8/4.
 */
public class DaoMaster extends AbstractDaoMaster {
    private final int SCHEMA_VERSON = 1;

    public DaoMaster(Database db, int schemaVersion) {
        super(db, schemaVersion);
    }

    @Override
    public AbstractDaoSession newSession() {
        return null;
    }

    @Override
    public AbstractDaoSession newSession(IdentityScopeType type) {
        return null;
    }
}
