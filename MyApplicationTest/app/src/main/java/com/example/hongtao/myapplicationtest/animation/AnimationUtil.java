package com.example.hongtao.myapplicationtest.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.hongtao.myapplicationtest.R;

/**
 * Created by hongtao on 17/10/14.
 */

public class AnimationUtil {

    /**
     * 改变View 高度
     * 动画没有执行完需要收到关闭
     *
     * @param rl_content_layout
     * @param toHeight          目标高度
     * @return
     */
    public static ValueAnimator changeViewHeight(final View rl_content_layout, int toHeight) {
        if(rl_content_layout.getHeight()!=toHeight) {
            ValueAnimator va = ValueAnimator.ofInt(rl_content_layout.getHeight(), toHeight);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    //获取当前的height值
                    int h = (Integer) valueAnimator.getAnimatedValue();
                    //动态更新view的高度
                    rl_content_layout.getLayoutParams().height = h;
                    rl_content_layout.requestLayout();
                }
            });
            va.setDuration(300);
            //开始动画
            va.start();
            return va;
        }
        return null;
    }

    public final static int ENTER_FROM_LEFT = 0;
    public final static int ENTER_FROM_RIGHT = 1;
    public static int CURR_ANIMATION_TYPE = ENTER_FROM_LEFT;

    public static Animation getAnimation(Context context, boolean enter) {
        return getAnimation(context, enter, CURR_ANIMATION_TYPE);
    }

    /**
     * @param context
     * @param enter          判断是否是进入场景
     * @param animation_type
     * @return
     */
    public static Animation getAnimation(Context context, boolean enter, int animation_type) {
        Animation animation = null;
        switch (animation_type) {
            case ENTER_FROM_LEFT:
                animation = AnimationUtils.loadAnimation(context, enter ? R.anim.enter_from_left : R.anim.exit_to_right);
                break;
            case ENTER_FROM_RIGHT:
                animation = AnimationUtils.loadAnimation(context, enter ? R.anim.enter_from_right : R.anim.exit_to_left);
                break;
            default:
                break;
        }
        return animation;
    }
}
