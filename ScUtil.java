package com.example.wangxiang.day14guoangbu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ScUtil {
    private static final int NETWORK_NONE = -1;
    private static final int NETWORK_MOBILE = 0;
    private static final int NETWORK_WIFI = 1;

    public static int getNetWorkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;

        }
        return NETWORK_NONE;

    }
}