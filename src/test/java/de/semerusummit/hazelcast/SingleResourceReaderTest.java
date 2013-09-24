package de.semerusummit.hazelcast;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.fest.assertions.Assertions.assertThat;

@Test
public class SingleResourceReaderTest {

    private static final String FILE_PREFIX = "HAZELCAST";
    public static final String FILE_CONTENT = "This is the content of my hazelcast test case!";

    private SingleResourceReader reader;

    @BeforeMethod
    public void setup() throws Exception {
        File tempFile = createATempFile();
        reader = new SingleResourceReader(tempFile.getAbsolutePath());
    }

    public void testReadAFileFrom() throws Exception {
        String result = reader.call();
        assertThat(result).isEqualTo(FILE_CONTENT);
    }

    public static File createATempFile() throws IOException {
        File tempFile = File.createTempFile(FILE_PREFIX, "" + System.currentTimeMillis());
        BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath(), Charset.defaultCharset());
        writer.append(FILE_CONTENT);
        writer.flush();
        return tempFile;
    }
}
