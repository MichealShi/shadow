package com.independent.shadow.http;

import com.independent.shadow.model.BaseHotfixModel;
import com.independent.shadow.model.HotfixModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chuck on 16/11/2.
 */
public interface ServiceApi {

    /**
     * 获取补丁包
     *
     * @param appVersion
     * @return
     */
    @GET("/getHotPatch")
    Call<HotfixModel> getHotPatch(@Query("appVersion") String appVersion);


    /**
     * 提交补丁修复状态
     *
     * @param hotPushType 补丁类型（热修复，a/b testting） 默认是1，以后有需要在添加类型
     * @param appState    1=成功;2=失败
     * @param imei        设备唯一标示
     * @return
     */
    @GET("/appHotFixState")
    Call<BaseHotfixModel> submitPatchStatus(@Query("hotPushType") String hotPushType, @Query("appState") int appState, @Query("imei") String imei);


    /**
     * 单个存在BUG的用户信息提交
     *
     * @param osVersion  手机系统版本
     * @param appVersion app版本
     * @param imei       设备唯一标示
     * @param net        当前用户使用的网络
     * @param step       用户使用的行为
     * @return
     */

    @GET("/addOneUserStep")
    Call<BaseHotfixModel> submitUserInfoInBug(@Query("osVersion") String osVersion, @Query("appVersion") String appVersion, @Query("imei") String imei, @Query("net") String net, @Query("step") String step);

}
