package com.example.hongtao.myapplicationtest.agent;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hongtao on 17/6/7.
 */

public class UserProxyhandler implements InvocationHandler {
    private Object obejcetclass;
    public UserProxyhandler(Object obejcetclass){
        this.obejcetclass=obejcetclass;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("zhenghongtao","proxy="+proxy.getClass().getName());
        Log.e("zhenghongtao","method="+method.getName());
        Log.e("zhenghongta","before invoke");
        Object object=method.invoke(obejcetclass,args);
        Log.e("zhenghongta","after invoke");
        return object;
    }
}
