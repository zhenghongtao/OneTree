package com.example.hongtao.myapplicationtest.yidao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.WindowManager;

/**
 * Created by hongtao on 17/2/15.
 */

public class UiUtils {
    public static final int COLORGRAY = 100;

    static int  windowHeight=1440;
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2pxLe(Context context, float dpValue) {
        if (context == null) {
            return 0;
        }
        float density=context.getResources().getDisplayMetrics().density;
       if(density==3.5) {//为了兼容乐视的大屏手机
           WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
           int windowwidth = wm.getDefaultDisplay().getWidth();
           if(windowwidth==windowHeight){
               return (int)dpValue*4;
           }
       }
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static float sp2px(Context context, float spValue) {
        if (context == null)
            return 0;
        return spValue * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int px2dip(Context context, float pxValue) {
        if (context == null)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
