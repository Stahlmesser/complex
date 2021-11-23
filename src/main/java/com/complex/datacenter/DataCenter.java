package com.complex.datacenter;

import com.complex.entity.Job;

import java.util.LinkedList;
import java.util.Vector;
import java.util.Iterator;

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
            Server server_=new Server(2,3);
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
        Job job=new Job(4);
        Job job2=new Job(2);
        Server server=new Server(2,3);
        server.process(System.currentTimeMillis()/1000,job);
        server.process(System.currentTimeMillis()/1000,job2);
    }
}
