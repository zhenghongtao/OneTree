package com.example.hongtao.myapplicationtest.yidao;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class VelocityViewPager extends ViewPager {
    public VelocityViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new CoverFlowTransformer(context, attrs));
    }
}