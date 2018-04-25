package com.androidfix.hongtao.test_modeule.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hongtao on 2018/3/18.
 */

public class PersonHandler implements InvocationHandler { private Object obj;

    public PersonHandler(Object obj){
        this.obj=obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        System.out.println("before");
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }

}
