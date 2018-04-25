package com.androidfix.hongtao.test_modeule.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by hongtao on 2018/3/18.
 */
public class PersonTestTest {
    @Test
    public void test1() throws Exception {
        ManPersion pDao = new ManPersion();
        PersonHandler handler = new PersonHandler(pDao);

        Person proxy = (Person) Proxy.newProxyInstance(pDao.getClass().getClassLoader(), pDao.getClass().getInterfaces(), handler);
        proxy.say();
        Object str=new Object();
        change(str);
        System.out.print("测试："+str.hashCode());
    }

    public static void  change(Object str){
        str=new Object();
        System.out.print("change 测试："+str.hashCode()+"/n");
    }

}