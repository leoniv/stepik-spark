package hadoop.studying;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import java.io.IOException;
import java.lang.Iterable;

public class MaxClosePriceReducer
    extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values,  Context context)
      throws IOException, InterruptedException {
      Float max = Float.MIN_VALUE;
      for (FloatWritable value : values) {
        max = Math.max(max, value.get());
      }

      context.write(key, new FloatWritable(max));
    }
}
