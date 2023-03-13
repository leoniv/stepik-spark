package hadoop.studying

import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.io.{Text, FloatWritable}

public class MaxClosePriceReducer {

}
    extends Reducer[Text, FloatWritable, Text, FloatWritable] {
      override protected[mapreduce] def reduce(key: Text, values: lang.Iterable[FloatWritable], context: Context): Unit = ???
    }
