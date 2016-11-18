import org.apache.spark.{SparkContext, SparkConf}
/**
 * Created by daozhang on 2016/11/19.
 */
object WordCountB {
    def main(args: Array[String]) {
      if (args.length == 0) {
        System.err.println("Usage:  <file1>")
        System.exit(1)
      }

      val conf = new SparkConf().setMaster("local[*]")setAppName("SogouCountB")
      val sc = new SparkContext(conf)
      val sgRDD=sc.textFile(args(0))
      println(sgRDD.map(_.split('\t')).filter(_.length ==5).map(_(3).split(' ')).filter(_(0).toInt ==1).filter(_(1).toInt ==2).count)
      sc.stop()
    }
}
