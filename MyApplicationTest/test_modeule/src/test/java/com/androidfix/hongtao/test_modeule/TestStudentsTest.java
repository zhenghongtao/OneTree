package com.androidfix.hongtao.test_modeule;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hongtao on 2018/3/18.
 */
public class TestStudentsTest {
    @Test
    public void tests() throws Exception {
        Studens mStudens=new Studens(4);
        LaoShi mlaoshi1=new LaoShi(mStudens);
        mlaoshi1.ondestory();
        LaoShi mlaoshi2=new LaoShi(mStudens);
        mlaoshi2.ondestory();
    }

}