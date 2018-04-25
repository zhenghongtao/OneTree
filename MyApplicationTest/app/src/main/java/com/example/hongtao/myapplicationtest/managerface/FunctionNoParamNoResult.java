package com.example.hongtao.myapplicationtest.managerface;

/**
 * Created by hongtao on 17/10/13.
 */

public abstract class FunctionNoParamNoResult extends Function {
    public FunctionNoParamNoResult(String function_name) {
        super(function_name);
    }
    public abstract void function();
}
