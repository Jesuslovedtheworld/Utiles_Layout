package com.baidu.mall.beandaoutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.mall.dao.DaoMaster;
import com.baidu.mall.dao.DaoSession;

public class ShopManager {
    // 是否加密
    public static final boolean ENCRYPTED = true;
    private Context mContext;
    private static final String DB_NAME = "shop.db";
    private static ShopManager mDbManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;

    private ShopManager(Context mContext) {
        this.mContext = mContext;
        //初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME);
        getDaoMaster(mContext);
        getDaoSession(mContext);
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static ShopManager getInstace(Context context) {
        if (mDbManager == null) {
            synchronized (ShopManager.class) {
                if (mDbManager == null) {
                    mDbManager = new ShopManager(context);
                }
            }
        }
        return mDbManager;
    }

    /**
     *读取可写数据库
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (mDevOpenHelper == null) {
            getInstace(context);
        }
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     *读取可读数据库
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (mDevOpenHelper == null) {
            getInstace(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取daoMastener
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (mDaoMaster == null){
            mDaoMaster = new DaoMaster(getWritableDatabase(context));
        }
        return mDaoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (mDaoSession == null){
            synchronized (ShopManager.class){
                  mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }
}
