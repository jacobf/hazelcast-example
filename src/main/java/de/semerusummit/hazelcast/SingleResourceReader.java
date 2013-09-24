package de.semerusummit.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class SingleResourceReader implements Callable<String>, Serializable, HazelcastInstanceAware {

    private final String externalFilePath;
    private HazelcastInstance hazelcastInstance;

    public SingleResourceReader(String externalFilePath) {
        this.externalFilePath = externalFilePath;
    }

    private String processFile() throws Exception {
        return new Scanner(new File(externalFilePath)).useDelimiter("\\Z").next();
    }

    @Override
    public String call() {
        try {
            String member = hazelcastInstance.getCluster().getLocalMember().toString();
            System.out.println(member);
            return processFile();
        } catch (Exception e) {
            e.printStackTrace();
            return "There was a problem calling processFile()";
        }
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
}
