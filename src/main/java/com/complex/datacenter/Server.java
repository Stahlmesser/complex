package com.complex.datacenter;


import com.complex.entity.Job;
import com.complex.utils.RandomID;

import java.util.*;

public class Server {
    protected Socket[] sockets;
    protected HashMap<Job, Socket> jobToSocketMap;
    protected LinkedList<Job> jobQueue;
    protected int totalJobs;
    protected int jobsInServer;

    protected Experiment experiment;

    public void print(){
        for(Socket socket:sockets){
            socket.print();
        }

        for(Job job:jobQueue){
            job.print();
        }

        System.out.println("totalJobs"+totalJobs);
        System.out.println("jobsInServer"+jobsInServer);

        Iterator iterator=jobToSocketMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry) iterator.next();
            Object keyOfJob=entry.getKey();
            Object valueOfSocket=entry.getValue();
            System.out.println(keyOfJob+":"+valueOfSocket);
        }
    }

    public Server(final int numberOfSockets,
                  final int numberOfCoresPerSocket){
        this.jobQueue=new LinkedList<Job>();
        this.sockets=new Socket[numberOfSockets];
        for (int i = 0; i < numberOfSockets; i++) {
            this.sockets[i] = new Socket(numberOfCoresPerSocket);
        }
        this.totalJobs=0;
        this.jobToSocketMap=new HashMap<Job, Socket>();
    }

    public void insertJob(final double time, final Job job) throws InterruptedException {
        if (this.getRemainingCapacity() > 0) {
            this.jobQueue.add(job);
        }else{
            this.process(time, job);
        }
        this.totalJobs++;
    }


    public void process2(final double time, final List<Job> jobList) throws InterruptedException {
        Socket targetSocket = null;
        Socket mostUtilizedSocket = null;
        double highestUtilization = Double.MIN_VALUE;
        Socket leastUtilizedSocket = null;
        double lowestUtilization = Double.MAX_VALUE;

        for (int i = 0; i < this.sockets.length; i++) {
            Socket currentSocket = this.sockets[i];

            double currentUtilization = currentSocket.getInstantUtilization();
            System.err.println("currentUtilization1111-->"+currentUtilization);
            if (currentUtilization > highestUtilization
                    && currentSocket.getRemainingCapacity() > 0) {
                highestUtilization = currentUtilization;
                mostUtilizedSocket = currentSocket;
            }

            if (currentUtilization < lowestUtilization
                    && currentSocket.getRemainingCapacity() > 0) {
                lowestUtilization = currentUtilization;
                leastUtilizedSocket = currentSocket;
            }
        }
        targetSocket = leastUtilizedSocket;
        for (int i=0;i<jobList.size();i++){
            targetSocket.addJob(time, jobList.get(i));
            this.jobToSocketMap.put( jobList.get(i), targetSocket);
        }
        double currentUtilization = targetSocket.getInstantUtilization();
        System.err.println("currentUtilization222-->"+currentUtilization);
        if(targetSocket.getRemainingCapacity()>0){
            targetSocket.process(System.currentTimeMillis()/1000);
        }
        double currentUtilization2 = targetSocket.getInstantUtilization();
        System.err.println("currentUtilization333-->"+currentUtilization2);

    }
    public void process(final double time, final Job job) throws InterruptedException {
        Socket targetSocket = null;
        Socket mostUtilizedSocket = null;
        double highestUtilization = Double.MIN_VALUE;
        Socket leastUtilizedSocket = null;
        double lowestUtilization = Double.MAX_VALUE;

        for (int i = 0; i < this.sockets.length; i++) {
            Socket currentSocket = this.sockets[i];

            double currentUtilization = currentSocket.getInstantUtilization();
            System.err.println("currentUtilization1111-->"+currentUtilization);
            if (currentUtilization > highestUtilization
                    && currentSocket.getRemainingCapacity() > 0) {
                highestUtilization = currentUtilization;
                mostUtilizedSocket = currentSocket;
            }

            if (currentUtilization < lowestUtilization
                    && currentSocket.getRemainingCapacity() > 0) {
                lowestUtilization = currentUtilization;
                leastUtilizedSocket = currentSocket;
            }
        }
        targetSocket = leastUtilizedSocket;
        targetSocket.addJob(time, job);
        double currentUtilization = targetSocket.getInstantUtilization();
        System.err.println("currentUtilization222-->"+currentUtilization);
        this.jobToSocketMap.put(job, targetSocket);
        if(targetSocket.getRemainingCapacity()>0){
            targetSocket.process(System.currentTimeMillis()/1000);
        }
        double currentUtilization2 = targetSocket.getInstantUtilization();
        System.err.println("currentUtilization333-->"+currentUtilization2);

    }

    public double getInstantUtilization() {
        double avg = 0.0d;
        for (int i = 0; i < this.sockets.length; i++) {
            avg += this.sockets[i].getInstantUtilization();
        }
        avg /= this.sockets.length;
        return avg;
    }

    public final int getRemainingCapacity() {
        int capacity = 0;
        for (int i = 0; i < this.sockets.length; i++) {
            capacity += this.sockets[i].getRemainingCapacity();
        }
        return capacity;
    }

    public final int getTotalCapacity() {
        int nJobs = 0;
        for (int i = 0; i < this.sockets.length; i++) {
            nJobs += this.sockets[i].getTotalCapacity();
        }
        return nJobs;
    }

    public int getJobsInService() {
        int nInService = 0;
        for (int i = 0; i < this.sockets.length; i++) {
            nInService += this.sockets[i].getJobsInService();
        }
        return nInService;
    }

    public double getPower() {
        double power = 0.0;
        for (int i = 0; i < this.sockets.length; i++) {
            power += this.sockets[i].getPower();
        }
       return power;
    }

    public static void main(String[] args) throws InterruptedException {
        Job job=new Job(4);
        Job job2=new Job(2);
        Server server=new Server(2,3);
        int num=2;
        for (int i=0;i<num;i++){
//            Job job2=new Job(RandomID.genID());

        }
//        server.getInstantUtilization();
//        server.process(System.currentTimeMillis()/1000,job);
//        server.process(System.currentTimeMillis()/1000,job2);
    }
}
