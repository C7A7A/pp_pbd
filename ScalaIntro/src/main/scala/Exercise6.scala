import java.util.TimeZone.getAvailableIDs

object Exercise6 {
  def main(args: Array[String]): Unit = {
    def howManyZonesInRegions(regions: Array[String]): Map[String, Int] = {
      val correctRegions = regions.filter(region => region.contains("/"))
//      println(correctRegions.mkString("Array(", ", ", ")"))
//      println(correctRegions.count(region => region.contains("Africa")))
      val groupedRegions = correctRegions.groupBy(region => region.take(region.indexOf("/")))
      val countedZones = groupedRegions.view.mapValues(_.length).toMap
      countedZones
    }

    val regions: Array[String] = getAvailableIDs
    println(howManyZonesInRegions(regions))
  }
}
