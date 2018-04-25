package com.example.hongtao.myapplicationtest.yidao;

import android.app.Activity;
import android.view.View;

import java.util.List;

/**
 * Created by hongtao on 17/2/17.
 */

public class HomePagePresenter {
    IHomePageView mIHomePageView;
    Activity mActivity;
    public HomePagePresenter(IHomePageView mIHomePageView, Activity mActivity){
        this.mActivity=mActivity;
        this.mIHomePageView=mIHomePageView;
    }
    public void initData(){
        addLeftMenuView(null);
        addRightMenuView(null);
    }

    /**
     * 添加左边的菜单menu
     * @param list
     */
    public void addLeftMenuView(List<View> list){
        mIHomePageView.getLeftMenuView().testaddManyView();
        //mIHomePageView.getLeftMenuView().addManyViews(list);
    }
    /**
     * 添加右边的菜单menu
     * @param list
     */
    public void addRightMenuView(List<View> list){
        mIHomePageView.getRightMenuView().testaddManyView();
        //mIHomePageView.getRightMenuView().addManyViews(list);
    }
}
