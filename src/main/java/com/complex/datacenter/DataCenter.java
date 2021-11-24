package com.complex.datacenter;

import com.complex.entity.Job;
import com.complex.utils.RandomID;

import java.util.*;

public class DataCenter {
    private Vector<Server> servers;
    private LinkedList<Job> jobs;

    public DataCenter() {
        this.servers = new Vector<Server>();
        this.jobs=new LinkedList<Job>();
    }

    public void addServer(final Server server) {
        this.servers.add(server);
    }

    public Vector<Server> getServers() {
        return this.servers;
    }

    public void initializtaion() {
        int nServers = 5;
        for(int i = 0; i < nServers; i++) {
            Server server_=new Server(2,2);
            this.addServer(server_);
        }
    }

    public void insertJobs(final double time,Job job){
        this.jobs.add(job);
    }

    public void allocateJobs(final double time) throws InterruptedException {
        for(Job job:jobs) {
            for (Server server : servers) {
                int remainingCapacity = server.getRemainingCapacity();
                if (remainingCapacity > 0 && server.getInstantUtilization() < 0.9) {
                    server.insertJob(time, job);
                }
            }
        }
        System.out.println(jobs.size()+"remains to be dealt with");
    }

    public void print(){
        Iterator<Server> iter=this.servers.iterator();
        while(iter.hasNext()){
            Server server=iter.next();
            server.print();
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataCenter dataCenter = new DataCenter();
        Server server=new Server(2,4);
        int num=2;
        List<Job> jobList = new ArrayList<>();
        for (int i=0;i<num;i++){
            Job job=new Job(RandomID.genID());
            jobList.add(job);
        }
        server.process2(System.currentTimeMillis()/1000,jobList);
        server.getInstantUtilization();
    }
}
