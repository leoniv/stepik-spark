package hadoop.studying;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;

class MaxClosePriceMapper
    extends Mapper<LongWritable, Text, Text, FloatWritable> {
  @Override
  protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    String[] items = value.toString().split(",");
    String stock = items[1];
    Float price = Float.parseFloat(items[6]);
    context.write(new Text(stock), new FloatWritable(price));
  }
}
