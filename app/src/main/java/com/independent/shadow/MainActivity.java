package com.independent.shadow;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.independent.shadow.http.HttpClientUtils;
import com.independent.shadow.util.SampleApplicationContext;
import com.independent.shadow.util.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.yanzhenjie.permission.AndPermission;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Tinker.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this.getApplication());

        Log.e(TAG, "i am on onCreate classloader:" + MainActivity.class.getClassLoader().toString());
        //test resource change
        Log.e(TAG, "i am on onCreate string:" + getResources().getString(R.string.test_resource));

        // request runtime permission
        AndPermission.with(this).requestCode(101)
                .permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .send();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        bindViews();

        HttpClientUtils.getHotFixApk(this);
    }

    private void bindViews() {
        findViewById(R.id.loadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
            }
        });

        findViewById(R.id.loadLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for lib/armeabi, just use TinkerInstaller.loadLibrary
                TinkerInstaller.loadArmLibrary(getApplicationContext(), "stlport_shared");
//                TinkerInstaller.loadLibraryFromTinker(getApplicationContext(), "assets/x86", "stlport_shared");
            }
        });

        findViewById(R.id.cleanPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinker.with(getApplicationContext()).cleanPatch();
            }
        });

        findViewById(R.id.killSelf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        findViewById(R.id.showInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(MainActivity.this);
            }
        });

        findViewById(R.id.infoTv).setVisibility(View.GONE);
    }

    public boolean showInfo(Context context) {
        // add more Build Info
        final StringBuilder sb = new StringBuilder();
        Tinker tinker = Tinker.with(getApplicationContext());
        if (tinker.isTinkerLoaded()) {
            sb.append("[patch is loaded] %n");
            sb.append(String.format("[buildConfig CLIENT_VERSION] %s %n", BuildInfo.CLIENTVERSION));
            sb.append(String.format("[buildConfig MESSAGE] %s %n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s %n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
            sb.append(String.format("[REAL TINKER_ID] %s %n", tinker.getTinkerLoadResultIfPresent().getTinkerID()));
            sb.append(String.format("[packageConfig patchMessage] %s %n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName
                    ("patchMessage")));
            sb.append(String.format("[TINKER_ID Rom Space] %d k %n", tinker.getTinkerRomSpace()));

        } else {
            sb.append("[patch is not loaded] %n");
            sb.append(String.format("[buildConfig CLIENT_VERSION] %s %n", BuildInfo.CLIENTVERSION));
            sb.append(String.format("[buildConfig MESSAGE] %s %n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s %n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
        }
        sb.append(String.format("[BaseBuildInfo Message] %s %n", BaseBuildInfo.TEST_MESSAGE));

        final TextView v = new TextView(context);
        v.setText(sb);
        v.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setTextColor(0xFF000000);
        v.setTypeface(Typeface.MONOSPACE);
        final int padding = 16;
        v.setPadding(padding, padding, padding, padding);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(v);
        final AlertDialog alert = builder.create();
        alert.show();
        return true;
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "i am on onResume");

        super.onResume();
        Utils.setBackground(false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.setBackground(true);
    }
}
