package hadoop.studying

import org.apache.hadoop.mapreduce.Mapper
import org.apache.hadoop.io.{Text, FloatWritable, LongWritable}

final class MaxClosePriceMapper()
    extends Mapper[LongWritable, Text, Text, FloatWritable]
