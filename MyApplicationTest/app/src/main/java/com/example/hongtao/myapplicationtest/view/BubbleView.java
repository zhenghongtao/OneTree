package com.example.hongtao.myapplicationtest.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongtao.myapplicationtest.R;

/**
 * Created by hongtao on 16/8/30.
 */
public class BubbleView  extends LinearLayout{

    LinearLayout layout;
    public BubbleView(Context context){
        super(context);
    }
    public BubbleView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initlayout(context);
        bindEvents();
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
    View ll_content,l_hand_info_order_car;
    TextView tv_hand_info_time,tv_hand_info_time_tip,tv_loading_right,tv_onclick_yongche;
    ImageView pd_loading_left,iv_arrow_right;

    private void initlayout(Context context){
        if(layout==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.bubble_layout,this);
        }
        ll_content=layout.findViewById(R.id.ll_content);
        tv_hand_info_time= (TextView) layout.findViewById(R.id.tv_hand_info_time);
        tv_hand_info_time_tip= (TextView) layout.findViewById(R.id.tv_hand_info_time_tip);
        pd_loading_left= (ImageView) layout.findViewById(R.id.pd_loading_left);
        tv_loading_right= (TextView) layout.findViewById(R.id.tv_loading_right);
        tv_onclick_yongche= (TextView) layout.findViewById(R.id.tv_onclick_yongche);
        iv_arrow_right= (ImageView) layout.findViewById(R.id.iv_arrow_right);
    }

    /**
     * 现在loading 图片，和加载更多状态
     */
    public void initLoading(){
        l_hand_info_order_car.setVisibility(View.GONE);
        tv_hand_info_time.setVisibility(View.GONE);
        tv_hand_info_time_tip.setVisibility(View.GONE);
        tv_loading_right.setVisibility(VISIBLE);
        pd_loading_left.setVisibility(VISIBLE);
    }
    /**
     * 现在loading 图片，和加载更多状态
     */
    public void initTimesAndClick(){
        tv_loading_right.setVisibility(GONE);
        pd_loading_left.setVisibility(GONE);
        l_hand_info_order_car.setVisibility(View.VISIBLE);
        tv_hand_info_time.setVisibility(View.VISIBLE);
        tv_hand_info_time_tip.setVisibility(View.VISIBLE);
    }


    /**
     * 设置字体颜色
     * @param color
     */
    public void setTextFontColor(int color){
        tv_hand_info_time.setTextColor(color);
        tv_hand_info_time_tip.setTextColor(color);
        tv_loading_right.setTextColor(color);
        tv_onclick_yongche.setTextColor(color);
    }

    /**
     * 设置图片
     */
    public void setImageBackGround(String[] button,String button_top ){

    }

    private AnimatorSet alphaAnimatorSet;

    private void bindEvents() {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(tv_hand_info_time, "alpha", 1.0f, 0.3f, 1.0f);
        alphaAnimator.setRepeatCount(1000);
        ObjectAnimator alphaAnimatorTip = ObjectAnimator.ofFloat(tv_hand_info_time_tip, "alpha", 1.0f, 0.3f, 1.0f);
        alphaAnimatorTip.setRepeatCount(1000);
        alphaAnimatorSet = new AnimatorSet();
        alphaAnimatorSet.setDuration(2000);
        alphaAnimatorSet.playTogether(alphaAnimator, alphaAnimatorTip);
    }

}
