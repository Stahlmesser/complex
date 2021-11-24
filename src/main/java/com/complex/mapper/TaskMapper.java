package com.complex.mapper;

import com.complex.entity.Job;

import java.util.HashMap;
import java.util.List;

public interface TaskMapper {


    void insertJobs(HashMap job);

    void insertCPU(HashMap keyset);

    List<String> queryAllCPUTime(HashMap map);

    List<Integer> queryAllCPU(HashMap map);
}
