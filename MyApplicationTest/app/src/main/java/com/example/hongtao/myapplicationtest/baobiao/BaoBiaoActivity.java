package com.example.hongtao.myapplicationtest.baobiao;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hongtao.myapplicationtest.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BaoBiaoActivity extends Activity {

    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_biao);
        ButterKnife.bind(this);
    }

    final int DATA_COUNT = 5;

    private List<Entry> getChartData() {
        List<Entry> chartData = new ArrayList<>();
        for (int i = 0; i < DATA_COUNT; i++) {
            chartData.add(new Entry(i * 2, i));
        }
        return chartData;
    }

    private ArrayList<String> getLabels() {
        ArrayList<String> chartLabels = new ArrayList<String>();
        for (int i = 0; i < DATA_COUNT; i++) {
            chartLabels.add("X" + i);
        }
        return chartLabels;
    }

    private LineData getLineData() {
        LineDataSet dataSetA = new LineDataSet((ArrayList<Entry>) getChartData(), "LabelA");

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(dataSetA); // add the datasets
        return new LineData(getLabels(), dataSetA);
    }

    public int dip2px(float dpValue) {
        return (int) (dpValue * getResources().getDisplayMetrics().density + 0.5f);
    }

    private void buildDraweableState() {
        float mRadius = dip2px(49);
        float outRectr[] = new float[]{mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius};
        //创建状态管理器
        int[] mNormalState = new int[]{};
        StateListDrawable drawable = new StateListDrawable();
        /**
         * 注意StateListDrawable的构造方法我们这里使用的
         * 是第一参数它是一个float的数组保存的是圆角的半径，它是按照top-left顺时针保存的八个值
         */
        //创建圆弧形状
        RoundRectShape rectShape = new RoundRectShape(outRectr, null, null);
        //创建drawable
        ShapeDrawable pressedDrawable = new ShapeDrawable(rectShape);
        //设置我们按钮背景的颜色
        pressedDrawable.getPaint().setColor(Color.BLACK);
        //添加到状态管理里面
        drawable.addState(mNormalState, pressedDrawable);

        //      ShapeDrawable disableDrawable = new ShapeDrawable(rectShape);
        //      disableDrawable.getPaint().setColor(prssedClor);
        //      disableDrawable.getPaint().setAlpha(125);
        //      drawable.addState(mDisableState, disableDrawable);

        ShapeDrawable normalDrawable = new ShapeDrawable(rectShape);
        normalDrawable.getPaint().setColor(Color.BLACK);
        drawable.addState(mNormalState, normalDrawable);
        //设置我们的背景，就是xml里面的selector
        llContent.setBackgroundDrawable(drawable);
    }
}
