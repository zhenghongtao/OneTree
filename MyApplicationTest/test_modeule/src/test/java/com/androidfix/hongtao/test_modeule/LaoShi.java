package com.androidfix.hongtao.test_modeule;

/**
 * Created by hongtao on 2018/3/18.
 */

public class LaoShi  {
    Studens mStudens;
    public LaoShi( Studens mStudens){
        this.mStudens=mStudens;
    }
    public void ondestory(){
        mStudens.show();
        mStudens=null;
    }
}
