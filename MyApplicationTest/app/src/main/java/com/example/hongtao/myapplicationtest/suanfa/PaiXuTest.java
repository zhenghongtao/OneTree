package com.example.hongtao.myapplicationtest.suanfa;

import static android.R.attr.key;

/**
 * Created by hongtao on 17/6/15.
 * 快速排序算法，递归算法
 */

public class PaiXuTest {
    public int getMiddle(int[] numbers,int low,int height){
        int temp=numbers[low];
        while(low<height){
            while(low<height && numbers[height]>=temp){
                height--;
            }
            numbers[low]=numbers[height];
            while (low<height && numbers[low]<=temp){
                low++;
            }
            numbers[height]=numbers[low];
        }
        numbers[height]=temp;
        return low;
    }
    public void quickSort(int[] numbers,int low,int height){
        if(low<height){
            int middle=getMiddle(numbers,low,height);
            quickSort(numbers,0,middle-1);
            quickSort(numbers,middle+1,numbers.length);
        }
    }
}
