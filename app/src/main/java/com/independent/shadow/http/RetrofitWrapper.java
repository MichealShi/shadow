package com.independent.shadow.http;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chuck on 16/11/2.
 */
public class RetrofitWrapper {
    private static RetrofitWrapper instance;
    private Context mContext;
    private Retrofit retrofit;

    private RetrofitWrapper() {
        //创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)   // 定义访问的主机地址
                .addConverterFactory(GsonConverterFactory.create()) //解析方法
                .build();
    }

    public static RetrofitWrapper getInstance() {
        if (null == instance) {
            synchronized (RetrofitWrapper.class) {
                if (null == instance) {
                    instance = new RetrofitWrapper();
                }
            }
        }

        return instance;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
