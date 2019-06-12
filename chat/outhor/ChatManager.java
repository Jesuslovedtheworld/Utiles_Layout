package com.baidu.thanksgod.outhor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.thanksgod.dao.DaoMaster;
import com.baidu.thanksgod.dao.DaoSession;

public class ChatManager {
    private static final boolean ENCRYPTED = true;
    private Context mContext;
    private static final String DB_NAME = "msg.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelpter;
    private static ChatManager mChatManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private ChatManager(Context context) {
        this.mContext = context;

        //初始化数据库信息
        mDevOpenHelpter = new DaoMaster.DevOpenHelper(mContext, DB_NAME);
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static ChatManager getInstance(Context context) {
        if (mChatManager == null) {
            synchronized (ChatManager.class) {
                if (mChatManager == null) {
                    mChatManager = new ChatManager(context);
                }
            }
        }
        return mChatManager;
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDataBase(Context context) {
        if (mDevOpenHelpter == null) {
            getInstance(context);
        }
        return mDevOpenHelpter.getWritableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDataBase(Context context) {
        if (mDevOpenHelpter == null) {
            getInstance(context);
        }
        return mDevOpenHelpter.getReadableDatabase();
    }

    /**
     * 获取DaoMaster
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (mDaoMaster == null) {
            mDaoMaster = new DaoMaster(getWritableDataBase(context));
        }
        return mDaoMaster;
    }
    public static DaoSession getDaoSession(Context context){
        if (mDaoSession == null) {
            synchronized (ChatManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return  mDaoSession;
    }

}
