package com.androidfix.hongtao.test_modeule;

/**
 * Created by hongtao on 2018/4/13.
 */

public class WomanPerson {
    {
        System.out.print("不是方法");
    }
    static{
        System.out.print("WomanPerson static方法");
    }
    public WomanPerson(){
        System.out.print("WomanPerson 构造方法");
    }
}
