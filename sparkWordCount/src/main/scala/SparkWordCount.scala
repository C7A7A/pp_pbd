import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import scala.Console.println

object SparkWordCount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().
//      setMaster("local").
      setAppName("SparkWordCount")

//    println("DIR:", System.getProperty("user.dir"))
    val sc: SparkContext = new SparkContext(conf)
    val regex = args(0).r

    val path: String = "hdfs:///labs/spark/cano.txt"
//    val path: String = "file:///C:/Program Files/studia/studia_pp/pp_pbd/sparkWordCount/src/data/cano.txt"

    val cano = sc.textFile(path)
    val canoTokenized = cano.flatMap(_.split(" "))
    val canoWords = canoTokenized.map((_, 1))
    val canoWordCounts = canoWords.reduceByKey(_ + _)

//    val canoWordCountsTop10 = canoWordCounts.sortBy(_._2, ascending = false).take(10)
//    println(canoWordCountsTop10.mkString(",\n"))
    val canoWordRegex = canoWordCounts
        .filter(word => regex.findFirstIn(word._1).isDefined)
        .sortBy(_._2, ascending = false)
        .take(10)
    println(canoWordRegex.mkString(",\n"))
  }
}