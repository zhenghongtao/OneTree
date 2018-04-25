package com.example.hongtao.myapplicationtest.yidao;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.hongtao.myapplicationtest.R;


public class CoverFlowTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.3f;
    private static final float MIN_ALPHA = 0.3f;
    private int visibleIndex;
    private int spacing;
    private int selectedSpacing;
    private float alphaFactor;
    private float scaleFactor;

    public CoverFlowTransformer(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoverFlowPager, 0, 0);
        spacing = typedArray.getDimensionPixelSize(R.styleable.CoverFlowPager_listSpacing, 0);
        selectedSpacing = typedArray.getDimensionPixelSize(R.styleable.CoverFlowPager_selectedSpacing, 0);
        visibleIndex = typedArray.getInt(R.styleable.CoverFlowPager_visibleIndex, 2);
        alphaFactor = typedArray.getFloat(R.styleable.CoverFlowPager_alphaFactor, 0f);
        scaleFactor = typedArray.getFloat(R.styleable.CoverFlowPager_scaleFactor, 0f);

        typedArray.recycle();
    }

    @Override
    public void transformPage(View view, float position) {
        if (position < -visibleIndex || position > visibleIndex) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);

            if (spacing != 0) {
                float hMargin = position * (spacing);
                if (selectedSpacing != 0) {
                    float ss = Math.min(selectedSpacing, Math.abs(position * selectedSpacing));
                    hMargin += (position > 0) ? ss : -ss;
                }
                System.out.println(hMargin);
                view.setTranslationX(hMargin);
            }

            if (scaleFactor != 0f) {
                float scale = Math.max(MIN_SCALE, 1 - Math.abs(position * scaleFactor));
                view.setScaleX(scale);
                view.setScaleY(scale);
            }

            if (alphaFactor != 0f) {
                view.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position * alphaFactor)));
            }
        }
    }
}
