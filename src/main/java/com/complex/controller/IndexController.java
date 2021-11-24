package com.complex.controller;

import com.alibaba.fastjson.JSONObject;
import com.complex.datacenter.Core;
import com.complex.entity.Job;
import com.complex.service.TaskService;
import com.complex.utils.AJAXReturn;
import com.complex.utils.JsonUtils;
import com.complex.utils.RandomID;
import com.complex.utils.TimeParse;
import com.github.abel533.echarts.DataZoom;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.CrossStyle;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private TaskService taskService;
    TimeParse timeParse = new TimeParse();


    @SneakyThrows
    @ResponseBody
    @PostMapping("load/cpu")
    public Object loadCPUIndex(@RequestBody String jsonData){
        jsonData = URLDecoder.decode(jsonData, "utf-8").replaceAll("=", "");
        HashMap<String, Object> map = JSONObject.parseObject(jsonData, HashMap.class);
        int pageNO = Integer.parseInt(map.get("pageno").toString());
        map.put("rows",50+pageNO);

        Option option =new Option();
        option.title("CPU utilization")
                .tooltip()
                .trigger(Trigger.axis)
                .axisPointer()
                .setType(PointerType.cross);

        Toolbox toolbox = new Toolbox().show(true);    //创建工具栏
        toolbox.feature(Tool.saveAsImage);
        option.setToolbox(toolbox);

        option.grid().left(3).right(4).bottom(3).containLabel(true);
        List legendName = new ArrayList();



        List<String> listTime =  taskService.queryAllCPUTime(map);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.boundaryGap(false);
        categoryAxis.setType(AxisType.category);
        categoryAxis.setData(listTime);
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis().type(AxisType.value),new ValueAxis().type(AxisType.value));

//        ValueAxis valueAxis = new ValueAxis();
//        valueAxis.axisLabel().formatter("{value} %");
//        valueAxis.type(AxisType.value);
//        option.yAxis(valueAxis);



//        List<Series> temp = new ArrayList<>();
        List temp2 = new ArrayList<>();
        for(int i=1;i<=3;i++){
            map.put("cid",i);
            Line line = new Line();
            HashMap series = new HashMap<>();
            List<Integer> list =  taskService.queryAllCPU(map);
            line.name("CPU"+i).type(SeriesType.line)
                    .stack("Total")
                    .setData(list);
            legendName.add("CPU"+i);
//            temp.add(line);
            series.put("name","CPU"+i);
            series.put("type","line");
//            series.put("areaStyle","{}");

//            series.put("stack","Total");
            series.put("data",list);
            temp2.add(series);


        }
//        option.series(temp);
        option.legend(legendName);

        HashMap retuen = new HashMap<>();
        retuen.put("legendT",legendName);
        retuen.put("xAxisT",listTime);
        retuen.put("seriesT",temp2);

        return AJAXReturn.success(retuen);
    }



    /*
    * desc  generate fake cpu utilization
    * */
    @SneakyThrows
    @ResponseBody
    @GetMapping ("simulation/cpu")
    public Object Simulation(){
//        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        for(int i=1;i<=100;i++){
            String Time = timeParse.convertDate2String(new Date(),
                    "yyyy-MM-dd HH:mm:ss");

            for(int k=1;k<=4;k++){
                Thread.sleep(500);
                HashMap keyset = new HashMap();
                double utilization =0.0;
                if(i<=20){
                    utilization  = RandomID.getCPUNum();
                }else if(i>20&&i<=40) {
                    utilization  = RandomID.getCPUNum2();
                }else if(i>40&&i<=60) {
                    utilization  = RandomID.getCPUNum3();
                }else if(i>60&&i<=80) {
                    utilization  = RandomID.getCPUNum4();
                }else if(i>80&&i<=100) {
                    utilization  = RandomID.getCPUNum5();
                }
//                int cid= RandomID.getCPUID();
                keyset.put("cid",k);
                keyset.put("utilization",utilization);
                keyset.put("cdate",Time);
                taskService.insertCPU(keyset);
            }
        }
        return AJAXReturn.success();
    }

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
