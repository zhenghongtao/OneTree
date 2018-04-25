package com.example.hongtao.myapplicationtest.yidao;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hongtao on 17/2/15.
 */

public class MainPageAnimatorUtils {
    /**
     * 页面进场动画
     * @param top_layout
     * @param viewpage
     * @param rightLayout
     */
    public static void pageGoInAnimator(View top_layout,View viewpage,View rightLayout){
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(200);
        ViewGroup.MarginLayoutParams olp = (ViewGroup.MarginLayoutParams) top_layout.getLayoutParams();

        float moveto = top_layout.getHeight() + olp.topMargin;
        ViewGroup.MarginLayoutParams viewpage_olp = (ViewGroup.MarginLayoutParams) viewpage.getLayoutParams();

        float viewpage_moveto = viewpage.getHeight() +viewpage_olp.bottomMargin;

        ViewGroup.MarginLayoutParams right_layout_olp = (ViewGroup.MarginLayoutParams) rightLayout.getLayoutParams();
        float right_layout_moveto = rightLayout.getWidth()+right_layout_olp.rightMargin;

        ObjectAnimator m4 = ObjectAnimator.ofFloat(top_layout, "translationY", -moveto, 0);
        ObjectAnimator m3 = ObjectAnimator.ofFloat(viewpage, "translationY", viewpage_moveto, 0);
        ObjectAnimator m2 = ObjectAnimator.ofFloat(rightLayout, "translationX", right_layout_moveto, 0);
        animSet.play(m4).with(m3).with(m2);
        animSet.start();
    }

    /**
     * 页面离场动画
     */
     public static void pageGoOutAnimator(View top_layout,View viewpage,View rightLayout){
         AnimatorSet animSet = new AnimatorSet();
         animSet.setDuration(200);
         ViewGroup.MarginLayoutParams olp = (ViewGroup.MarginLayoutParams) top_layout.getLayoutParams();
         float moveto = top_layout.getHeight() + olp.topMargin;
         ViewGroup.MarginLayoutParams viewpage_olp = (ViewGroup.MarginLayoutParams) viewpage.getLayoutParams();
         float viewpage_moveto = viewpage.getHeight() + viewpage_olp.bottomMargin;
         ViewGroup.MarginLayoutParams right_layout_olp = (ViewGroup.MarginLayoutParams) rightLayout.getLayoutParams();
         float right_layout_moveto = rightLayout.getWidth()+right_layout_olp.rightMargin;
         ObjectAnimator m4 = ObjectAnimator.ofFloat(top_layout, "translationY", 0, -moveto);
         ObjectAnimator m3 = ObjectAnimator.ofFloat(viewpage, "translationY", 0, viewpage_moveto);
         ObjectAnimator m2 = ObjectAnimator.ofFloat(rightLayout, "translationX", 0, right_layout_moveto);
         animSet.play(m4).with(m3).with(m2);
         animSet.start();
     }
    /**
     * viewpage 滑动离开center 页面 top_layout 和 rightLayout 动画
     */
    public static void  viewPageMoveAnimator(View top_layout,View rightLayout,View msgtitle,float n){
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(0);
        ViewGroup.MarginLayoutParams olp = (ViewGroup.MarginLayoutParams) top_layout.getLayoutParams();
        float moveto = top_layout.getHeight() + olp.topMargin;
        ViewGroup.MarginLayoutParams right_layout_olp = (ViewGroup.MarginLayoutParams) rightLayout.getLayoutParams();
        float right_layout_moveto = rightLayout.getWidth()+right_layout_olp.rightMargin;
        ViewGroup.MarginLayoutParams msgtitle_olp = (ViewGroup.MarginLayoutParams) msgtitle.getLayoutParams();
        float msgtitle_moveto = msgtitle.getWidth()+msgtitle_olp.leftMargin;

        ObjectAnimator m3 = ObjectAnimator.ofFloat(msgtitle, "translationX", msgtitle.getTranslationX(), -msgtitle_moveto*n);
        ObjectAnimator m4 = ObjectAnimator.ofFloat(top_layout, "translationY", top_layout.getTranslationY(), moveto*(n-1));
        ObjectAnimator m2 = ObjectAnimator.ofFloat(rightLayout, "translationX", rightLayout.getTranslationY(), right_layout_moveto*(1-n));
        animSet.play(m4).with(m3).with(m2);
        animSet.start();
    }
    /**
     * viewpage 滑动离开center 页面 top_layout 和 rightLayout 动画
     * 向右滑动的时
     */
    public static void  viewPageMoveAnimator(View top_layout,View rightLayout,float n){
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(0);
        ViewGroup.MarginLayoutParams olp = (ViewGroup.MarginLayoutParams) top_layout.getLayoutParams();
        float moveto = top_layout.getHeight() + olp.topMargin;
        ViewGroup.MarginLayoutParams right_layout_olp = (ViewGroup.MarginLayoutParams) rightLayout.getLayoutParams();
        float right_layout_moveto = rightLayout.getWidth()+right_layout_olp.rightMargin;
        ObjectAnimator m4 = ObjectAnimator.ofFloat(top_layout, "translationY", top_layout.getTranslationY(), moveto*(n-1));
        ObjectAnimator m2 = ObjectAnimator.ofFloat(rightLayout, "translationX", rightLayout.getTranslationY(), right_layout_moveto*(1-n));
        animSet.play(m4).with(m2);
        animSet.start();
    }
}
