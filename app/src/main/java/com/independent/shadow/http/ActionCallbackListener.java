package com.independent.shadow.http;

import com.independent.shadow.model.BaseHotfixModel;

/**
 * Created by chuck on 16/11/2.
 */
public interface ActionCallbackListener<T> {
    /**
     * 请求成功  code == 1
     *
     * @param data
     * @param <T>
     */
    <T extends BaseHotfixModel> void onSuccess(T data);

    /**
     * 请求成功 但是 code != 1
     *
     * @param data
     * @param <T>
     */
    <T extends BaseHotfixModel> void onAppFailure(T data);

    /**
     * 请求失败
     */
    void onFailure();

}
