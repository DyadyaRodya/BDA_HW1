package bdlc.lab1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class HW1Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (line.toLowerCase().contains("debug")) {
            line = "debug";
            context.getCounter(CounterType.DEBUG).increment(1);
        } else if (line.toLowerCase().contains("info")) {
            context.getCounter(CounterType.INFO).increment(1);
            line = "info";
        } else if (line.toLowerCase().contains("notice")) {
            context.getCounter(CounterType.NOTICE).increment(1);
            line = "notice";
        } else if (line.toLowerCase().contains("warn")) {
            context.getCounter(CounterType.WARNING).increment(1);
            line = "warning";
        } else if (line.toLowerCase().contains("err")) {
            context.getCounter(CounterType.ERROR).increment(1);
            line = "error";
        } else if (line.toLowerCase().contains("crit")) {
            context.getCounter(CounterType.CRIT).increment(1);
            line = "crit";
        } else if (line.toLowerCase().contains("alert")) {
            context.getCounter(CounterType.ALERT).increment(1);
            line = "alert";
        } else if (line.toLowerCase().contains("emerg") || line.toLowerCase().contains("panic")) {
            context.getCounter(CounterType.EMERG).increment(1);
            line = "emerg";
        } else {
            context.getCounter(CounterType.UNKNOWN).increment(1);
            line = "";
        }
        if (!line.equals("")) {
            word.set(line);
            context.write(word, one);
        }
    }
}
//7-debug, 6-info, 5-notice, 4-warning/warn, 3-error/err, 2-crit, 1-alert, 0-emerg/panic