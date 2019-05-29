package com.example.sc.okhttp;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private static volatile OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler handler;

    public OkHttpEngine(Context context) {
        File sdCache = context.getExternalCacheDir();//设置下载地址
        int cacheSize = 10 * 1024 * 1024;//设置缓存大小
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//设置超时时间  15秒
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();//创建请求 对象
        handler = new Handler();
    }

    public static OkHttpEngine getmInstance(Context context){
        if (mInstance == null){
            synchronized (OkHttpEngine.class){
                if (mInstance == null){
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }
    public void getAsynHttp(String url,ResultCallBack resultCallBack){//异步get请求
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call,resultCallBack);

    }
    private void dealResult(Call call,final ResultCallBack resultCallBack){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(),e,resultCallBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(resultCallBack,response.body().string());
            }
        });
    }
    private void sendSuccessCallback(final ResultCallBack resultCallBack, final String str){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (resultCallBack != null){
                    try {
                        resultCallBack.onResponse(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //失败
    private void sendFailedCallback(final Request request,final Exception e,final ResultCallBack resultCallBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (resultCallBack != null){
                    resultCallBack.onError(request,e);
                }
            }
        });
    }
}
