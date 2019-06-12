package com.baidu.thanksgod.outhor;

import android.content.Context;

import com.baidu.thanksgod.dao.DaoMaster;

import java.util.List;

public class MsgDaoUtil {
    private static final String TAG = MsgDaoUtil.class.getSimpleName();
    private static OnDbUpdateListener mUpdateListener;

    public void setUpdateListener(OnDbUpdateListener updateListener) {
        mUpdateListener = updateListener;
    }

    public static boolean insertMsg(Context context,Msg msg){
        boolean flag = false;
        flag = ChatManager.getDaoSession(context).getMsgDao().insert(msg) == -1 ? false : true;
        if (flag){
            mUpdateListener.onUpdate(msg);
        }
        return false;
    }
    public interface OnDbUpdateListener{
        void onUpdate(Msg msg);
    }
    public List<Msg> queryAll(Context context){
        return ChatManager.getDaoSession(context).loadAll(Msg.class);
    }
}
