package com.androidfix.hongtao.test_modeule;

/**
 * Created by hongtao on 2018/3/18.
 */

public class Studens extends WomanPerson {
    int ages;
     public Studens(int ages){
         this.ages=ages;
     }
     public void show(){
         System.out.print("年龄是"+ages+"岁了");
     }
    {
        System.out.print("Studens 不是方法");
    }
    static{
        System.out.print("Studens static方法");
    }
    public Studens(){
        System.out.print("Studens 构造方法");
    }
}
