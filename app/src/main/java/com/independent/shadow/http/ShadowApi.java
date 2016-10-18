package com.independent.shadow.http;

import com.independent.shadow.model.HotfixModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public interface ShadowApi {

    @POST("/C4000/M4001")
    Call<HotfixModel> post(@Body ReqParam reqParam);
}
