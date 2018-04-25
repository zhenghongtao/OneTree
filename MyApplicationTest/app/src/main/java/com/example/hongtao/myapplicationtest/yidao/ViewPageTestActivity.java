package com.example.hongtao.myapplicationtest.yidao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hongtao.myapplicationtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hongtao on 17/2/9.
 */

public class ViewPageTestActivity extends Activity implements MenuHorizontalScrollView.OnViewChangedListener {

    @BindView(R.id.top_layout)
    ImageView top_layout;
    @BindView(R.id.scroll_test)
    MenuHorizontalScrollView scrollTest;

    @BindView(R.id.left_layout_view)
    VerticalMenuView leftLayoutView;
    @BindView(R.id.center_layout_view)
    CardView centerLayoutView;
    @BindView(R.id.rigth_layout_view)
    CardView rigthLayoutView;

    public void initLeftMargin(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int windowwidth = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams mleftLayoutParams= (LinearLayout.LayoutParams) leftLayoutView.getLayoutParams();
        mleftLayoutParams.leftMargin=(windowwidth-mleftLayoutParams.width)/2;
        leftLayoutView.setLayoutParams(mleftLayoutParams);

        LinearLayout.LayoutParams mrightLayoutParams= (LinearLayout.LayoutParams) rigthLayoutView.getLayoutParams();
        mrightLayoutParams.rightMargin=(windowwidth-mleftLayoutParams.width)/2;
        rigthLayoutView.setLayoutParams(mrightLayoutParams);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage);
        ButterKnife.bind(this);
        initLeftMargin();
        scrollTest.setOnViewChangedListener(this);
        scrollTest.setSmoothScrollingEnabled(true);
        scrollTest.moveToCenter();
        leftLayoutView.testaddManyView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        scrollTest.moveToCenter();
    }

    @Override
    public void firstViewShow() {

    }

    @Override
    public void secondViewShow() {

    }

    @Override
    public void endViewShow() {

    }


    @Override
    public void startFirstShowAnimation(float n) {
        leftLayoutView.startAnimation(n);
    }

    @Override
    public void startEndShowAnimation(float n) {

    }


}
