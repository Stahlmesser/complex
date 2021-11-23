//
//package com.complex.entity;
//
//public class Job {
//    private double startTime;
//    private String startDate;
//    private String finishDate;
//    private double finishTime;
//    private long jobId;
//    private double jobSize;
//    private static long currentId;
//
//    public void print(){
//        System.out.println("jobId:"+jobId);
//        System.out.println("jobSize:"+jobSize);
//        System.out.println("startDate:"+startDate);
//        System.out.println("finishDate:"+finishDate);
//        System.out.println("startTime:"+startTime);
//        System.out.println("finishTime:"+finishTime);
//    }
//
//    private long assignId() {
//        long toReturn = Job.currentId;
//        Job.currentId++;
//        return toReturn;
//    }
//
//    //constructor
//    public Job(final double theJobSize) {
//        this.jobSize = theJobSize;
//        this.jobId = assignId();
//    }
//
//    public void setFinishDate(String finishDate){
//        this.finishDate=finishDate;
//    }
//
//    public void setStartDate(String startDate){
//        this.startDate=startDate;
//    }
//
//    public final void markStart(final double time) {
//        this.startTime = time;
//    }
//
//    public final void markFinish(final double time) {
//        this.finishTime = time;
//    }
//
//    public final double getJobSize(){
//        return this.jobSize;
//    }
//
//    public final long getJobId(){
//        return this.jobId;
//    }
//}
