package com.example.hongtao.myapplicationtest.yidao;

import android.support.v4.view.ViewPager;

/**
 * Created by hongtao on 17/2/17.
 */

public interface IHomePageView {

    VerticalMenuView getLeftMenuView();
    VerticalMenuView getRightMenuView();
    ViewPager getViewPage();
}
