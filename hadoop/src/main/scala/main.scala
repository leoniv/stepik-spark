package hadoop.studying

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{Text, FloatWritable}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, TextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.{
  FileOutputFormat,
  TextOutputFormat,
}
import scala.annotation.nowarn

// It expects input like:
//NASD,gog,1,0,5,99,190.1,1
//NASD,kek,1,5,99,10.1,1
//NASD,foo,1,0,5,99,19.1,1
//NASD,gog,1,0,5,99,19.1,1
//NASD,kek,1,0,5,99,160.1,1
//NASD,foo,1,0,5,99,32.1,1
object main {
  def main(args: Array[String]): Unit = {
    @nowarn("cat=deprecation")
    val job = new Job(new JobConf(true))
    val (arg1, arg2) = args.toList match {
      case a :: b :: rest => new Path(a) -> new Path(b)
      case _ => sys.error("Usage: <in path> <out path>")
    }
    job.setJarByClass(this.getClass())
    job.setJobName("Max close price")
    FileInputFormat.addInputPath(job, arg1)
    FileOutputFormat.setOutputPath(job, arg2)

    job.setInputFormatClass(classOf[TextInputFormat])
    job.setOutputFormatClass(classOf[TextOutputFormat[_, _]])

    job.setMapperClass(classOf[MaxClosePriceMapper])
    job.setReducerClass(classOf[MaxClosePriceReducer])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[FloatWritable])

    sys.exit(if (job.waitForCompletion(true)) 0 else 1)
  }
}
