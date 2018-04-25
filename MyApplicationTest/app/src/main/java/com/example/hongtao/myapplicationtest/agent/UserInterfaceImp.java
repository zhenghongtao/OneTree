package com.example.hongtao.myapplicationtest.agent;

/**
 * Created by hongtao on 17/6/7.
 */

public class UserInterfaceImp implements UserInterface {
    String username;
    public UserInterfaceImp(String username){
        this.username=username;
    }

    @Override
    public String getName() {
        return username;
    }
}
