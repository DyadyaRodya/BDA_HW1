import bdlc.lab1.HW1Mapper;
import bdlc.lab1.HW1Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapReduceTest {

    private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

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
        HW1Reducer reducer = new HW1Reducer();
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws IOException {
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
    }

    @Test
    public void testReducer() throws IOException {
        List<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));
        reduceDriver
                .withInput(new Text("debug"), values)
                .withOutput(new Text("debug"), new IntWritable(2))
                .runTest();
    }

    @Test
    public void testMapReduce() throws IOException {
        mapReduceDriver
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
                .withOutput(new Text("alert"), new IntWritable(1))
                .withOutput(new Text("crit"), new IntWritable(1))
                .withOutput(new Text("debug"), new IntWritable(1))
                .withOutput(new Text("emerg"), new IntWritable(2))
                .withOutput(new Text("error"), new IntWritable(1))
                .withOutput(new Text("info"), new IntWritable(1))
                .withOutput(new Text("notice"), new IntWritable(1))
                .withOutput(new Text("warning"), new IntWritable(1))
                .runTest();
    }
}
