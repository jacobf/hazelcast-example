package de.semerusummit.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

import java.util.concurrent.Future;

public class HazelCastHighLanderImplementation {

    public String runOnASingleNode(SingleResourceReader reader) throws Exception{
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IExecutorService executorService = hz.getExecutorService("default");
        Future<String> future = executorService.submit(reader);
        return future.get();
    }
}
