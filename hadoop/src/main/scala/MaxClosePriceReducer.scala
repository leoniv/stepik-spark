package hadoop.studying

import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.io.{Text, FloatWritable}

final class MaxClosePriceReducer()
    extends Reducer[Text, FloatWritable, Text, FloatWritable]
