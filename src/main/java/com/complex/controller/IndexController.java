package com.complex.controller;

import com.alibaba.fastjson.JSONObject;
import com.complex.datacenter.Core;
import com.complex.entity.Job;
import com.complex.service.TaskService;
import com.complex.utils.AJAXReturn;
import com.complex.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private TaskService taskService;



    @GetMapping()
    public Object loadConsoleIndex(){
        return "index";
    }

    @ResponseBody
    @PostMapping("start/jobs")
    public Object loadDatabaseIndex(@RequestBody String jsonData){
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        int num = Integer.parseInt(String.valueOf(param.get("num")));
        Core core= new Core();
//        AJAXReturn ajaxReturn = core.STARTWORK(core,num);
//        if(ajaxReturn.getErrcode()==0){
//            String object = String.valueOf(ajaxReturn.getData());
//            HashMap job = JSONObject.parseObject(object, HashMap.class);
//            System.out.println(job);
//            taskService.insertJobs(job);
//        }
        return AJAXReturn.success();
    }
}
