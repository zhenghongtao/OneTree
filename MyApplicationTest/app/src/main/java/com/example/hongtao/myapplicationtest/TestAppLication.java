package com.example.hongtao.myapplicationtest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import com.alipay.euler.andfix.patch.PatchManager;
import com.androidfix.hongtao.base_config_lib.robust.PatchManipulateImp;
import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import android.support.multidex.MultiDex;
import java.io.File;
import java.util.List;

/**
 * Created by hongtao on 17/1/20.
 */
public class TestAppLication extends Application {
    PatchManager patchManager;
    String  appversion="0.0.1";
    String TAG=TestAppLication.class.getSimpleName();






    @Override
    public void onCreate() {
        super.onCreate();
        //loadpath("out.apatch");
        //runRobust();
    }

    /**
     * 加载补丁文件
     *//*
    public void loadpath(String filename){

        patchManager = new PatchManager(this);
        patchManager.init(getAppVersionName(this));//current version
        patchManager.loadPatch();
        try {
            // .apatch file path
            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+filename;
            File locFile = new File(patchFileString);
            if(locFile.exists()) {
                patchManager.addPatch(locFile.getAbsolutePath());
            }
            Log.e(TAG, "加载成功：" + locFile.getAbsolutePath());
        } catch (Exception e) {
            Log.e(TAG, "e="+e.getMessage());
        }
    }*//**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
   /* private void runRobust() {
        new PatchExecutor(getApplicationContext(), new PatchManipulateImp(), new RealCallback()).start();
    }
    class RealCallback implements RobustCallBack {


        @Override
        public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {
            System.out.println(" robust arrived in onPatchListFetcheds result="+result);
        }

        @Override
        public void onPatchFetched(boolean result, boolean isNet, Patch patch) {
            System.out.println(" robust arrived in onPatchFetched result="+result);
        }

        @Override
        public void onPatchApplied(boolean result, Patch patch) {
            System.out.println(" robust arrived in onPatchApplied result="+result+"，patch="+patch);

        }

        @Override
        public void logNotify(String log, String where) {
            System.out.println(" robust arrived in logNotify where=" + where);
        }

        @Override
        public void exceptionNotify(Throwable throwable, String where) {
            throwable.printStackTrace();
            System.out.println(" robust arrived in exceptionNotify where=" + where);
        }
    }*/

}
