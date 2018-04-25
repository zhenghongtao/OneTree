package com.example.hongtao.myapplicationtest.fargments;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.example.hongtao.myapplicationtest.R;
import com.example.hongtao.myapplicationtest.managerface.FunctionManager;
import com.example.hongtao.myapplicationtest.managerface.FunctionWithParamAndResult;

/**
 * Created by hongtao on 17/10/13.
 */

public class YDHomePage extends FragmentActivity {

    FragmentManager fragmentManager;
    Button button;
    BaseFragment currbaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yd_home_layout);
        fragmentManager = getSupportFragmentManager();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currbaseFragment == yongCheFragment) {
                    BaseFragment.move_to_right = false;
                    replaceFragment(jieSongJiFragment);
                } else if (currbaseFragment == jieSongJiFragment) {
                    BaseFragment.move_to_right = true;
                    replaceFragment(yongCheFragment);
                }
            }
        });
        initFargments();
        initFunction();
        addFragment(yongCheFragment);
    }

    JieSongJiFragment jieSongJiFragment;
    JieSongZhanFragment jieSongZhanFragment;
    YongCheFragment yongCheFragment;

    /**
     *
     */
    public void initFargments() {
        jieSongJiFragment = new JieSongJiFragment();
        jieSongZhanFragment = new JieSongZhanFragment();
        yongCheFragment = new YongCheFragment();
    }

    private void addFragment(BaseFragment mbaseFragment) {
        if (mbaseFragment == null)
            return;
        if(currbaseFragment!=null) {
            fragmentManager.beginTransaction().hide(currbaseFragment).commit();
        }
        currbaseFragment = mbaseFragment;
        if (!mbaseFragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.content, mbaseFragment).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }else{
            fragmentManager.beginTransaction().show(currbaseFragment).commit();
        }
    }

    public void replaceFragment(BaseFragment mbaseFragment) {
        if (mbaseFragment == null)
            return;
        currbaseFragment = mbaseFragment;
        if (!mbaseFragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, mbaseFragment).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public void initFunction() {
        FunctionManager.getInstance().addFunctionWithParamAndResult(new FunctionWithParamAndResult<String,String>(YongCheFragment.GET_NAME) {
            @Override
            public String function(String o) {
                return "测试"+o;
            }
        });
    }
}
