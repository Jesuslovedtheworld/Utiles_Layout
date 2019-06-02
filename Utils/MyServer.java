package com.jiyun.day15;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by $lzj on 2019/3/21.
 */
public interface MyServer {

    public String Url = "http://cdn.banmi.com/banmiapp/";

    @GET("apk/banmi_330.apk")
    Observable<ResponseBody> getData();
}
