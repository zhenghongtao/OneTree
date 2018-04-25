package com.example.hongtao.myapplicationtest;

import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by hongtao on 17/8/4.
 */

public class TinkerUtils {
//    private static final String TAG = "Tinker";
//    public static void installTinker(Context context, ApplicationLike applicationLike) {
//        // 安装tinker
//        SampleTinkerManager.initCurrentChannelValue(ChannelUtil.getChannel());
//        SampleTinkerManager.setTinkerApplicationLike(applicationLike);
//        SampleTinkerManager.initFastCrashProtect();
//        //should set before com.dx168.patchsdk.sample.tinker is installed
//        SampleTinkerManager.setUpgradeRetryEnable(true);
//        //installTinker after load multiDex
//        //or you can put com.tencent.com.dx168.patchsdk.sample.tinker.** to main dex
//        SampleTinkerManager.installTinker(applicationLike);
//        Tinker.with(context);
//        //使用Hack的方式,如果补丁中有so库 那么直接加载补丁中的armeabi下的so库(将tinker library中的armeabi注册到系统的library path中。)
//        TinkerLoadLibrary.installNavitveLibraryABI(context, "armeabi");
//    }
//    public static void setUpTinker(Context context) {
//        if (ChannelUtil.isGoogleVersion()) {
//            return;
//        }
//        //在补丁管理后台注册的id和key，参数值配置在gradle文件中
//        String appId = BuildConfig.TINKER_APP_ID;
//        String appSecret = BuildConfig.TINKER_APP_SECRET;
//        String tinkerUrl = BuildConfig.TINKER_PATCH_URL;
//        PatchManager.getInstance().init(context, tinkerUrl, appId, appSecret, new ActualPatchManager() {
//            @Override
//            public void cleanPatch(Context context) {
//                TinkerInstaller.cleanPatch(context);
//                DebugUtil.debug(TAG, "local patch sdk >>>>> cleanPatch");
//            }
//            @Override
//            public void applyPatch(Context context, String patchPath) {
//                TinkerInstaller.onReceiveUpgradePatch(context, patchPath);
//                DebugUtil.debug(TAG, "local patch sdk >>>>> applyPatch: " + patchPath);
//            }
//        });
//        PatchManager.getInstance().setTag(ChannelUtil.getChannel());//可用于灰度发布
//        PatchManager.getInstance().setChannel(ChannelUtil.getChannel());
//        PatchManager.getInstance().queryAndApplyPatch(new PatchListener() {
//            @Override
//            public void onQuerySuccess(String response) {
//                DebugUtil.debug(TAG, "local patch sdk >>>>>  onQuerySuccess response={ignore in log}");
//            }
//            @Override
//            public void onQueryFailure(Throwable e) {
//                DebugUtil.debug(TAG, "local patch sdk >>>>>  onQueryFailure e=" + Log.getStackTraceString(e));
//            }
//            @Override
//            public void onDownloadSuccess(String path) {
//                DebugUtil.debug(TAG, "local patch sdk >>>>>  onDownloadSuccess path=" + path);
//            }
//            @Override
//            public void onDownloadFailure(Throwable e) {
//                DebugUtil.debug(TAG, "local patch sdk >>>>>  onDownloadFailure e=" + Log.getStackTraceString(e));
//            }
//            @Override
//            public void onApplySuccess() {
//                DebugUtil.debug(TAG, "local patch sdk >>>>>  onApplySuccess");
//            }
//            @Override
//            public void onApplyFailure(String msg) {
//                DebugUtil.debug(TAG, "local patch sdk >>>>> onApplyFailure msg=" + msg);
//            }
//            @Override
//            public void onCompleted() {
//                DebugUtil.debug(TAG, "local patch sdk >>>>> onCompleted");
//            }
//        });
//    }
}
