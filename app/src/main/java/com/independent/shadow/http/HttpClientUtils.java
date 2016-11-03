package com.independent.shadow.http;

import android.support.annotation.NonNull;

import com.independent.shadow.model.BaseHotfixModel;
import com.independent.shadow.model.HotfixModel;
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
     * @param callbackListener if (hotfixModel.getCode() == 1 && !TextUtils.isEmpty(hotfixModel.getHotUrl())) {
     *                         //            Toast.makeText(context, "请求成功,正在下载补丁包", Toast.LENGTH_SHORT).show();
     *                         //            Utils.downloadApk(context, hotfixModel.getHotUrl(), Environment.getExternalStorageDirectory().getAbsolutePath(), "testPatch.apk");
     *                         //        }
     */
    public static void getHotFixApk(final ActionCallbackListener callbackListener) {
        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<HotfixModel> call = serviceApi.getHotPatch("5.1");
        call.enqueue(getCallback(callbackListener));
    }


    /**
     * 提交修复补丁状态
     *
     * @param callbackListener
     */
    public static void submitModifiedStatus(final ActionCallbackListener callbackListener) {
        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<BaseHotfixModel> call = serviceApi.submitPatchStatus("1", 1, "518518518518518");
        call.enqueue(getCallbackBase(callbackListener));
    }

    /**
     * 提交单个存在问题用户的信息
     *
     * @param callbackListener
     */
    public static void submitUserInfo(final ActionCallbackListener callbackListener) {
        ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);
        Call<BaseHotfixModel> call = serviceApi.submitUserInfoInBug("4.4", "4.9.0", "518518518518", "4G", "用户点击了一个小按钮");
        call.enqueue(getCallbackBase(callbackListener));
    }


    @NonNull
    private static Callback<BaseHotfixModel> getCallbackBase(final ActionCallbackListener callbackListener) {
        return new Callback<BaseHotfixModel>() {
            @Override
            public void onResponse(Call<BaseHotfixModel> call, Response<BaseHotfixModel> response) {
                XLog.i(TAG, call.request());
                if (response.isSuccessful()) {
                    BaseHotfixModel baseHotfixModel = response.body();
                    judgMentCode(baseHotfixModel, callbackListener);
                }
            }

            @Override
            public void onFailure(Call<BaseHotfixModel> call, Throwable t) {
                callbackListener.onFailure();
            }
        };
    }

    @NonNull
    private static Callback<HotfixModel> getCallback(final ActionCallbackListener callbackListener) {
        return new Callback<HotfixModel>() {
            @Override
            public void onResponse(Call<HotfixModel> call, Response<HotfixModel> response) {
                if (response.isSuccessful()) {
                    HotfixModel hotfixModel = response.body();
                    judgMentCode(hotfixModel, callbackListener);
                }
            }

            @Override
            public void onFailure(Call<HotfixModel> call, Throwable t) {
                callbackListener.onFailure();
            }

        };
    }

    /**
     * 根据code 判断成功还是失败
     *
     * @param baseHotfixModel
     * @param callbackListener
     */

    private static void judgMentCode(BaseHotfixModel baseHotfixModel, ActionCallbackListener callbackListener) {
        if (baseHotfixModel.getCode() == 1) {
            callbackListener.onSuccess(baseHotfixModel);
        } else {
            callbackListener.onAppFailure(baseHotfixModel);
        }
    }

}
