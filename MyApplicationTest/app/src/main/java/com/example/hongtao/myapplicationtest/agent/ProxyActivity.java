package com.example.hongtao.myapplicationtest.agent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hongtao.myapplicationtest.R;

import java.lang.reflect.Proxy;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserInterfaceImp muser=new UserInterfaceImp("张三");
        UserProxyhandler  ih=new UserProxyhandler(muser);

        UserInterface userInterface= (UserInterface) Proxy.newProxyInstance(muser.getClass().getClassLoader(),muser.getClass().getInterfaces(),ih);
        String name=userInterface.getName();
        Log.e("zhenghongta","name："+name);

    }
}
