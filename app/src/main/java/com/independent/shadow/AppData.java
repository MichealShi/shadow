package com.independent.shadow;

import android.content.Context;

import com.independent.shadow.prefer.PreferPropertyString;
import com.independent.shadow.prefer.PreferenceUtil;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public class AppData {
    private static final String SP_NAME = "shadow";
    private static PreferenceUtil sp = new PreferenceUtil();
    private static Context context = null;


    /**
     * 初始化, 在Application中调用
     *
     * @param c
     */
    public static void init(Context c) {
        context = c;
        sp.init(c, SP_NAME);
    }

    public static Context getContext() {
        return context;
    }

    public static final PreferPropertyString imeiCash = new PreferPropertyString(sp, "imeiCash", ""); // imei
}
