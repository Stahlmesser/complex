package com.complex.service;


import com.complex.entity.Job;
import com.complex.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;


    public void insertJobs(HashMap job) {
        taskMapper.insertJobs(job);
    }
}
