package com.independent.shadow.http;

import com.independent.shadow.model.HotfixModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public interface ShadowApi {

    @POST("/getHotPatch")
    Call<HotfixModel> post(@Body BaseRequestParam reqParam);
}
