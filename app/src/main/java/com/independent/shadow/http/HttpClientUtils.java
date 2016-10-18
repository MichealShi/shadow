package com.independent.shadow.http;

import android.content.Context;
import android.os.Environment;

import com.independent.shadow.model.HotfixModel;
import com.independent.shadow.util.Utils;
import com.independent.shadow.util.XLog;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public class HttpClientUtils {
    private static final String TAG = "HttpClientUtils";

    private static final String BASE_URL = "http://v3.iqianjin.com";

    public static void getHotFixApk(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShadowApi service = retrofit.create(ShadowApi.class);
        Call<HotfixModel> model = service.post(new ReqParam(context));
        model.enqueue(new Callback<HotfixModel>() {
            @Override
            public void onResponse(Response<HotfixModel> response, Retrofit retrofit) {
                XLog.d(response.body().toString());
                String apkUrl = "http://v1.iqianjin.com/iqianjin_V4.9.1.apk";
                Utils.downloadApk(context, apkUrl, Environment.getExternalStorageDirectory().getAbsolutePath(), "test.apk");
            }

            @Override
            public void onFailure(Throwable t) {
                XLog.d(t.getMessage());
            }
        });
    }
}
