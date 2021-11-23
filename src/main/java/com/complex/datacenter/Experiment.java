package com.complex.datacenter;

import com.complex.entity.Job;

public class Experiment {
    private long nEventsProcessed;
    private double currentTime;
    private String experimentName;
    private boolean stop;
    private DataCenter dataCenter;
    private long nEventsProccessed;

    public Experiment(final String theExperimentName,DataCenter dataCenter) {
        this.stop = false;
        this.currentTime = 0.0d;
        this.experimentName = theExperimentName;
        this.dataCenter = dataCenter;
    }

    public void run() {
        long startTime = System.currentTimeMillis()/1000;
        this.nEventsProccessed = 0;
        System.out.println("Starting simulation");
        Job job1=new Job(5);
        Job job2=new Job(5);
//        Iterator<Server> iter = dataCenter.getServers().iterator();
//        while (iter.hasNext()) {
//            iter.next().startJobService(startTime,job1);
//        }

    }


    public static void main(String[] args) {
        DataCenter dataCenter=new DataCenter();
        dataCenter.initializtaion();
        dataCenter.print();
        Experiment exp1=new Experiment("experiment1",dataCenter);
        exp1.run();
    }
}
