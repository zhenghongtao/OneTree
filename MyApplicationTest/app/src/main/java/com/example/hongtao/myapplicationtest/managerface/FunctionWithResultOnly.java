package com.example.hongtao.myapplicationtest.managerface;

/**
 * Created by hongtao on 17/10/13.
 */

public abstract class FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String function_name) {
        super(function_name);
    }
    public abstract Result function();
}
