import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;
public class AvgSizeStations extends Configured implements Tool {
    private static final Logger LOG = Logger.getLogger(AvgSizeStations.class);
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new AvgSizeStations(), args);
        System.exit(res);
    }
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf(), "wordcount");
        job.setJarByClass(this.getClass());
        // Use TextInputFormat, the default unless job.setInputFormatClass is used
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(AvgSizeStationMapper.class);
        job.setReducerClass(AvgSizeStationReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }
    public static class AvgSizeStationMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text year = new Text();
        private IntWritable size = new IntWritable();
        private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            try {
                if (key.get() == 0)
                    return;
                else {
                    String line = value.toString();
                    int i = 0;
                    for (String word : line
                            .split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) {
                        if (i == 4) {
                            year.set(word.substring(word.lastIndexOf('/') + 1,
                                    word.lastIndexOf('/') + 5));
                        }
                        if (i == 5) {
                            size.set(Integer.parseInt(word));
                        }
                        i++;
                    }
                    context.write(year, size);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static class AvgSizeStationReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
        private DoubleWritable result = new DoubleWritable();
        Float average;
        Float count;
        int sum;
        @Override
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            average = 0f;
            count = 0f;
            sum = 0;
            Text sumText = new Text("average size of station for " + key
                    + " year is: ");
            for (IntWritable val : values) {
                sum += val.get();
                count += 1;
            }
            average = sum / count;
            result.set(average);
            context.write(sumText, result);
        }
    }
}