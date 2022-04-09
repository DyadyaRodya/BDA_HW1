import bdlc.lab1.CounterType;
import bdlc.lab1.HW1Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class CountersTest {

    private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

    private final String testUnknownLevel = "best test text";
    private final String testDebug = "bla bla [debug] bla bla";
    private final String testInfo = "bla bla [info] bla bla";
    private final String testNotice = "bla bla [notice] bla bla";
    private final String testWarning = "bla bla [warning] bla bla";
    private final String testError = "bla bla [error] bla bla";
    private final String testCrit = "bla bla [crit] bla bla";
    private final String testAlert = "bla bla [alert] bla bla";
    private final String testEmerg = "bla bla [emerg] bla bla";
    private final String testPanic = "bla bla [panic] bla bla";

    @Before
    public void setUp() {
        HW1Mapper mapper = new HW1Mapper();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapperCounterOne() throws IOException  {
        mapDriver
                .withInput(new LongWritable(), new Text(testUnknownLevel))
                .runTest();
        assertEquals("Expected 1 counter increment", 1, mapDriver.getCounters()
                .findCounter(CounterType.UNKNOWN).getValue());
    }

    @Test
    public void testMapperCounterZero() throws IOException {
        mapDriver
                .withInput(new LongWritable(), new Text(testDebug))
                .withInput(new LongWritable(), new Text(testInfo))
                .withInput(new LongWritable(), new Text(testNotice))
                .withInput(new LongWritable(), new Text(testWarning))
                .withInput(new LongWritable(), new Text(testError))
                .withInput(new LongWritable(), new Text(testCrit))
                .withInput(new LongWritable(), new Text(testAlert))
                .withInput(new LongWritable(), new Text(testEmerg))
                .withInput(new LongWritable(), new Text(testPanic))
                .withOutput(new Text("debug"), new IntWritable(1))
                .withOutput(new Text("info"), new IntWritable(1))
                .withOutput(new Text("notice"), new IntWritable(1))
                .withOutput(new Text("warning"), new IntWritable(1))
                .withOutput(new Text("error"), new IntWritable(1))
                .withOutput(new Text("crit"), new IntWritable(1))
                .withOutput(new Text("alert"), new IntWritable(1))
                .withOutput(new Text("emerg"), new IntWritable(1))
                .withOutput(new Text("emerg"), new IntWritable(1))
                .runTest();
        assertEquals("Expected 1 counter increment", 0, mapDriver.getCounters()
                .findCounter(CounterType.UNKNOWN).getValue());
    }

    @Test
    public void testMapperCounters() throws IOException {
        mapDriver
                .withInput(new LongWritable(), new Text(testDebug))
                .withInput(new LongWritable(), new Text(testInfo))
                .withInput(new LongWritable(), new Text(testNotice))
                .withInput(new LongWritable(), new Text(testWarning))
                .withInput(new LongWritable(), new Text(testError))
                .withInput(new LongWritable(), new Text(testCrit))
                .withInput(new LongWritable(), new Text(testAlert))
                .withInput(new LongWritable(), new Text(testEmerg))
                .withInput(new LongWritable(), new Text(testPanic))
                .withInput(new LongWritable(), new Text(testUnknownLevel))
                .withInput(new LongWritable(), new Text(testUnknownLevel))
                .withOutput(new Text("debug"), new IntWritable(1))
                .withOutput(new Text("info"), new IntWritable(1))
                .withOutput(new Text("notice"), new IntWritable(1))
                .withOutput(new Text("warning"), new IntWritable(1))
                .withOutput(new Text("error"), new IntWritable(1))
                .withOutput(new Text("crit"), new IntWritable(1))
                .withOutput(new Text("alert"), new IntWritable(1))
                .withOutput(new Text("emerg"), new IntWritable(1))
                .withOutput(new Text("emerg"), new IntWritable(1))
                .runTest();

        assertEquals("Expected 2 counter increment", 2, mapDriver.getCounters()
                .findCounter(CounterType.UNKNOWN).getValue());
    }
}

