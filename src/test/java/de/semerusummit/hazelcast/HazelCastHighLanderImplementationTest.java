package de.semerusummit.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

@Test
public class HazelCastHighLanderImplementationTest {

    private SingleResourceReader reader;

    @BeforeMethod
    public void setup() throws Exception {
        File tempFile = SingleResourceReaderTest.createATempFile();
        reader = new SingleResourceReader(tempFile.getAbsolutePath());
    }

    public void testHazelCastHighlanderImplementation() throws Exception{
        String result = new HazelCastHighLanderImplementation().runOnASingleNode(reader);
        assertThat(result).isEqualTo(SingleResourceReaderTest.FILE_CONTENT);
    }

    public void testMultipleHazelCastInstances() throws Exception{
        // starting a new one
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        // starting another
        String result = new HazelCastHighLanderImplementation().runOnASingleNode(reader);
        assertThat(result).isEqualTo(SingleResourceReaderTest.FILE_CONTENT);
    }
}
