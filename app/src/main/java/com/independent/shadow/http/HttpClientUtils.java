package com.independent.shadow.http;

import android.content.Context;
import android.os.Environment;

import com.independent.shadow.model.HotfixModel;
import com.independent.shadow.util.Utils;
import com.independent.shadow.util.XLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        HotPatchReq req = new HotPatchReq();
        req.setStep("10001");
        ShadowApi service = retrofit.create(ShadowApi.class);
        Call<HotfixModel> model = service.post(req);
        XLog.d(TAG, req.toString());
        model.enqueue(new Callback<HotfixModel>() {
            @Override
            public void onResponse(Call<HotfixModel> call, Response<HotfixModel> response) {
                if (null == response.body()) {
                    return;
                }
                XLog.d(response.body().toString());
                String apkUrl = "http://v1.iqianjin.com/iqianjin_V4.9.1.apk";
                Utils.downloadApk(context, apkUrl, Environment.getExternalStorageDirectory().getAbsolutePath(), "test.apk");
            }

            @Override
            public void onFailure(Call<HotfixModel> call, Throwable t) {
                XLog.d(TAG, t.getMessage());
            }
        });
    }
}
