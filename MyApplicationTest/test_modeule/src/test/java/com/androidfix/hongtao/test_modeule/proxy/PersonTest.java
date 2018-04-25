package com.androidfix.hongtao.test_modeule.proxy;

/**
 * Created by hongtao on 2018/3/18.
 */
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;
public class PersonTest {
    public void test(){
        ManPersion pDao = new ManPersion();
        PersonHandler handler = new PersonHandler(pDao);

        Person proxy = (Person) Proxy.newProxyInstance(pDao.getClass().getClassLoader(), pDao.getClass().getInterfaces(), handler);
        proxy.say();

    }
}
