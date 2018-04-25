package com.example.hongtao.myapplicationtest.animation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout.LayoutParams;
import java.io.IOException;
import java.lang.reflect.Field;

public class UIUtils {

    public static final int COLORGRAY = 100;


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (context == null) {
            return 0;
        }
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2pxLe(Context context, float dpValue) {
        if (context == null) {
            return 0;
        }
//        float density=context.getResources().getDisplayMetrics().density;
//        Log.e("zhenghongtao","density="+density);
//        if(density==3.5 || density==2.625) {//为了兼容乐视的大屏手机
//
//            Log.e("zhenghongtao","windowwidth="+windowwidth);
//            if(windowwidth==1440){
//                return (int)dpValue*windowwidth/360;
//            }
//            if(windowwidth==1080){
//
//            }
//        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int windowwidth = wm.getDefaultDisplay().getWidth();
        return (int)dpValue*windowwidth/360;
        //return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
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

    /**
     * 将16进制颜色值转换为hex值
     *
     * @param color
     * @return
     */
    public static String transformColor(int color) {
        String colorStr = Integer.toHexString(color);
        if (colorStr != null && colorStr.length() == 8) {
            colorStr = colorStr.substring(2);
        }
        return "#" + colorStr;
    }


    public static int readPictureDegree(String paramString) {
        try {
            int i = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
            switch (i) {
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 6:
                    return 90;
                case 3:
                    return 180;
                case 8:
            }
            return 270;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return 0;
    }

    public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2) {
        int i = paramOptions.outHeight;
        int j = paramOptions.outWidth;
        int k = 1;
        int m;
        if ((i > paramInt2) || (j > paramInt1)) {
            k = Math.round(i / paramInt2);
            m = Math.round(j / paramInt1);
            if (k <= m)
                ;
        } else {
            return k;
        }
        return m;
    }

//	private static Dynamics childTop = new Dynamics(400, 0.7f);

    /**
     * bigger than bigger
     *
     * @param v
     * @param bigHeight 单位像素
     * @param time
     */
    public static void expand(final View v, final int bigHeight, final int time) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int temp = v.getLayoutParams().height;
        final long timeBeagin = AnimationUtils.currentAnimationTimeMillis();
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
//				final long endTime = AnimationUtils.currentAnimationTimeMillis() - timeBeagin;
//				if (endTime>=time) {
//					interpolatedTime =1;
//				}else {
//					interpolatedTime = endTime/time*1.0f;
//				}
//
                v.getLayoutParams().height = interpolatedTime == 1 ? bigHeight : (int) (temp + (bigHeight - temp) * interpolatedTime);
                v.setBackgroundColor(Color.argb((int) (COLORGRAY * (1 - interpolatedTime)), 0, 0, 0));
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.VISIBLE);
                v.setBackgroundColor(Color.argb(0, 0, 0, 0));
                LayoutParams params = (LayoutParams) v.getLayoutParams();
                params.height = bigHeight;
                params.width = LayoutParams.MATCH_PARENT;
                v.setLayoutParams(params);
            }
        });
        // 1dp/ms
        if (time == 0) {
            a.setDuration((int) (bigHeight / v.getContext().getResources().getDisplayMetrics().density));
        } else {
            a.setDuration(time);
        }
        v.startAnimation(a);
    }

    /**
     * bigger to smaller
     *
     * @param v
     * @param smallHeight 单位像素
     * @param time
     */
    public static void collapse(final View v, final int smallHeight, int time) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int temp = v.getLayoutParams().height;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1 ? smallHeight : (int) (temp - (temp - smallHeight) * interpolatedTime);
                v.setBackgroundColor(Color.argb((int) (COLORGRAY * interpolatedTime), 0, 0, 0));
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.VISIBLE);
                v.setBackgroundColor(Color.argb(COLORGRAY, 0, 0, 0));
                LayoutParams params = (LayoutParams) v.getLayoutParams();
                params.height = smallHeight;
                params.width = LayoutParams.MATCH_PARENT;
                v.setLayoutParams(params);
            }
        });
        // 1dp/ms
        if (time == 0) {
            a.setDuration((int) (smallHeight / v.getContext().getResources().getDisplayMetrics().density));
        } else {
            a.setDuration(time);
        }
        v.startAnimation(a);
    }



    /**
     * 根据资源ID得到View
     */
    public static View inflate(Context context, int resource, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(resource, root, attachToRoot);
    }

    /**
     * 根据资源ID得到View
     */
    public static View inflate(Context context, int resource, ViewGroup root) {
        return LayoutInflater.from(context).inflate(resource, root);
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();
    }

    /**
     * 得到屏幕高度
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
    }

    public static int getMinScreen(Context context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        return Math.min(width, height);
    }

    public static int getMaxScreen(Context context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        return Math.max(width, height);
    }

    /**
     * 得到不包含状态栏的高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeightWithoutStatusBar(Activity activity) {
        return getScreenHeight(activity) - getStatusBarHeight(activity);
    }

    /**
     * 将一倍尺寸缩放到当前屏幕大小的尺寸（宽）
     *
     * @param w
     * @return
     */
    public static int zoomWidth(Context context, int w) {
        int sw = 0;
        sw = Math.min(getScreenWidth(context), getScreenHeight(context));

        return Math.round(w * sw / 320f + 0.5f);
    }

    /**
     * 将一倍尺寸缩放到当前屏幕大小的尺寸（高）
     *
     * @param h
     * @return
     */
    public static int zoomHeight(Context context, int h) {
        int sh = 0;
        sh = getScreenHeight(context);

        return (int) (h * sh / 480f + 0.5f);
    }

    /**
     * 缩放控件
     *
     * @param w
     * @param view
     */
    public static void zoomViewWidth(Context context, int w, View view) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            return;
        }

        int width = zoomWidth(context, w);
        params.width = width;
    }

    /**
     * 缩放控件
     *
     * @param w
     * @param h
     * @param view
     */
    public static void zoomView(Context context, int w, int h, View view) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (params == null) {
            return;
        }

        params.width = zoomWidth(context, w);
        params.height = zoomWidth(context, h);
    }


    /**
     * 获取顶部状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取NavigationBar 高度
     * 注意: 竖向 和 横向 宽度可能不一致
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return getNavigationBarPortraitHeight(context);
        } else {
            return getNavigationBarLandscapeWidth(context);
        }
    }

    /**
     * NavigationBar 竖屏高度
     *
     * @return
     */
    public static int getNavigationBarPortraitHeight(Context context) {
        if (!hasNavigationBar(context)) {
            return 0;
        }
        final boolean isMeiZu = Build.MANUFACTURER.equals("Meizu");
        if (isMeiZu) {
            return getMeizuNaviogationBarHeight(context);
        } else {
            int result = 0;
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        }
    }

    /**
     * NavigationBar 全屏宽度
     *
     * @return
     */
    public static int getNavigationBarLandscapeWidth(Context context) {
        if (!hasNavigationBar(context)) {
            return 0;
        }

        final boolean isMeiZu = Build.MANUFACTURER.equals("Meizu");
        if (isMeiZu) {
            return getMeizuNaviogationBarHeight(context);
        } else {
            int result = 0;
            Resources resources = context.getResources();
            int resourceId;
            if (isTablet(context)) {
                resourceId = resources.getIdentifier("navigation_bar_height_landscape", "dimen", "android");
            } else {
                resourceId = resources.getIdentifier("navigation_bar_width", "dimen", "android");
            }

            if (resourceId > 0) {
                return context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        }
    }

    @Nullable
    private static int getMeizuNaviogationBarHeight(Context context) {
        final boolean isMeiZu = Build.MANUFACTURER.equals("Meizu");
        final boolean autoHideSmartBar = Settings.System.getInt(context.getContentResolver(),
                "mz_smartbar_auto_hide", 0) == 1;
        if (isMeiZu) {
            if (autoHideSmartBar) {
                return 0;
            } else {
                try {
                    Class c = Class.forName("com.android.internal.R$dimen");
                    Object obj = c.newInstance();
                    Field field = c.getField("mz_action_button_min_height");
                    int height = Integer.parseInt(field.get(obj).toString());
                    return context.getResources().getDimensionPixelSize(height);
                } catch (Throwable e) { // 不自动隐藏smartbar同时又没有smartbar高度字段供访问，取系统navigationbar的高度
                    return 0;
                }
            }
        }
        return 0;
    }

    private static boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 是否存在NavigationBar
     *
     * @param context
     * @return
     */
    public static boolean hasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        // 魅族手机不存在NavigationBar
        return id > 0 && resources.getBoolean(id) && !Build.MANUFACTURER.equals("Meizu");
    }

    /**
     * 判断是否是横屏
     *
     * @return
     */
    public static boolean isLandscape(Activity activity) {
        return isLandscape(activity);
    }

    /**
     * 判断是否是横屏
     *
     * @param activity
     * @return
     */
    public static boolean isLandscape(Context activity) {
        if (activity == null) {
            return false;
        }

        int t = activity.getResources().getConfiguration().orientation;
        return t == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 全屏
     *
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 取消全屏
     *
     * @param activity
     */
    public static void cancelFullScreen(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置为横屏
     *
     * @param activity
     */
    public static void setScreenLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置为竖屏
     *
     * @param activity
     */
    public static void setScreenPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * 打开屏幕常亮
     */
    public static void enableScreenAlwaysOn(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /**
     * 关闭屏幕常亮
     */
    public static void disableScreenAlwaysOn(Context context) {
        if (context instanceof Activity) {
            // 由于会引起栈溢出，暂时取消
            // ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /**
     * 测量view大小
     *
     * @param child
     */
    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 测量该控件的 尺寸
     *
     * @param v
     * @return int[]{width,height}
     */
    public static int[] measure(View v) {
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return new int[]{v.getMeasuredWidth(), v.getMeasuredHeight()};
    }

    /**
     * 缩放控件
     */
    public static void zoomViewHeight(Context context, int h, View view) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (params == null) {
            return;
        }

        params.height = zoomWidth(context, h);
    }


    /**
     * public static void showToast(int txtId) { mToast =
     * Toast.makeText(LetvApplication.getInstance(), txtId, Toast.LENGTH_SHORT);
     * mToast.setText(txtId); mToast.setGravity(Gravity.CENTER, 0, 0);
     * mToast.duration=Toast.LENGTH_SHORT); mToast.show(); }
     * <p/>
     * /** 隐藏键盘输入法
     *
     * @param mActivity
     */
    public static void hideInputMethod(Activity mActivity) {
        if (null != mActivity && null != mActivity.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) mActivity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != mInputMethodManager) {
                mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 测量该控件的 尺寸
     *
     * @param v
     * @return int[]{width,height}
     */
    public static int[] measureSize(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        int width = v.getMeasuredWidth();
        int height = v.getMeasuredHeight();
        return new int[]{width, height};
    }

    /**
     * 将一倍尺寸缩放到当前屏幕大小的尺寸（高）
     */
    public static int zoomRealHeight(Context context, int h) {
        int sh = 0;
        sh = Math.max(getScreenWidth(context), getScreenHeight(context));

        return (int) (h * sh / 480f + 0.5f);
    }

    public static int[] getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    public static int[] getLocationInWindow(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap picBlur(Context context, Bitmap bitmap, Float radius) {
        Bitmap outPutBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript rs = RenderScript.create(context);
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outPutBitmap);
        ScriptIntrinsicBlur blur =
                ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        blur.setInput(allIn);
        blur.setRadius(10f);
        blur.forEach(allOut);
        allIn.copyTo(outPutBitmap);
        rs.destroy();

        return outPutBitmap;
    }

    /**
     * 颜色选择器
     *
     * @param normal
     * @param pressed
     * @return
     */
    public static ColorStateList createColorStateList(int normal, int pressed) {
        int[] colors = new int[]{pressed, pressed, pressed, pressed, normal, -1};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.checked, android.R.attr.state_enabled};
        states[2] = new int[]{android.R.attr.state_selected, android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused, android.R.attr.state_enabled};
        states[4] = new int[]{android.R.attr.state_enabled};
        states[5] = new int[]{}; //View.EMPTY_STATE_SET
        return new ColorStateList(states, colors);
    }

    /**
     * 图片选择
     */
    public static StateListDrawable createStateDrawable(Drawable normal, Drawable pressed,
                                                        Drawable focus, Drawable checked) {
        StateListDrawable sd = new StateListDrawable();
        // 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        // 所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
        sd.addState(new int[]{android.R.attr.state_checked}, checked);
        sd.addState(new int[]{android.R.attr.state_focused}, focus);
        sd.addState(new int[]{android.R.attr.state_selected}, focus);
        sd.addState(new int[]{}, normal);
        return sd;
    }

    /**
     * 根据颜色圆角生成  drawable对象
     */
    public static GradientDrawable createGradientDrawable(int radious, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radious);
        return drawable;
    }

    /**
     * public static void showToast(int txtId) { mToast =
     * Toast.makeText(LetvApplication.getInstance(), txtId, Toast.LENGTH_SHORT);
     * mToast.setText(txtId); mToast.setGravity(Gravity.CENTER, 0, 0);
     * mToast.setDuration(Toast.LENGTH_SHORT); mToast.show(); }
     * <p/>
     * /** 隐藏键盘输入法
     *
     * @param mActivity
     */
    public static void hideSoftkeyboard(Activity mActivity) {
        if (null != mActivity && null != mActivity.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) mActivity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != mInputMethodManager) {
                mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    /**
     * 收藏渐变规则
     */
    private static float[] collectionAniScaleRec = new float[]{1.0f, 0.6f, 1.5f, 0.8f, 1.0f};

    private static float[] unConllectionAniScalRec = new float[]{1.0f, 0.6f, 1.5f, 0.6f, 1.0f};

    /**
     * 动画总执行时间ms(由于实际几个Animation之间执行回调需要时间，所以实际总动画时间会比这个时间长)
     */
    private static final int animTotallTime = 220;

    /**
     * 渐变率(必须要能被scaleSize数组的相邻两个数值之差整除)
     */
    private static float scaleRate = 0.1f;

    /**
     * 赞动画
     *
     * @param view
     */
    public static void animTop(Context context, final View view) {
        if (view != null) {
            if (null != view.getAnimation() && !view.getAnimation().hasEnded()) {
                return;
            }
            view.post(new Runnable() {
                @Override
                public void run() {
                    animCollection(view, collectionAniScaleRec, 0);
                }
            });
        }
    }

    /**
     * 收藏/取消收藏 动画实际逻辑
     */
    private static void animCollection(final View view, final float[] aniScaleRec, final int index) {
        animFrames(view, index, aniScaleRec, new Runnable() {

            @Override
            public void run() {
                if (index + 1 < aniScaleRec.length - 1) {
                    animCollection(view, aniScaleRec, index + 1);
                }
            }
        });

    }

    /**
     * 收藏view的每次变换动画逻辑
     *
     * @param view
     * @param index
     * @param aniScaleRec
     * @param callback    动画执行完成回调
     */
    private static void animFrames(final View view, final int index, float[] aniScaleRec, final Runnable callback) {
        /**
         * 总帧数
         */
        float totalFrame = 0;
        for (int i = 0; i < aniScaleRec.length - 1; i++) {
            totalFrame += Math.abs(aniScaleRec[i + 1] - aniScaleRec[i]);
        }
        /**
         * 执行每一帧切换的时间
         */
        final int animFrameTime = animTotallTime / ((int) (totalFrame / scaleRate));
        float animEnd = aniScaleRec[index + 1];
        float animStart = aniScaleRec[index];
        final int scaleCount = Math.abs(((int) (animEnd * 100) - (int) (animStart * 100)) / ((int) (scaleRate * 100)));
        int duration = scaleCount * animFrameTime;
        ScaleAnimation sa = new ScaleAnimation(animStart, animEnd, animStart, animEnd, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);
        sa.setDuration(duration);
        sa.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.run();
            }
        });
        view.startAnimation(sa);
    }
}
