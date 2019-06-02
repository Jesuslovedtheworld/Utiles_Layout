package com.jiyun.wanandroid.utils;

/*
* *  author gme
*    time 2019年5月21日
*/

import android.content.Context;
import android.os.Environment;

//获取应用的缓存位置
public class CommonUtil {
    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }
}
