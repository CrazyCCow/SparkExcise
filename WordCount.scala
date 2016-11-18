
import java.text.{SimpleDateFormat}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 * Created by daozhang on 2016/11/18.
 */
object WordCount {
  def main(args: Array[String]) {
    if (args.length == 0) {
      System.err.println("Usage: SogouA <file1>")
      System.exit(1)
    }
    val sdf = new SimpleDateFormat("HH:mm:ss")
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val line = sc.textFile(args(0))
    println(line.map(_.split("\\t")(0)).filter(x => sdf.parse(x).compareTo(sdf.parse("00:00:00"))>=0 && sdf.parse(x).compareTo(sdf.parse("12:00:00"))<=0).count)
    sc.stop()
  }
}