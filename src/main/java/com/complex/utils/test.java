package com.complex.utils;

import com.complex.datacenter.Core;

import java.util.HashMap;

public class test {

    public static void main(String[] args) {
//        Core core= new Core();
//        AJAXReturn ajaxReturn = new AJAXReturn();
//        ajaxReturn = core.STARTWORK(core);
//        if(ajaxReturn.getErrcode()==0){
//            System.out.println(ajaxReturn.getErrcode());
//            System.out.println(ajaxReturn.getErrmsg());
//            System.out.println(ajaxReturn.getData());
//
//        }
        test2 y = new test2();
        AJAXReturn ajaxReturn = y.tesssss();
        HashMap a = JsonUtils.objectToMap("Job(startTime=1.637277972369E12, startDate=2021-11-18 23:26:12.37, finishDate=2021-11-18 23:26:12.37, finishTime=1.637277972372E12, jobId=0, jobSize=5.0)");
        System.out.println(a);
        System.out.println(ajaxReturn.getErrmsg());
        System.out.println(ajaxReturn.getData());
    }
}
