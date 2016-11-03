package com.independent.shadow.http;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.independent.shadow.model.BaseHotfixModel;
import com.independent.shadow.model.HotfixModel;
import com.independent.shadow.util.Utils;
import com.independent.shadow.util.XLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by chuck on 16/11/2.
 */

public class HttpClientUtils {
    private static final String TAG = "HttpClientUtils";

    /**
     * 获取补丁包
     *
     * @param context
     */
    public static void getHotFixApk(final Context context) {

        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<HotfixModel> call = serviceApi.getHotPatch("5.1");
        call.enqueue(new Callback<HotfixModel>() {
            @Override
            public void onResponse(Call<HotfixModel> call, Response<HotfixModel> response) {
                if (response.isSuccess()) {
                    HotfixModel hotfixModel = response.body();
                    if (hotfixModel.getCode() == 1 && !TextUtils.isEmpty(hotfixModel.getHotUrl())) {
                        Toast.makeText(context, "请求成功,正在下载补丁包", Toast.LENGTH_SHORT).show();
                        Utils.downloadApk(context, hotfixModel.getHotUrl(), Environment.getExternalStorageDirectory().getAbsolutePath(), "testPatch.apk");
                    } else {
                        Toast.makeText(context, hotfixModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<HotfixModel> call, Throwable t) {
                Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * 提交修复补丁状态
     *
     * @param context
     */
    public static void submitModifiedStatus(final Context context) {
        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<BaseHotfixModel> call = serviceApi.submitPatchStatus("1", 1, "518518518518518");
        call.enqueue(new Callback<BaseHotfixModel>() {
            @Override
            public void onResponse(Call<BaseHotfixModel> call, Response<BaseHotfixModel> response) {
                XLog.i(TAG, call.request());
                if (response.isSuccess()) {
                    BaseHotfixModel baseHotfixModel = response.body();
                    if (baseHotfixModel.getCode() == 1) {
                        Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "提交失败" + baseHotfixModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseHotfixModel> call, Throwable t) {
                Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 提交单个存在问题用户的信息
     *
     * @param context
     */

    public static void submitUserInfo(final Context context, final ActionCallbackListener callbackListener) {
        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<BaseHotfixModel> call = serviceApi.submitUserInfoInBug("4.4", "4.9.0", "518518518518", "4G", "用户点击了一个小按钮");
        call.enqueue(getCallback(callbackListener));
    }

    @NonNull
    private static Callback<BaseHotfixModel> getCallback(final ActionCallbackListener callbackListener) {
        return new Callback<BaseHotfixModel>() {
                @Override
                public void onResponse(Call<BaseHotfixModel> call, Response<BaseHotfixModel> response) {
                    XLog.i(TAG, call.request());
                    if (response.isSuccess()) {
                        BaseHotfixModel baseHotfixModel = response.body();
                        if (baseHotfixModel.getCode() == 1) {
                            callbackListener.onSuccess(baseHotfixModel);
                        } else {
                            callbackListener.onAppFailure(baseHotfixModel);
                        }
                    }
//                if (response.isSuccess()) {
//                    BaseHotfixModel baseHotfixModel = response.body();
//                    if (baseHotfixModel.getCode() == 1) {
//                        Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "提交失败" + baseHotfixModel.getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                }
                }

            @Override
            public void onFailure(Call<BaseHotfixModel> call, Throwable t) {
                callbackListener.onFailure();
//                Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
